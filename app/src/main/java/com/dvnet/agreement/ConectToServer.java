package com.dvnet.agreement;

import static java.util.Collections.emptyList;

import android.app.Application;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ConectToServer {

    private String OdooHostCommon;
    private String OdooHostObject;
    private String OdooDb;
    private String OdooUser;
    private String OdooPass;

    XmlRpcClientConfigImpl common;
    XmlRpcClientConfigImpl object;
    XmlRpcClient client;

    public ConectToServer() {
    }


    public void getdatatoserver(String host, String database, String user, String password){
        this.OdooHostCommon = host + "/xmlrpc/2/common";
        this.OdooHostObject = host + "/xmlrpc/2/object";
        this.OdooDb = database;
        this.OdooUser = user;
        this.OdooPass = password;

        common = new XmlRpcClientConfigImpl();
        object = new XmlRpcClientConfigImpl();
        client = new XmlRpcClient();
        client.setTransportFactory(new XmlRpcCommonsTransportFactory(client));

        try {
            common.setServerURL(new URL(OdooHostCommon));
            object.setServerURL(new URL(OdooHostObject));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public int GetUser(){
        List parametrs = new ArrayList();
        parametrs.add(this.OdooDb);
        parametrs.add(this.OdooUser);
        parametrs.add(this.OdooPass);

        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);

        int uid = 0;
        try {
            uid = (int)client.execute(
                    common,
                    "authenticate",
                    parametrs);
        } catch (XmlRpcException e) {
            e.printStackTrace();
            Log.i("Conect To Server", "GetUser: " + e.toString());
        }

        return uid;
    }
    public void GetVersion(){

        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);

        List parameter = null;


        try {
            client.execute(common,
                    "version",
                    //emptyList()
                    parameter);
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
    }



}
