package cn.sleepycoder.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int TYPE_Normal = 1;
    public static final int TYPE_Progress = 2;
    public static final int TYPE_BigText = 3;
    public static final int TYPE_Inbox = 4;
    public static final int TYPE_BigPicture = 5;
    public static final int TYPE_Hangup = 6;
    public static final int TYPE_Media = 7;
    public static final int TYPE_Customer = 8;
    private NotificationManager manger;
    private Intent service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        service = new Intent(this,DownloadService.class);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn1:
                simpleNotify();
                break;
            case R.id.btn2:
                startService(service);
                break;
            case R.id.btn3:
                bigTextStyle();
                break;
            case R.id.btn4:
                inBoxStyle();
                break;
            case R.id.btn5:
                bigPictureStyle();
                break;
            case R.id.btn6:
                hangup();
                break;
            case R.id.btn7:
                mediaStyle();
                break;
            case R.id.btn8:
                customStyle();
              notificationClick();
//                Intent intent = new Intent(this,MediaService.class);
//                startService(intent);
                break;
            default:
                break;
        }
    }

    private void simpleNotify(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        //Ticker是状态栏显示的提示
        builder.setTicker("简单Notification");
        //第一行内容  通常作为通知栏标题
        builder.setContentTitle("标题");
        //第二行内容 通常是通知正文
        builder.setContentText("通知内容");
        //第三行内容 通常是内容摘要什么的 在低版本机器上不一定显示
        builder.setSubText("这里显示的是通知第三行内容！");
        //ContentInfo 在通知的右侧 时间的下面 用来展示一些其他信息
        //builder.setContentInfo("2");
        builder.setAutoCancel(true);
        builder.setNumber(2);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.push));
        Intent intent = new Intent(this,SettingsActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
        builder.setContentIntent(pIntent);


      builder.addAction(R.drawable.notification,
        "1",null);

      builder.addAction(R.drawable.notification,
        "2",
        null);
        //设置震动
        //long[] vibrate = {100,200,100,200};
        //builder.setVibrate(vibrate);

        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification notification = builder.build();
        manger.notify(TYPE_Normal,notification);
    }

    private void bigTextStyle(){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("BigTextStyle");
        builder.setContentText("BigTextStyle演示示例");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.notification));
        android.support.v7.app.NotificationCompat.BigTextStyle style = new android.support.v7.app.NotificationCompat.BigTextStyle();
        style.bigText("这里是点击通知后要显示的正文，可以换行可以显示很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长");
        style.setBigContentTitle("点击后的标题");
        style.setSummaryText("末尾只一行的文字内容");
        builder.setStyle(style);
        builder.setAutoCancel(true);
        Intent intent = new Intent(this,SettingsActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
        builder.setContentIntent(pIntent);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification notification = builder.build();
        manger.notify(TYPE_BigText,notification);
    }

    /**
     * 最多显示五行 再多会有截断
     */
    public void inBoxStyle(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("InboxStyle");
        builder.setContentText("InboxStyle演示示例");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.notification));
        android.support.v7.app.NotificationCompat.InboxStyle style = new android.support.v7.app.NotificationCompat.InboxStyle();
        style.setBigContentTitle("BigContentTitle")
                .addLine("第一行，第一行，第一行，第一行，第一行，第一行，第一行")
                .addLine("第二行")
                .addLine("第三行")
                .addLine("第四行")
                .addLine("第五行")
                .setSummaryText("SummaryText");
        builder.setStyle(style);
        builder.setAutoCancel(true);
        Intent intent = new Intent(this,SettingsActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
        builder.setContentIntent(pIntent);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification notification = builder.build();
        manger.notify(TYPE_Inbox,notification);
    }

    public void bigPictureStyle(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("BigPictureStyle");
        builder.setContentText("BigPicture演示示例");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.notification));
        android.support.v7.app.NotificationCompat.BigPictureStyle style = new android.support.v7.app.NotificationCompat.BigPictureStyle();
        style.setBigContentTitle("BigContentTitle");
        style.setSummaryText("SummaryText");
        style.bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.small));
        builder.setStyle(style);
        builder.setAutoCancel(true);
        Intent intent = new Intent(this,ImageActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
        builder.setContentIntent(pIntent);
        Notification notification = builder.build();
        manger.notify(TYPE_BigPicture,notification);
    }

    private void hangup(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            Toast.makeText(MainActivity.this, "此类通知在Android 5.0以上版本才会有横幅有效！", Toast.LENGTH_SHORT).show();
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("横幅通知");
        builder.setContentText("请在设置通知管理中开启消息横幅提醒权限");
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.notification));
        Intent intent = new Intent(this,ImageActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
        builder.setContentIntent(pIntent);
        builder.setFullScreenIntent(pIntent,true);
        builder.setAutoCancel(true);
      builder.addAction(R.drawable.notification,
        "1",null);

      builder.addAction(R.drawable.notification,
        "2",
        null);
        Notification notification = builder.build();
        manger.notify(TYPE_Hangup,notification);
    }

    private void mediaStyle(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("MediaStyle");
        builder.setContentText("Song Title");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.notification));
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Intent intent = new Intent(this,ImageActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
        builder.setContentIntent(pIntent);
        builder.addAction(R.drawable.ic_previous_white,"",null);
        builder.addAction(R.drawable.ic_stop_white,"",null);
        builder.addAction(R.drawable.ic_play_arrow_white_18dp,"",pIntent);
        builder.addAction(R.drawable.ic_next_white,"",null);
        NotificationCompat.MediaStyle style = new NotificationCompat.MediaStyle();
        style.setMediaSession(new MediaSessionCompat(this,"MediaSession",
                new ComponentName(MainActivity.this,Intent.ACTION_MEDIA_BUTTON),null).getSessionToken());
        //CancelButton在5.0以下的机器有效
        style.setCancelButtonIntent(pIntent);
        style.setShowCancelButton(true);
        //设置要现实在通知右方的图标 最多三个
        style.setShowActionsInCompactView(2,3);
        builder.setStyle(style);
        builder.setShowWhen(false);

      Notification notification = builder.build();
        manger.notify(TYPE_Media,notification);
    }

  private void customStyle(){
    RemoteViews view = new RemoteViews(getPackageName(), R.layout.customer);
    view.setTextViewText(R.id.tv_1, "gg1");
    view.setTextViewText(R.id.tv_2, "gg2");
    view.setTextViewText(R.id.tv_3, "gg3");
//    view.setImageViewResource(R.id.iv, R.drawable.notification);

    NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
    builder.setSmallIcon(R.drawable.notification);
//    builder.setContent(view);
    Notification notification = builder.build();
    notification.contentView = view;
    manger.notify(TYPE_Customer,notification);
  }

  public void notificationClick() {

    // 1. 创建一个 NotificationCompat.Builder 对象，并对其进行设置
    android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this)
      .setSmallIcon(R.drawable.notification);

    // 创建带自定义通知的步骤：
    //   创建一个 RemoteViews 对象：
    //      第一个参数为当前包名，
    //      第二个参数为要传入的自定义布局，
    RemoteViews rv = new RemoteViews(getPackageName(), R.layout.customer);

    // 2. 创建一个 Notification 对象，并对其进行设置
    // 注意：当 NotificationCompat.Builder 对象设置完成之后才创建，
    // 否则会导致之后 NotificationCompat.Builder 对象的设置无效
    Notification notification = builder.build();
    notification.contentView = rv; // 调用 Notification 对象的 contentView 方法来传入自定义的布局
    notification.defaults = Notification.DEFAULT_ALL; // 设置通知震动和声音为默认

    // 3. 创建一个 NotificationManager 对象来获取通知管理器
    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    // 4. 使用 NotificationManager 的 notify 方法来发送通知
    manager.notify(105, notification);
  }


    @Override
    protected void onDestroy() {
        if(service!=null){
            stopService(service);
        }
        super.onDestroy();
    }
}
