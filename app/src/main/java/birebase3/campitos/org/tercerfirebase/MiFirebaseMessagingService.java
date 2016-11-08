package birebase3.campitos.org.tercerfirebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by campitos on 8/11/16.
 */

public class MiFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG="MiFirebaseMsgService";

    /**
     *
     * @param remoteMessage Este Objeto representa el mensaje recivido de fireabse
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        /*
        Aqui se reciben los mensajes, cuando la aplicación esta en foreground
        aqui se reciben los mensajes.
        Si la aplicación esta en el background automaticamente se reciben como
        notificaciones, que al ahcer tap en ella acrtiva la app.
        Para mayores informes ver:
        https://firebase.google.com/docs/cloud-messaging/concept-options
         */
        Log.d(TAG,"De:"+remoteMessage.getFrom());

        //Checamos si el mensaje tiene un data payload
        if(remoteMessage.getData().size()>0){
            Log.d(TAG,"Mensaje payload es: "+remoteMessage.getData());
        }

        //Checamos si el mensaje tiene una notificación payload
        if(remoteMessage.getNotification()!=null){
            Log.d(TAG,"Cuerpo dem mensaje de notificación: "+remoteMessage.getNotification().getBody());
        }
        //Aqui tambien iria código para generar nuestras propias notificaciones
        //Como resulatdo de recibir un mensaje de FCM

    }

    /**
     * Creamos y mostramos una notificacion propia conteniendo el FCM recibido
     *
     * @param cuerpoMensaje  Cuerpo del mensaje recibido
     */

    private  void mandarNotoificacion(String cuerpoMensaje){

        Intent intent=new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        Uri uriConSonido= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder noBuilder=new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_ic_notification)
                .setContentTitle("Mensajito FCM")
                .setContentText(cuerpoMensaje)
                .setAutoCancel(true)
                .setSound(uriConSonido)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
         notificationManager.notify(0/*ID de servicio*/,noBuilder.build());


    }
}
