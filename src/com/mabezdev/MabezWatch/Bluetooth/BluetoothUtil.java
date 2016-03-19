package com.mabezdev.MabezWatch.Bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.widget.Toast;
import com.mabezdev.MabezWatch.Activities.Main;
import com.mabezdev.MabezWatch.Util.ObjectWriter;

import java.util.Set;

/**
 * Created by Mabez on 19/03/2016.
 */
public class BluetoothUtil {

    private static BluetoothDevice chosenBT;
    private static Set<BluetoothDevice> pairedDevices;

    private BluetoothUtil(){

    }

    public static Set<BluetoothDevice> getBluetoothDevices(BluetoothAdapter myBluetoothAdapter){
        pairedDevices = myBluetoothAdapter.getBondedDevices();
        return pairedDevices;
    }

    public static BluetoothAdapter getDefaultAdapter(){
        return BluetoothAdapter.getDefaultAdapter();
    }

    public static BluetoothDevice getDeviceFromAddress(String address, BluetoothAdapter bluetoothAdapter){
        if(pairedDevices==null){
            pairedDevices = bluetoothAdapter.getBondedDevices();
        }
        for(BluetoothDevice bt : pairedDevices){
            if(bt.getAddress().equals(address)){
                return bt;
            }
        }
        return null;
    }

    public static void setChosenDevice(BluetoothDevice b){
        chosenBT = b;
    }

    public static BluetoothDevice getDeviceToConnect(){
        return chosenBT;
    }

    public static void turnOnBluetooth(BluetoothAdapter b){
        if (!b.isEnabled()) {
            b.enable();
        }
    }
    public  static void turnOffBluetooth(BluetoothAdapter b){
        if (b.isEnabled()) {
            b.disable();
        }
    }

    public static void storeDevice(BluetoothDevice BT){
        //save bluetoothDevice
        DeviceSave store = new DeviceSave(BT);

        boolean done = ObjectWriter.writeObject(Main.BLUETOOTH_FILE,store);
        if(!done){
            System.out.println("Write failed! The preffered device will not be stored.");
        }
    }



}