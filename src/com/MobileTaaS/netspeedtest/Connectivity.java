package com.MobileTaaS.netspeedtest;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Check device's network connectivity and speed 
 * @author emil http://stackoverflow.com/users/220710/emil
 *
 */
public class Connectivity extends ExecuteAsRootBase {

    /**
     * Get the network info
     * @param context
     * @return
     */
    public static NetworkInfo getNetworkInfo(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    /**
     * Check if there is any connectivity
     * @param context
     * @return
     */
    public static boolean isConnected(Context context){
        NetworkInfo info = Connectivity.getNetworkInfo(context);
        return (info != null && info.isConnected());
    }

    /**
     * Check if there is any connectivity to a Wifi network
     * @param context
     * @param type
     * @return
     */
    public static boolean isConnectedWifi(Context context){
        NetworkInfo info = Connectivity.getNetworkInfo(context);
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
    }

    /**
     * Check if there is any connectivity to a mobile network
     * @param context
     * @param type
     * @return
     */
    public static boolean isConnectedMobile(Context context){
        NetworkInfo info = Connectivity.getNetworkInfo(context);
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
    }

    /**
     * Check if there is fast connectivity
     * @param context
     * @return
     */
    public static boolean isConnectedFast(Context context){
        NetworkInfo info = Connectivity.getNetworkInfo(context);
        return (info != null && info.isConnected() && Connectivity.isConnectionFast(info.getType(),info.getSubtype()));
    }

    
    public static String connectionSpeed(Context context){
    	
    	 NetworkInfo info = Connectivity.getNetworkInfo(context);
    	
    	
    	String connSpeed = "XXXXX";
        if(info.getType()==ConnectivityManager.TYPE_WIFI){
        	System.out.println("WIFI");
        	
            return "WIFI";
            
        }else if(info.getType()==ConnectivityManager.TYPE_MOBILE){
        	
        	System.out.println("mobile");
        	
            switch(info.getSubtype()){
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            	return "NETWORK_TYPE_1"; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_CDMA:
            	return "NETWORK_TYPE_CDMA"; // ~ 14-64 kbps
            case TelephonyManager.NETWORK_TYPE_EDGE:
            	return "NETWORK_TYPE_EDGE"; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            	return "NETWORK_TYPE_EVDO_0"; // ~ 400-1000 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            	return "NETWORK_TYPE_EVDO_A"; // ~ 600-1400 kbps
            case TelephonyManager.NETWORK_TYPE_GPRS:
            	System.out.println("GPRS");
            	return "GPRS"; // ~ 100 kbps
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            	return "HSDPA"; // ~ 2-14 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPA:
            	return "HSPA"; // ~ 700-1700 kbps
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            	return "HSUPA"; // ~ 1-23 Mbps
            case TelephonyManager.NETWORK_TYPE_UMTS:
            	System.out.println("UMTS");
            	return "UMTS"; // ~ 400-7000 kbps
            /*
             * Above API level 7, make sure to set android:targetSdkVersion 
             * to appropriate level to use these
             */
            case TelephonyManager.NETWORK_TYPE_EHRPD: // API level 11 
            	return "EHRPD"; // ~ 1-2 Mbps
            case TelephonyManager.NETWORK_TYPE_EVDO_B: // API level 9
            	return "NETWORK_TYPE_EVDO_B"; // ~ 5 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPAP: // API level 13
            	return "HSPAP"; // ~ 10-20 Mbps
            case TelephonyManager.NETWORK_TYPE_IDEN: // API level 8
            	return "IDEN"; // ~25 kbps 
            case TelephonyManager.NETWORK_TYPE_LTE: // API level 11
            	return "LTE"; // ~ 10+ Mbps
            // Unknown
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
            	return "SPEED UNKOWN !!!";
            }
        }else{
        	return "UNKNOWN TYPE ";
        }
        
        return connSpeed;
    }
    
    /**
     * Check if the connection is fast
     * @param type
     * @param subType
     * @return
     */
    public static boolean isConnectionFast(int type, int subType){
        if(type==ConnectivityManager.TYPE_WIFI){
        	System.out.println("WIFI");
        	
            return true;
        }else if(type==ConnectivityManager.TYPE_MOBILE){
        	
        	System.out.println("mobile");
        	
            switch(subType){
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return false; // ~ 14-64 kbps
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return true; // ~ 400-1000 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return true; // ~ 600-1400 kbps
            case TelephonyManager.NETWORK_TYPE_GPRS:
            	System.out.println("GPRS");
                return false; // ~ 100 kbps
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return true; // ~ 2-14 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return true; // ~ 700-1700 kbps
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return true; // ~ 1-23 Mbps
            case TelephonyManager.NETWORK_TYPE_UMTS:
            	System.out.println("UMTS");
                return true; // ~ 400-7000 kbps
            /*
             * Above API level 7, make sure to set android:targetSdkVersion 
             * to appropriate level to use these
             */
            case TelephonyManager.NETWORK_TYPE_EHRPD: // API level 11 
                return true; // ~ 1-2 Mbps
            case TelephonyManager.NETWORK_TYPE_EVDO_B: // API level 9
                return true; // ~ 5 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPAP: // API level 13
                return true; // ~ 10-20 Mbps
            case TelephonyManager.NETWORK_TYPE_IDEN: // API level 8
                return false; // ~25 kbps 
            case TelephonyManager.NETWORK_TYPE_LTE: // API level 11
                return true; // ~ 10+ Mbps
            // Unknown
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
            default:
                return false;
            }
        }else{
            return false;
        }
    }

	@Override
	protected ArrayList<String> getCommandsToExecute() {
		// TODO Auto-generated method stub
		return null;
	}

	public static boolean canRunRootCommands()
	   {
	      boolean retval = false;
	      Process suProcess;

	      try
	      {
	         suProcess = Runtime.getRuntime().exec("su");

	         DataOutputStream os = new DataOutputStream(suProcess.getOutputStream());
	         DataInputStream osRes = new DataInputStream(suProcess.getInputStream());

	         if (null != os && null != osRes)
	         {
	            // Getting the id of the current user to check if this is root
	            os.writeBytes("id\n");
	            os.flush();

	            String currUid = osRes.readLine();
	            boolean exitSu = false;
	            if (null == currUid)
	            {
	               retval = false;
	               exitSu = false;
	               Log.d("ROOT", "Can't get root access or denied by user");
	            }
	            else if (true == currUid.contains("uid=0"))
	            {
	               retval = true;
	               exitSu = true;
	               Log.d("ROOT", "Root access granted");
	            }
	            else
	            {
	               retval = false;
	               exitSu = true;
	               Log.d("ROOT", "Root access rejected: " + currUid);
	            }

	            if (exitSu)
	            {
	               os.writeBytes("exit\n");
	               os.flush();
	            }
	         }
	      }
	      catch (Exception e)
	      {
	         // Can't get root !
	         // Probably broken pipe exception on trying to write to output stream (os) after su failed, meaning that the device is not rooted

	         retval = false;
	         Log.d("ROOT", "Root access rejected [" + e.getClass().getName() + "] : " + e.getMessage());
	      }

	      return retval;
	   }

}