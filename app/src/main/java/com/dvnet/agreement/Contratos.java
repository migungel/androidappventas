package com.dvnet.agreement;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.dvnet.agreement.data.Smart;
import com.dvnet.agreement.data.User;
import com.dvnet.agreement.data.agreement.Agremeent;
import com.dvnet.agreement.data.partner.Partner;

import org.xml.sax.Parser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Contratos extends AppCompatActivity {
    private String contrato;
    private Agremeent agrement;
    private Partner partner;
    private User user;
    private Resources res;
    private Context context;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_agreementview);
////        context = getApplicationContext();
////        res = context.getResources();
//        Log.i("create", "sssssssss " + res);
//    }

    public Contratos(){ }

    public Contratos(Context current){
        this.context = current;
    }

    public Agremeent getAgrement() {
        return agrement;
    }

    public void setAgrement(Agremeent agrement) {
        this.agrement = agrement;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public String getContrato() {
        getInformation();
        return contrato;
    }


    private void getInformation(){
        //this.contrato = String.format(plantilla, ag.getCompany());

        boolean television = false;
        boolean telefonia = false;
        boolean internet = false;
        boolean smart = false;

        switch(agrement.getTipoServicio_Id()) {
            case "tva":
                television = true;
                break;
            case "tvd":
                television = true;
                break;
            case "int":
                internet = true;
                break;
            case "dpa":
                television = true;
                internet = true;
                break;
            case "dpd":
                television = true;
                internet = true;
                break;
            case "tel":
                telefonia = true;
                break;
            case "dpat":
                television = true;
                telefonia = true;
                break;
            case "dpdt":
                television = true;
                telefonia = true;
                break;
            case "dpit":
                internet = true;
                telefonia = true;
                break;
            case "tpa":
                television = true;
                internet = true;
                telefonia = true;
                break;
            case "tpd":
                television = true;
                internet = true;
                telefonia = true;
                break;
            case "smart":
                smart = true;
        }

        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<style>\n" +
                "     \n" +
                "    div.SaltoDePagina { \n" +
                "        padding-bottom: 50px; \n" +
                "        PAGE-BREAK-AFTER: always \n" +
                "    }\n" +
                "    .titulo{\n" + //<table style=\"font-size:20px; font-family: Courier New, Lucida Console, monospace;
                "        font-family: Arial,sans-serif;\n" +
                "        text-decoration: underline;\n" +
                "        text-align: center;\n" +
                "        font-size: 18px;\n" +
                "        font-weight: bold;\n" +
                "        width: 100%\n" +
                "    }\n" +
                "   .anexo{\n" +
                "       font-family: Arial,sans-serif;\n" +
                "       text-align: center;\n" +
                "       font-size: 18px;\n" +
                "   }\n" +
                "   .subtitulo{\n" +
                "       font-family: Arial,sans-serif;\n" +
                "       text-decoration: underline;\n" +
                "       font-size: 16px;\n" +
                "       font-weight: bold;\n" +
                "   }\n" +
                "   .tabla1{\n" +
                "       border-collapse: collapse;\n" +
                //"       border-color: #000000;\n" +
                "       width: 100%;\n" +
                "       text-align: justify;\n" +
                "       font-size: 12px;\n" +
                "   }\n" +
                "   .sino{\n" +
                "       width: 50px;\n" +
                "       text-align: center;\n" +
                "       font-weight: bold;\n" +
                "   }\n" +
                "   .tabla2{\n" +
                "       border: none;\n" +
                "       border-collapse: collapse;\n" +
                "       width: 100%;\n" +
                "       font-size: 12px;\n" +
                "   }\n" +
                "   body{\n" +
                "       font-family: Arial;\n" +
                "   }\n" +
                "</style>\n" +
                "<body>\n";

        Date c = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatterd = new SimpleDateFormat("dd");
        SimpleDateFormat formatterm = new SimpleDateFormat("MM");
        SimpleDateFormat formattery = new SimpleDateFormat("yyyy");
        String date = formatter.format(c);

        int day =  Integer.parseInt(formatterd.format(c));  //c.get(Calendar.DAY_OF_MONTH);
        int month = Integer.parseInt(formatterm.format(c));
        int year = Integer.parseInt(formattery.format(c));
        String mes = "ENERO";

        switch (month){
            case 1:
                date = day + " de Enero de " + year;
                mes = "ENERO";
                break;
            case 2:
                date = day + " de Febrero de " + year;
                mes = "FEBRERO";
                break;
            case 3:
                date = day + " de Marzo de " + year;
                mes = "MARZO";
                break;
            case 4:
                date = day + " de Abril de " + year;
                mes = "ABRIL";
                break;
            case 5:
                date = day + " de Mayo de " + year;
                mes = "MAYO";
                break;
            case 6:
                date = day + " de Junio de " + year;
                mes = "JUMIO";
                break;
            case 7:
                date = day + " de Julio de " + year;
                mes = "JULIO";
                break;
            case 8:
                date = day + " de Agosto de " + year;
                mes = "AGOSTO";
                break;
            case 9:
                date = day + " de Septiembre de " + year;
                mes = "SEPTIEMBRE";
                break;
            case 10:
                date = day + " de Octubre de " + year;
                mes = "OCTUBRE";
                break;
            case 11:
                date = day + " de Noviembre de " + year;
                mes = "NOVIEMBRE";
                break;
            case 12:
                date = day + " de Diciembre de " + year;
                mes = "DICIEMBRE";
                break;
        }

        if (internet == true){
            html += contratoint(day, mes, year, date);
            html += anexoa(day, mes, year);
            html += anexob(day, mes, year);
            html += anexoc(day, mes, year);
            html += anexod(day, mes, year);
            html += pagare(day, mes, year);
        }

        if (television == true){
            html += contratotv(day, mes, year);
            html += pagare(day, mes, year);
        }

        if(telefonia == true){
            html += telefonia();
        }

//        if (smart) {
//            html += smart(day, mes, year, date);
//        }

        if( !agrement.getSmart_id().equals("0") || smart){
            html += smart(day, mes, year, date);
        }

        html +=  "</body>\n" +
                "</html>\n";

        this.contrato = html;

    }


    private String contratotv(int dia, String mes, int anio){

        String firmapng = Base64.encodeToString(agrement.getSing(), Base64.DEFAULT);
        String firmauserpng = Base64.encodeToString(user.getSing(), Base64.DEFAULT);

        String ContratoTv = "<div class=SaltoDePagina>\n" +
                "        <table width=100% style=\"font-size:12px;\">\n" +
                "            <tr>\n" +
                "                <td align=\"center\"><strong>CONTRATO DE SERVICIO DE CABLE</strong></td>\n" +
                "            </tr>\n" +
                "            <tr><td></br></td></tr>\n" +
                "            <tr><td></br></td></tr>\n" +
                "            <tr>\n" +
                "                <td align=\"justify\">" + agrement.getCompany() + " Es un sistema de televisi??n por cable que cuenta con la autorizaci??n de la ARCOTEL para administrar el sistema de televisi??n pagada por suscripci??n, que opera en la ciudad de Daule provincia del guayas, con sucursales en los lugares de: CANTONES: NOBOL, SANTA LUCIA, LOMAS DE SARGENTILLO, PEDRO CARBO, PALESTINA, COLIMES Y SALITRE. PARROQUIAS: EL LAUREL, EL LIMONAL, SABANILLA. RECINTO: EL MATE. COMUNA: PETRILLO.</td>\n" +
                "            </tr>\n" +
                "            <tr><td></br></td></tr>\n" +
                "            <tr>\n" +
                "                <td align=\"justify\">" + agrement.getCompany() + " proveer?? el servicio ZONA CANTONAL "+ agrement.getCanton() +" de televisi??n pagada a favor de quien suscriba el presente contrato, en adelante llamado SUSCRIPTOR siempre que cumpla con todas las obligaciones que se comprometa por este contrato, y especialmente cancele cumplidamente el valor relativo a los derechos de instalaci??n y derecho de activaci??n que se especifica en este contrato bajo el t??tulo de PAGUE POR UNA SOLA VEZ la tarifa mensual que corresponda; as?? como los impuestos que hubiere lugar.</td>\n" +
                "            </tr>\n" +
                "            <tr><td></br></td></tr>\n" +
                "            <tr>\n" +
                "                <td align=\"justify\">El SUSCRIPTOR pagar?? las cuotas a las que se refiere el contrato MAXIMO TRES d??as despu??s de la fecha de instalaci??n, sin necesidad de notificaci??n previa, mediante pago directo en las oficinas de DV TELEVISION DVTV S.A., pago a alg??n recaudador debidamente autorizado y que porte identificaci??n de la compa????a,  seg??n sea su conveniencia. En caso de MORA DV TELEVISION DVTV S.A. queda autorizada, a suspender el servicio y el suscriptor se compromete a cancelar los costos de reconexi??n debiendo en todo caso cancelar los valores atrasados, caso contrario se iniciara las acciones legales correspondientes.</td>\n" +
                "            </tr>\n" +
                "            <tr><td></br></td></tr>\n" +
                "            <tr>\n" +
                "                <td align=\"justify\">DV TELEVISION DVTV S.A. no ser?? responsable de las posibles y eventuales suspensiones del servicio, ya sea total o parcialmente, si ??stas se producen por causas de fuerza mayor o por razones ajenas a la voluntad y control de DV TELEVISION DVTV S.A., sin perjuicio que esta realizara sus mejores esfuerzos para establecer el servicio en las mejores condiciones. DV TELEVISION DVTV S.A. tampoco ser?? responsable respecto a la calidad y contenido de la programaci??n que se transmita a trav??s de los diferentes canales.</td>\n" +
                "            </tr>\n" +
                "            <tr><td></br></td></tr>\n" +
                "            <tr>\n" +
                "                <td align=\"justify\">El suscriptor no podr?? compartir los servicios de DV TELEVISION DVTV S.A. con terceros, ya que son para uso exclusivo y disfrute del suscriptor, quien ser?? civil y penalmente responsable de su correcta utilizaci??n en los t??rminos de este contrato. Si ello sucediere ya sea mediante conexiones o instalaciones a otros equipos de televisi??n o de sonido, DV TELEVISION DVTV S.A. queda expresamente facultada a dar por terminado el contrato, sin perjuicios que pueda ejercer todas las acciones legales a la que tenga derecho.</td>\n" +
                "            </tr>\n" +
                "            <tr><td></br></td></tr>\n" +
                "            <tr>\n" +
                "                <td align=\"justify\">DV TELEVISION DVTV S.A. podr??, a su solo juicio, variar el monto de las tarifas mensuales que el suscriptor debe cancelar por sus servicios, para el efecto deber?? emitir una comunicaci??n escrita a la ??ltima direcci??n declarada por el suscriptor, con no menos de quince d??as de anticipaci??n a la vigencia de las nuevas tarifas.</td>\n" +
                "            </tr>\n" +
                "            <tr><td></br></td></tr>\n" +
                "            <tr>\n" +
                "                <td align=\"justify\">DV TELEVISION DVTV S.A. es propietario del cable con el que se realiza la instalaci??n, el cual no puede exceder de 40 metros por cada una; En caso de exceder esta medida, DV TELEVISION DVTV S.A. tendr?? derecho a facturar el adicional utilizado al suscriptor, y tambi??n tendr??  derecho a retirarlo en caso de que se  d?? por terminado el contrato por cualquiera de las dos partes.</td>\n" +
                "            </tr>\n" +
                "            <tr><td></br></td></tr>\n" +
                "            <tr>\n" +
                "                <td align=\"justify\">???Autorizo(amos) expresa e irrevocablemente a DV TELEVISION DVTV S.A. o quien sea el futuro cesionario, beneficiario o acreedor del cr??dito solicitado o del documento o t??tulo cambiario que lo respalde para que obtenga cuantas veces sean necesarias, de cualquier fuente de informaci??n, incluidos los bur??s de cr??dito, mi informaci??n de riesgos crediticios, de igual forma DV TELEVISION DVTV S.A. o quien sea el futuro cesionario, beneficiario o acreedor del cr??dito solicitado o del documento o t??tulo cambiario que lo respalde queda expresamente autorizado para que pueda transferir o entregar dicha informaci??n a los bur??s de cr??dito y/o a la Central de Riesgos si fuere pertinente???.</td>\n" +
                "            </tr>\n" +
                "            <tr><td></br></td></tr>\n" +
                "            <tr>\n" +
                "                <td align=\"justify\">La duraci??n m??nima del presente contrato es de UN A??O (365 d??as).</td>\n" +
                "            </tr>\n" +
                "            <tr><td>\n" +
                "                <table width=100%>\n" +
                "                    <tr>\n" +
                "                       <td align=\"center\" width=50%><img src=\"data:image/png;base64," + firmauserpng + "\" width=\"200\" height=\"100\"></td>\n" +
                "                       <td align=\"center\" width=50%><img src=\"data:image/png;base64," + firmapng + "\" width=\"200\" height=\"100\"></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" width=50%>DV TELEVISION DVTV S.A.</td>\n" +
                "                        <td align=\"center\" width=50%>EL SUSCRIPTOR</td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td></tr>\n" +
                "            <tr>\n" +
                "                <td align=\"center\">ABRIENDO HORIZONTES</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td align=\"center\">Direcci??n: 9 de Octubre y Jos?? V??lez-Daule</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td align=\"center\">TELEFONO: 2797023-094097664</td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "</br>\n" +
                "</br>\n" +
                "    <div class=SaltoDePagina>\n" +
                "        <table width=100% style=\"font-size:12px;\">\n" +
                "            <tr><td align=\"right\">??Abriendo horizontes!</td></tr>\n" +
                "            <tr><td></br></td></tr>\n" +
                "            <tr><td>\n" +
                "                    <table width=100% style=\"font-size:12px;\">\n" +
                "                        <tr>\n" +
                "                            <td width=20%>CONTRATO N??-</td>\n" +
                "                            <td width=20%></td>\n" +
                "                            <td width=20%></td>\n" +
                "                            <td width=20%>FECHA:</td>\n" +
                "                            <td width=20%>" + dia + " - " + mes + " - " + anio + "</td>\n" +
                "                        </tr>\n" +
                "                    </table>\n" +
                "            </td></tr>\n" +
                "            <tr><td align=\"center\"><strong>CONTRATO DE SERVICIO DE CABLE</strong></td></tr>\n" +
                "            <tr><td align=\"center\"><strong>DATOS DEL SUSCRIPTOR</strong></td></tr>\n" +
                "            <tr><td width=90%>\n" +
                "                <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                    <tr>\n" +
                "                        <td width=40%><strong>C.I./RUC</strong></td>\n" +
                "                        <td width=60%>" + partner.getIdentification_id() + "</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td width=40%><strong>NOMBRES</strong></td>\n" +
                "                        <td width=60%>" + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + "</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td width=40%><strong>FECHA/NACIMIENTO</strong></td>\n" +
                "                        <td width=60%>" + partner.getNacimiento() + "</td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td></tr>\n" +
                "            <tr><td align=\"center\"><strong>DIRECCION DEL SUSCRIPTOR</strong></td></tr>\n" +
                "            <tr><td width=90%>\n" +
                "                <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                    <tr>\n" +
                "                        <td width=40%><strong>CALLE</strong></td>\n" +
                "                        <td width=60%>" + partner.getStreet() + "</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td width=40%><strong>SECTOR</strong></td>\n" +
                "                        <td width=60%>" + agrement.getSector() + "</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td width=40%><strong>POSTE</strong></td>\n" +
                "                        <td width=60%>" + agrement.getPoste() + "</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td width=40%><strong>CIUDAD</strong></td>\n" +
                "                        <td width=60%>"+ partner.getCanton() +"</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td width=40%><strong>TELEFONO</strong></td>\n" +
                "                        <td width=60%>" + partner.getPhone() + " " + partner.getMobile() + "</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td width=40%><strong>EMAIL</strong></td>\n" +
                "                        <td width=60%>" + partner.getEmail() + "</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td width=40%><strong>REFERENCIA</strong></td>\n" +
                "                        <td width=60%>" + partner.getStreet2() + "</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td width=40%><strong>SERVICIO ANTERIOR</strong></td>\n" +
                "                        <td width=60%></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td width=40%><strong>POSTE REFERENCIAL</strong></td>\n" +
                "                        <td width=60%></td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td></tr>\n" +
                "            <tr><td align=\"center\"><strong>TIPO DE SERVICIO</strong></td></tr>\n" +
                "            <tr><td width=90%>\n" +
                "                <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                    <tr>\n" +
                "                        <td width=40%><strong>SERVICIO BASICO</strong></td>\n" +
                "                        <td width=60%></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td width=40%><strong>SERVICIO PROMOCION</strong></td>\n" +
                "                        <td width=60%></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td width=40%><strong>SERVICIO + UN ADICIONAL</strong></td>\n" +
                "                        <td width=60%></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td width=40%><strong>SERVICIO + DOS ADICIONALES</strong></td>\n" +
                "                        <td width=60%></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td width=40%><strong>TOTAL SERVICIO CONTRATADO</strong></td>\n" +
                "                        <td width=60%></td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td></tr>\n" +
                "            <tr><td align=\"center\"><strong>MATERIALES UTILIZADOS (EGRESO DE BODEGA</strong>)</td></tr>\n" +
                "            <tr><td width=90%>\n" +
                "                <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                    <tr>\n" +
                "                        <td width=30% align=\"center\"><strong>MATERIALES</strong></td>\n" +
                "                        <td width=30% align=\"center\"><strong>CANTIDAD</strong></td>\n" +
                "                        <td width=40% align=\"center\"><strong>OBSERVACIONES</strong></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td width=30%><strong>CABLE RG06</strong></td>\n" +
                "                        <td width=30%></td>\n" +
                "                        <td width=40%></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td width=30%><strong>CONECTORES CRIMP</strong></td>\n" +
                "                        <td width=30%></td>\n" +
                "                        <td width=40%></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td width=30%><strong>SPLITERS 2W</strong></td>\n" +
                "                        <td width=30%></td>\n" +
                "                        <td width=40%></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td width=30%><strong>SPLITERS 3W</strong></td>\n" +
                "                        <td width=30%></td>\n" +
                "                        <td width=40%></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td width=30%><strong>SPLITER 4W</strong></td>\n" +
                "                        <td width=30%></td>\n" +
                "                        <td width=40%></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td width=30%><strong>UNIONES</strong></td>\n" +
                "                        <td width=30%></td>\n" +
                "                        <td width=40%></td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td></tr>\n" +
                "            <tr><td align=\"center\" style=\"font-size:10px;\">EL SOLICITANTE DECLARA QUE TODOS LOS DATOS SUMINISTRADOS SON CORRECTOS Y AUTORIZA</td></tr>\n" +
                "            <tr><td align=\"center\" style=\"font-size:12px;\">A DV TELEVISION DVTV S.A. A VERIFICARLOS, IGUALMENTE DECLARA QUE HA LEIDO Y ACEPTA LAS CONDICIONES IMPRESAS</td></tr>\n" +
                "            <tr><td align=\"center\" style=\"font-size:12px;\">EN ESTE CONTRATO Y PAGARA AL MOMENTO DE LA FIRMA EL VALOR DETALLADO</td></tr>\n" +
                "            <tr><td>\n" +
                "                <table width=90%>\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" width=34%><img src=\"data:image/png;base64," + firmapng + "\" width=\"200\" height=\"100\"></td>\n" +
                "                        <td align=\"center\" width=33%></td>\n" +
                "                        <td align=\"center\" width=34%><img src=\"data:image/png;base64," + firmauserpng + "\" width=\"200\" height=\"100\"></td>\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" width=34%>EL SUSCRIPTOR</td>\n" +
                "                        <td align=\"center\" width=33%>DPTO.TECNICO</td>\n" +
                "                        <td align=\"center\" width=33%>DV TELEVISION DVTV S.A.</td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td></tr>\n" +
                "        </table>\n" +
                "    </div>\n";

        return ContratoTv;
    }

    private String contratoint(int dia, String mes, int anio, String fecha){

        String firmapng = Base64.encodeToString(agrement.getSing(), Base64.DEFAULT);
        String firmauserpng = Base64.encodeToString(user.getSing(), Base64.DEFAULT);

        String ruc = "0992929170001";
        if(agrement.getCompany_Id() == "1"){
            ruc = "0911591212001";
        }

        String ContratoInternet = "<div class=SaltoDePagina>\n" +
                "   <center><strong>CONTRATO DE ADHESION No ______</strong></center>\n" +
                "	</br>\n" +
                "\t<table width=100% style=\"font-size:12px; font-family: Arial\">\n" +
                "\t\t<tr><td align=\"justify\">En la ciudad de " + agrement.getCanton() +", el d??a de hoy "+ fecha +", comparecen, a la suscripci??n del presente acuerdo, en adelante \"EL CONTRATO\", por una parte, la persona jur??dica, DV TELEVISION DVTV S.A., al que en adelante y para efectos del presente contrato se le denominar?? \"EL PRESTADOR\"; y, por otra parte, el/la se??or (a) " + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + " con C??dula " + partner.getIdentification_id() + " a la que en adelante y para efectos de este convenio se le denominar?? el \"SUSCRIPTOR\", al tenor de las siguientes clausulas:</td></tr>\t\n" +
                "\t\t<tr><td></br></td></tr>\t\t\n" +
                "\t\t<tr><td><strong>PRIMERA: DATOS DE LOS COMPARECIENTES.</strong></td></tr>\n" +
                "\t\t<tr><td>El PRESTADOR declara la siguiente informaci??n:</td></tr>\n" +
                "\t\t<tr width=100%>\n" +
                "\t\t\t<td align=\"center\">\n" +
                "\t\t\t\t<table width=90% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=40%>RAZON SOCIAL</td>\n" +
                "\t\t\t\t\t\t<td width=50%>"+ agrement.getCompany() +"</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=40%>NOMBRE COMERCIAL</td>\n" +
                "\t\t\t\t\t\t<td width=50%></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=40%>DIRECCION</td>\n" +
                "\t\t\t\t\t\t<td width=50%>9 DE OCTUBRE 62 A Y JOSE VELEZ</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=40%>PROVINCIA</td>\n" +
                "\t\t\t\t\t\t<td width=50%>GUAYAS</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=40%>CANTON</td>\n" +
                "\t\t\t\t\t\t<td width=50%>DAULE</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=40%>PARROQUIA</td>\n" +
                "\t\t\t\t\t\t<td width=50%>DAULE</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=40%>RUC</td>\n" +
                "\t\t\t\t\t\t<td width=50%>"+ ruc +"</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=40%>CORREO ELECTRONICO</td>\n" +
                "\t\t\t\t\t\t<td width=50%>servicioalcliente@dvtelevision.com</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=40%>PAGINA WEB</td>\n" +
                "\t\t\t\t\t\t<td width=50%>www.dvtelevision.com</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=40%>TELEFONOS</td>\n" +
                "\t\t\t\t\t\t<td width=50%>042797023-042798122</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td>El SUSCRIPTOR declara la siguiente informaci??n:</td></tr>\n" +
                "\t\t<tr width=100%>\n" +
                "\t\t\t<td align=\"center\">\n" +
                "\t\t\t\t<table width=90% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=40%>NOMBRE/RAZON SOCIAL</td>\n" +
                "\t\t\t\t\t\t<td width=50%>" + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + "</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=40%>CEDULA/RUC</td>\n" +
                "\t\t\t\t\t\t<td width=50%>" + partner.getIdentification_id() + "</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=40%>DIRECCION</td>\n" +
                "\t\t\t\t\t\t<td width=50%>"+ partner.getStreet() +"</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=40%>REFERENCIA</td>\n" +
                "\t\t\t\t\t\t<td width=50%>"+ partner.getStreet2() +"</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=40%>PROVINCIA</td>\n" +
                "\t\t\t\t\t\t<td width=50%>" + partner.getProvincia() + "</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=40%>CANTON</td>\n" +
                "\t\t\t\t\t\t<td width=50%>" + partner.getCanton() + "</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=40%>PARROQUIA</td>\n" +
                "\t\t\t\t\t\t<td width=50%></td>\n" +
                "\t\t\t\t\t</tr>\t\t\t\t\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=40%>CORREO ELECTRONICO</td>\n" +
                "\t\t\t\t\t\t<td width=50%>" + partner.getEmail() + "</td>\n" +
                "\t\t\t\t\t</tr>\t\t\t\t\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=40%>TELEFONOS</td>\n" +
                "\t\t\t\t\t\t<td width=50%>"+ partner.getPhone() + " " + partner.getMobile() + "</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=40%>POSTE O CAJA</td>\n" +
                "\t\t\t\t\t\t<td width=50%> "+ agrement.getNap() + "</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td>Si el abonado es de la tercera edad o discapacitado (caso afirmativo, aplica tarifa preferencial de acuerdo al plan de prestador.</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td><strong>SEGUNDA: OBJETO</strong></td></tr>\n" +
                "\t\t<tr><td>EL PRESTADOR se compromete a proporcionar al ABONADO el/los siguiente (s) SERVICIO (s), para lo cual el PRESTADOR dispone de los correspondientes t??tulos habilitantes otorgados por ARCOTEL, de conformidad con el ordenamiento jur??dico vigente:</td></tr>\n" +
                "\t\t<tr width=100%>\n" +
                "\t\t\t<td align=\"center\">\n" +
                "\t\t\t\t<table width=90% border=\"1\" cellspacing=\"0\" cellpadding=\"1\" style=\"font-size: 10ptx\">\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=65%>???\tM??vil avanzado (SMA)</td>\n" +
                "\t\t\t\t\t\t<td width=15%></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=65%>???\tM??vil avanzado a trav??s de operador m??vil virtual (OMV)</td>\n" +
                "\t\t\t\t\t\t<td width=15%></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=65%>???\tTelefon??a Fija</td>\n" +
                "\t\t\t\t\t\t<td width=15%></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=65%>???\tTelecomunicaciones por sat??lite</td>\n" +
                "\t\t\t\t\t\t<td width=15%></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=65%>???\tValor agregado</td>\n" +
                "\t\t\t\t\t\t<td width=15%></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=65%>???\tAcceso a internet</td>\n" +
                "\t\t\t\t\t\t<td width=15% align=\"center\">X</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=65%>???\tTroncalizados</td>\n" +
                "\t\t\t\t\t\t<td width=15%></td>\n" +
                "\t\t\t\t\t</tr>\t\t\t\t\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=65%>???\tComunales</td>\n" +
                "\t\t\t\t\t\t<td width=15%></td>\n" +
                "\t\t\t\t\t</tr>\t\t\t\t\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=65%>???\tAudio y Video por suscripci??n</td>\n" +
                "\t\t\t\t\t\t<td width=15%></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=65%>???\tPortador</td>\n" +
                "\t\t\t\t\t\t<td width=15%></td>\n" +
                "\t\t\t\t\t</tr>\t\t\t\t\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">Las condiciones del/los servicio (s) que el SUSCRIPTOR va a contratar se encuentran detalladas en el ANEXO A, el cual forma parte integrante del presente contrato.</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td><strong>TERCERA: VIGENCIA DEL CONTRATO.</strong></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">\n" +
                "\t\t\t\tEl presente contrato tendr?? una duraci??n de <span style=\"font-weight:bold;\">1 a??o</span> y entrar?? en vigencia, a partir de la fecha de instalaci??n y prestaci??n efectiva del servicio. La fecha inicial considerada para facturaci??n para cada uno de los servicios contratados ser?? la de activaci??n del servicio.\n" +
                "\t\t\t\tLas partes se comprometen a respetar el plazo de vigencia pactado, sin perjuicio de que el SUSCRIPTOR pueda darlo por terminado unilateralmente, en cualquier tiempo, previa notificaci??n f??sica o electr??nica, con por lo menos quince (15) d??as de anticipaci??n, conforme lo dispone la Ley Org??nica de Telecomunicaciones y de Defensa del consumidor y sin que para ello est?? obligado a cancelar multas o recargos de valores de ninguna naturaleza.\n" +
                "\t\t\t\tEl SUSCRIPTOR acepta la renovaci??n autom??tica sucesiva del contrato en las mismas condiciones de este contrato, independientemente de su derecho a terminar la relaci??n contractual conforme la legislaci??n aplicable, o solicitar en cualquier tiempo, con hasta quince (15) d??as de antelaci??n a la fecha de renovaci??n, su decisi??n de no renovaci??n.\n" +
                "\t\t</td></tr>\n" +
                "\t\t<tr><td align=\"center\">\n" +
                "\t\t\t\t<table width=60% border=\"1\" cellspacing=\"0\" cellpadding=\"1\" style=\"font-size: 10ptx\">\n" +
                "\t\t\t\t\t<tr><td width=25% align=\"center\"><strong>Si</strong></td>\n" +
                "\t\t\t\t\t\t<td width=25% align=\"center\"><strong>X</strong></td>\n" +
                "\t\t\t\t\t\t<td width=25% align=\"center\"><strong>No</strong></td>\n" +
                "\t\t\t\t\t\t<td width=25%></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr><td></br></td></tr>\t\n" +
                "\t\t<tr><td><strong>CUARTA: DOCUMENTOS DEL CONTRATO.</strong></td>\n" +
                "\t\t</tr><tr>\n" +
                "\t\t\t<td align=\"justify\">\n" +
                "\t\t\t\tForman parte integrante y total del presente CONTRATO los siguientes documentos:\n" +
                "\t\t\t\t<br>3.1 El Anexo A \"Condiciones de Contrataci??n\".\n" +
                "\t\t\t\t<br>3.2 El Anexo B \"Compra o arriendo de equipos\".\n" +
                "\t\t\t\t<br>3.3 El Anexo C ???Aceptaci??n de uso de datos personales???\n" +
                "\t\t\t\t<br>3.4 El Anexo D ???Servicios adicionales, suplementarios y promociones???\n" +
                "\t\t\t\t<br>3.5 Las Actas de instalaci??n y activaci??n, de todos los servicios que se presten y que respaldar??n los servicios ofrecidos.\n" +
                "\t\t</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td><strong>QUINTA: PERMANENCIA M??NIMA.</strong></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">El SUSCRIPTOR se acoge al per??odo de permanencia m??nima de ________ en la prestaci??n del servicio contratado.</td></tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td align=\"center\">\n" +
                "\t\t\t\t<table width=60% border=\"1\" cellspacing=\"0\" cellpadding=\"1\" style=\"font-size: 10ptx\">\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=25% align=\"center\"><strong>Si</strong></td>\n" +
                "\t\t\t\t\t\t<td width=25% align=\"center\"><strong>X</strong></td>\n" +
                "\t\t\t\t\t\t<td width=25% align=\"center\"><strong>No</strong></td>\n" +
                "\t\t\t\t\t\t<td width=25% align=\"center\"></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td align=\"justify\">\n" +
                "\t\t\t\tLos beneficios de la permanencia m??nima son:\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td align=\"center\">\n" +
                "\t\t\t\t<table width=60% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=10% align=\"center\"><strong>5.1</strong></td>\n" +
                "\t\t\t\t\t\t<td width=10% >DESCUENTO DEL 80 % EN EL COSTO DE INSTALACION DEL SERVICIO</td>\t\t\t\t\t\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=10% align=\"center\"><strong>5.2</strong></td>\n" +
                "\t\t\t\t\t\t<td width=50%></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=10% align=\"center\"><strong>5.3</strong></td>\n" +
                "\t\t\t\t\t\t<td width=50%></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=10% align=\"center\"><strong>5.4</strong></td>\n" +
                "\t\t\t\t\t\t<td width=50%></td>\t\t\t\t\t\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=10% align=\"center\"><strong>5.5</strong></td>\n" +
                "\t\t\t\t\t\t<td width=50%></td>\t\t\t\t\t\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=10% align=\"center\"><strong>5.6</strong></td>\n" +
                "\t\t\t\t\t\t<td width=50%></td>\t\t\t\t\t\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=10% align=\"center\"><strong>5.7</strong></td>\n" +
                "\t\t\t\t\t\t<td width=50%></td>\t\t\t\t\t\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=10% align=\"center\"><strong>5.8</strong></td>\n" +
                "\t\t\t\t\t\t<td width=50%></td>\t\t\t\t\t\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">La permanencia m??nima se acuerda, sin perjuicio de que el SUSCRIPTOR conforme lo determina la LEY ORG??NICA DE TELECOMUNICACIONES, pueda dar por terminado el contrato en forma unilateral y anticipada, y en cualquier tiempo, previa notificaci??n por medios f??sicos o electr??nicos al PRESTADOR, con por lo menos quince (15) d??as de anticipaci??n, para cuyo efecto deber?? proceder a cancelar los servicios efectivamente prestados o por los bienes solicitados y recibidos, as?? como tambi??n el costo de instalaci??n que faltare hasta la terminaci??n del contrato.</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\t\t\n" +
                "\t\t<tr><td><strong>SEXTA: TARIFA Y FORMA DE PAGO.</strong></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">Las tarifas o valores mensuales a ser cancelados por cada uno de los servicios contratados por el SUSCRIPTOR se describe en la ficha del servicio del ANEXO A y, el pago se realizar??, de la siguiente forma:</td></tr>\n" +
                "\t\t<tr width=100%><td align=\"center\">\n" +
                "\t\t\t<table width=90% border=\"1\" cellspacing=\"0\" cellpadding=\"1\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td width=70%></td>\n" +
                "\t\t\t\t\t<td width=10% align=\"center\"><strong>Si</strong></td>\n" +
                "\t\t\t\t\t<td width=10% align=\"center\"><strong>No</strong></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td width=70%>Pago directo en cajas del prestador del servicio</td>\n" +
                "\t\t\t\t\t<td width=10%><strong></strong></td>\n" +
                "\t\t\t\t\t<td width=10%><strong></strong></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td width=70%>D??bito autom??tico cuenta de ahorro o corriente</td>\n" +
                "\t\t\t\t\t<td width=10%><strong></strong></td>\n" +
                "\t\t\t\t\t<td width=10%><strong></strong></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td width=70%>Pago en ventanillas de locales autorizados</td>\n" +
                "\t\t\t\t\t<td width=10% align=\"center\"><strong><strong>X</strong></strong></td>\n" +
                "\t\t\t\t\t<td width=10%><strong></strong></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td width=70%>Debito con tarjeta de cr??dito</td>\n" +
                "\t\t\t\t\t<td width=10%><strong></strong></td>\n" +
                "\t\t\t\t\t<td width=10%><strong></strong></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td width=70%>Transferencia v??a medios electr??nicos</td>\n" +
                "\t\t\t\t\t<td width=10%><strong></strong></td>\n" +
                "\t\t\t\t\t<td width=10%><strong></strong></td>\n" +
                "\t\t\t\t</tr>\t\t\t\t\n" +
                "\t\t\t</table>\n" +
                "\t\t</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">\n" +
                "\t\t\t\tLa tarifa correspondiente al servicio contratado y efectivamente prestado, estar??n siempre dentro de los techos tarifarios se??alados por la ARCOTEL y en los t??tulos habilitantes correspondientes, en caso de que se establezcan, de conformidad con el ordenamiento jur??dico vigente.\n" +
                "\t\t\t\tEn caso de que el SUSCRIPTOR desee cambiar su modalidad de pago a otra de las disponibles, deber?? comunicar al prestador de servicio con quince (15) d??as de anticipaci??n. El prestador del servicio, luego de haber sido comunicado, instrumentar?? la nueva forma de pago.\n" +
                "\t\t</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td><strong>SEPTIMA: COMPRA, ARRENDAMIENTO DE EQUIPOS.</strong></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">Cuando sea procedente el arrendamiento o adquisici??n de equipos, por parte del abonado, toda la informaci??n pertinente ser?? detallada en un anexo adicional, suscrito por el abonado el cual contendr?? los temas relacionados a las condiciones de los equipos adquiridos/arrendados, entre otras caracter??sticas deber?? incluir: Cantidad, Precio, Marca, Estado, y las condiciones de tal adquisici??n o arrendamiento, particularmente el tiempo en el que se pagara el arrendamiento o la compra del equipo, el valor mensual a cancelar o las condiciones de pago.</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td><strong>OCTAVA: USO DE INFORMACI??N PERSONAL.</strong></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">Los datos personales que los usuarios proporcionen a los prestadores de servicios del r??gimen general de telecomunicaciones, no podr??n ser usados para la promoci??n comercial de servicios o productos, inclusive de la propia operadora; salvo autorizaci??n y consentimiento expreso del abonado/suscriptor, el que constara como instrumento separado y distinto al presente contrato de prestaci??n del servicio (contrato de adhesi??n) a trav??s de medios f??sicos o electr??nicos. En dicho instrumento se deber?? dejar constancia expresa de los datos personales o informaci??n que est??n expresamente autorizados; el plazo de autorizaci??n y el objetivo que esta utilizaci??n persigue, conforme lo dispuesto en el art??culo 121 del reglamento general a la ley org??nica de Telecomunicaciones.</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td><strong>NOVENA: RECLAMO Y SOPORTE T??CNICO.</strong></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">El SUSCRIPTOR puede solicitar soporte t??cnico o la formulaci??n de reclamos por el servicio contratado a trav??s de los siguientes medios:</td></tr>\n" +
                "\t\t<tr><td align=\"center\">\n" +
                "\t\t\t<table width=90% style=\"font-size: 10ptx\">\n" +
                "\t\t\t\t<tr><td>-\tMedio electr??nico: www.dvtelevision.com, servicioalcliente@dvtelevision.com.</td></tr>\n" +
                "\t\t\t\t<tr><td>-\tOficinas de atenci??n a usuarios: 9 DE OCTUBRE 62A Y JOSE VELEZ.</td></tr>\n" +
                "\t\t\t\t<tr><td>-\tHorarios de atenci??n: 09:00 ??? 18:00 LUNES- VIERNES/ 10:00 ??? 14:00 PM SABADOS.</td></tr>\n" +
                "\t\t\t\t<tr><td>-\tTel??fonos: 042797023 042798122 0984929651.</td></tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">Para la atenci??n de reclamos NO resueltos por el PRESTADOR, el SUCRIPTOR tambi??n podr?? presentar sus denuncias y reclamos ante la Agencia de Regulaci??n y Control de las Telecomunicaciones (ARCOTEL) por cualquiera de los siguientes canales de atenci??n:</td></tr>\n" +
                "\t\t<tr><td align=\"center\">\n" +
                "\t\t\t<table width=90% style=\"font-size: 10ptx\">\n" +
                "\t\t\t\t<tr><td>-\tAtenci??n presencial: oficinas del tas coordinaciones zonales de la ARCOTEL</td></tr>\n" +
                "\t\t\t\t<tr><td>-\tPBX Directo Matriz, Coordinaciones Zonales y oficinas t??cnicas.</td></tr>\n" +
                "\t\t\t\t<tr><td>-\tCall Center: llamadas gratuitas al n??mero 1800 567-567.</td></tr>\n" +
                "\t\t\t\t<tr><td>-\tCorreos tradicional (Oficios).</td></tr>\n" +
                "\t\t\t\t<tr><td>-\tP??gina web.\n" +
                "\t\t\t\t\t<br>\t\twww.arcotel.gob.ec\n" +
                "\t\t\t\t\t<br>\t\thttp://reclamoconsumidor.arcotel.gob.ec/osTicket/\n" +
                "\t\t\t\t</td></tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td><strong>D??CIMA: TERMINACI??N DEL CONTRATO.</strong></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">Las partes acuerdan rec??procamente que EL CONTRATO se terminar?? por la ejecuci??n total de las obligaciones derivadas del mismo; por acuerdo mutuo y que conste por escrito, o cuando ocurra alguna de las siguientes causales:</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">12.1 Si cualquiera de las partes no cumple con las obligaciones del presente contrato, como son la calidad y disponibilidad del servicio acordados, el pago del precio convenido, mal uso de los equipos instalados y/o servicios prestados, etc., siempre que la parte responsable no adopte las soluciones pertinentes dentro de los quince (15) d??as h??biles siguientes al recibo de requerimiento escrito por la otra.</td></tr>\n" +
                "\t\t<tr><td align=\"justify\">12.2 Cuando EL SUSCRIPTOR use los servicios y/o los equipos para fines distintos a los contratados, cuando permita el uso de los mismos a terceros y/o cuando explote con ellos servicios de telecomunicaciones ilegales, y/o no autorizados por el Estado ecuatoriano, sin perjuicio de la denuncia del cometimiento de infracciones que se comentan a la Ley Org??nica de Telecomunicaciones y que deban ser sancionadas por ARCOTEL.</td></tr>\n" +
                "\t\t<tr><td align=\"justify\">12.3 En el evento de terminar el Permiso otorgado a EL PRESTADOR por el Estado Ecuatoriano.</td></tr>\n" +
                "\t\t<tr><td align=\"justify\">S?? el Contrato termina anticipadamente por causas atribuibles al SUSCRIPTOR, EL SUSCRIPTOR deber?? pagar a EL PRESTADOR los servicios efectivamente prestados o los bienes solicitados y recibidos, hasta la terminaci??n del contrato.</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td><strong>D??CIMA PRIMERA: CONFIDENCIALIDAD Y PROPIEDAD INTELECTUAL.</strong></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">\n" +
                "\t\t\t\tEL PRESTADOR declara que sus invenciones, ideas, conceptos, secretos comerciales, informaci??n confidencial o informaci??n no divulgada y cualquier asunto relacionado con la Propiedad intelectual (registrada o no registrada), son solamente de su propiedad. EL PRESTADOR acuerda compartir y discutir ciertas partes de sus invenciones, ideas, conceptos, secretos comerciales, informaci??n confidencial o informaci??n no divulgada y cualquier asunto relacionado con la Propiedad intelectual (registrada o no registrada) de acuerdo a las necesidades del contrato de prestaci??n de servicios y otros documentos integrantes del mismo con el SUSCRIPTOR, sin que esto signifique de ninguna manera una cesi??n, transferencia, licencia de uso o cualquier otra forma por la que EL PRESTADOR ceda o transfiera cualquiera de sus modalidades de la Propiedad intelectual (registrada o no registrada) a favor del SUSCRIPTOR.\n" +
                "\t\t\t\tTanto EL PRESTADOR como el SUSCRIPTOR guardar??n estricta confidencialidad sobre el contenido del presente CONTRATO. Es obligaci??n de las mismas informar a sus funcionarios, empleados, colaboradores, subcontratistas y/o terceros relacionados con el presente CONTRATO, la obligaci??n de mantener la reserva sobre el contenido y condiciones del mismo; sobre todo de aquellos documentos y/o informaci??n que sean calificados como confidenciales.\n" +
                "\t\t</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td><strong>D??CIMA SEGUNDA: CONTROVERSIAS.</strong></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">\n" +
                "\t\t\t\tLas diferencias que surjan de la ejecuci??n del presente contrato, podr??n ser resueltas por mutuo acuerdo entre las partes, sin perjuicio de que el ABONADO O SUSCRIPTOR acuda con su reclamo, queja o denuncia, ante las autoridades administrativas que corresponda. De no llegar a una soluci??n, cualquiera de las partes podr?? acudir ante los jueces competentes.\n" +
                "\t\t</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">\n" +
                "\t\t\t\tNo obstante lo indicado, las partes pueden pactar adicionalmente, someter sus controversias ante un centro de mediaci??n o arbitraje, si as?? lo deciden expresamente, en cuyo caso el abonado/suscriptor deber?? se??alarlo en forma expresa.\n" +
                "\t\t</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">\n" +
                "\t\t\t\tEL ABONADO, en caso de conflicto, acepta someterse a la mediaci??n o arbitraje (puede significar costos en los que debe incurrir el ABONADO/SUSCRIPTOR- no aplica a empresas publicas prestadoras de servicios de telecomunicaciones)\n" +
                "\t\t</td></tr>\n" +
                "\t\t<tr><td align=\"center\">\n" +
                "\t\t\t<table width=60% border=\"1\" cellspacing=\"0\" cellpadding=\"1\" style=\"font-size: 10ptx\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td width=25% align=\"center\"><strong>Si</strong></td>\n" +
                "\t\t\t\t\t<td width=25% align=\"center\"><strong>X</strong></td>\n" +
                "\t\t\t\t\t<td width=25% align=\"center\"><strong>No</strong></td>\n" +
                "\t\t\t\t\t<td width=25% align=\"center\"></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t</td></tr>\n" +
                "\t\t<tr><td align=\"center\"><img src=\"data:image/png;base64," + firmapng + "\" width=\"200\" height=\"100\"></td></tr>\n" +
                "\t\t<tr><td align=\"center\">????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????</td></tr>\n" +
                "\t\t<tr><td align=\"center\">Firma de aceptaci??n de sujeci??n a arbitraje</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td><strong>DECIMA TERCERA: NOTIFICACI??N Y DOMICILIO.</strong></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">Las notificaciones que corresponda, ser??n entregadas en el domicilio de cada una de las partes se??alado en la cl??usula PRIMERA del presente contrato. Cualquier cambio de domicilio debe ser comunicado por escrito a la otra parte en un plazo de 10 d??as, a partir del d??a siguiente en que el cambio se efect??e.</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td><strong>DECIMA CUARTA: EMPAQUETAMIENTO DE SERVICIOS.</strong></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">La contrataci??n incluye empaquetamiento de servicios.</td></tr>\n" +
                "\t\t<tr><td align=\"center\">\n" +
                "\t\t\t<table width=60% border=\"1\" cellspacing=\"0\" cellpadding=\"1\" style=\"font-size: 10ptx\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td width=25% align=\"center\"><strong>Si</strong></td>\n" +
                "\t\t\t\t\t<td width=25%></td>\n" +
                "\t\t\t\t\t<td width=25% align=\"center\"><strong>No</strong></td>\n" +
                "\t\t\t\t\t<td width=25% align=\"center\"><strong>X</strong></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">Los paquetes de servicios y los beneficios de estos, as?? como sus tarifas son:</td></tr>\n" +
                "\t\t<tr width=100%><td align=\"center\">\n" +
                "\t\t\t<table width=90% border=\"1\" cellspacing=\"0\" cellpadding=\"1\" style=\"font-size: 10ptx\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td width=25% align=\"center\"><strong>Paquete</strong> </td>\n" +
                "\t\t\t\t\t<td width=30% align=\"center\"><strong>Beneficios</strong></td>\n" +
                "\t\t\t\t\t<td width=25% align=\"center\"><strong>Tarifa</strong></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td width=25%>.</td>\n" +
                "\t\t\t\t\t<td width=30%></td>\n" +
                "\t\t\t\t\t<td width=25%></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td width=25%>.</td>\n" +
                "\t\t\t\t\t<td width=30%></td>\n" +
                "\t\t\t\t\t<td width=25%></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td width=25%>.</td>\n" +
                "\t\t\t\t\t<td width=30%></td>\n" +
                "\t\t\t\t\t<td width=25%></td>\n" +
                "\t\t\t\t</tr>\t\t\t\t\n" +
                "\t\t\t</table>\n" +
                "\t\t</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td><strong>DECIMA QUINTA: ANEXOS.</strong></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">Es parte integrante del presente contrato el anexo 1 que contiene las ????condiciones particulares del servicio????, as?? como los dem??s anexos y documentos que se incorporen de conformidad con el ordenamiento jur??dico.</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">El abonado acepta el presente contrato con sus t??rminos y condiciones y dem??s documentos ANEXOS para lo cual deja constancia de lo anterior y firman junto con el PRESTADOR del mismo tenor, en la ciudad de: " + agrement.getCanton() + " a los " + dia + " d??as del mes " + mes + " del a??o "+ anio +".</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td>\n" +
                "\t\t\t<table width=90% cellspacing=\"0\" cellpadding=\"1\" style=\"font-size: 10ptx\">\n" +
                "\t\t\t\t<td>\n" +
                "\t\t\t\t\t<td width=50% align=\"center\"></td>\n" +
                "\t\t\t\t\t<td width=50% align=\"center\"></td>\n" +
                "\t\t\t\t</td>\n" +
                "\t\t\t</table>\n" +
                "\t\t</td></tr>\n" +
                "\t\t<tr><td>\n" +
                "\t\t\t<table width=90% cellspacing=\"0\" cellpadding=\"1\" style=\"font-size: 10ptx\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td width=50% align=\"center\"><img src=\"data:image/png;base64," + firmapng + "\" width=\"200\" height=\"100\"></td>\n" +
                "\t\t\t\t\t<td width=50% align=\"center\"><img src=\"data:image/png;base64," + firmauserpng + "\" width=\"200\" height=\"100\"></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td width=50% align=\"center\">_____________________________</td>\n" +
                "\t\t\t\t\t<td width=50% align=\"center\">_____________________________</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t</td></tr>\n" +
                "\t\t<tr><td>\n" +
                "\t\t\t<table width=90% cellspacing=\"0\" cellpadding=\"1\" style=\"font-size: 10ptx\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td width=50% align=\"center\">CC: "+ partner.getIdentification_id() + "</td>\n" +
                "\t\t\t\t\t<td width=50% align=\"center\">CC: "+ user.getIdentificacion() + "</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td width=50% align=\"center\">SUSCRIPTOR</td>\n" +
                "\t\t\t\t\t<td width=50% align=\"center\">PRESTADOR</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t</td></tr>\n" +
                "\t</table>\n" +
                "</div>";

        return ContratoInternet;
    }

    private String anexoa(int dia, String mes, int anio){

        String firmapng = Base64.encodeToString(agrement.getSing(), Base64.DEFAULT);
        String firmauserpng = Base64.encodeToString(user.getSing(), Base64.DEFAULT);

        String anexoa = "<div class=SaltoDePagina>\n" +
                "    <table width=100% style=\"font-size:12px; font-family: Arial\" border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "        <tr><td align=\"center\" >ANEXO A</td></tr>\n" +
                "        <tr><td align=\"center\">SERVICIO DE ACCESO A INTERNET</td></tr>        \n" +
                "    </table>\n" +
                "    <table width=100%>        \n" +
                "        <tr><td></br></td></tr>\n" +
                "    </table>\n" +
                "    <table width=100%> \n" +
                "        <tr><td>      \n" +
                "            <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                <tr><td >Fecha de Suscripci??n del Anexo: " + dia + " de "+ mes + " de " + anio + "</td></tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "        <tr><td></td></tr>\n" +
                "        <tr><td>Nombre del Plan:</td></tr>\n" +
                "        <tr><td>\n" +
                "            <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                <tr><td >" + agrement.getTipoServicio() + " " +  agrement.getPlan() + "</td></tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "        <tr><td></td></tr>\n" +
                "        <tr><td>Red de acceso:</td></tr>\n" +
                "        <tr><td>      \n" +
                "            <table width=100%>\n" +
                "                <tr><td width=45%>\n" +
                "                    <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                        <tr>\n" +
                "                            <td width=80%>Par de cobre</td>\n" +
                "                            <td width=20%></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=80%>Coaxial</td>\n" +
                "                            <td width=20%></td>\n" +
                "                        </tr>\n" +
                "                        <tr>                                \n" +
                "                            <td width=80%>Otros</td>\n" +
                "                            <td width=20%></td>\n" +
                "                        </tr>\n" +
                "                    </table>\n" +
                "                </td>\n" +
                "                <td width=10%></td>\n" +
                "                <td width=45%>\n" +
                "                    <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                        <tr>\n" +
                "                            <td width=80%>Fibra ??ptica</td>\n" +
                "                            <td width=20% align=\"center\"><strong>X</strong></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=80%>Inal??mbrico</td>\n" +
                "                            <td width=20%></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=80%>.</td>\n" +
                "                            <td width=20%></td>\n" +
                "                        </tr>\n" +
                "                    </table>\n" +
                "                </td></tr>                \n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "        <tr><td></td></tr>\n" +
                "        <tr><td>Tipo de cuenta:</td></tr>\n" +
                "        <tr><td>      \n" +
                "            <table width=100%>\n" +
                "                <tr><td width=45%>\n" +
                "                    <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                        <tr>\n" +
                "                            <td width=80%>Residencial</td>\n" +
                "                            <td width=20%></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=80%>Cibercaf??</td>\n" +
                "                            <td width=20%></td>\n" +
                "                        </tr>                            \n" +
                "                    </table>\n" +
                "                </td>\n" +
                "                <td width=10%></td>\n" +
                "                <td width=45%>\n" +
                "                    <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                        <tr>\n" +
                "                            <td width=80%>Corporativo</td>\n" +
                "                            <td width=20%></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=80%>Otro tipo</td>\n" +
                "                            <td width=20%></td>\n" +
                "                        </tr>\n" +
                "                    </table>\n" +
                "                </td></tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "        <tr><td></td></tr>\n" +
                "        <tr><td>Velocidad (kbps):</td></tr>\n" +
                "        <tr><td>\n" +
                "            <table width=100%>\n" +
                "                <tr><td width=45%>\n" +
                "                    <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                        <tr>\n" +
                "                            <td width=80%>Comercial de bajada</td>\n" +
                "                            <td width=20%></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=80%>M??nima efectiva de bajada</td>\n" +
                "                            <td width=20%></td>\n" +
                "                        </tr>                            \n" +
                "                    </table>\n" +
                "                </td>\n" +
                "                <td width=10%></td>\n" +
                "                <td width=45%>\n" +
                "                    <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                        <tr>\n" +
                "                            <td width=80%>Comercial de subida</td>\n" +
                "                            <td width=20%></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=80%>M??nima efectiva de subida</td>\n" +
                "                            <td width=20%></td>\n" +
                "                        </tr>                            \n" +
                "                    </table>\n" +
                "                </td></tr>                \n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "        <tr><td></td></tr>\n" +
                "        <tr><td>      \n" +
                "            <table width=100%>\n" +
                "                <tr><td width=45%>\n" +
                "                    <table width=100% >\n" +
                "                        <tr><td>Nivel de compartici??n (1:1, 2:1, 4:1, 8:4)</td></tr>                        \n" +
                "                    </table>\n" +
                "                </td>\n" +
                "                <td width=10%></td>\n" +
                "                <td width=45%>\n" +
                "                    <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                        <tr><td>.</td></tr>                        \n" +
                "                    </table>\n" +
                "                </td></tr>                \n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "        <tr><td></td></tr>\n" +
                "        <tr><td>\n" +
                "            <table width=100%>\n" +
                "                <tr><td width=40%>\n" +
                "                    <table width=100%>\n" +
                "                        <tr><td>El contrato incluye permanencia m??nima:</td></tr>\n" +
                "                    </table>\n" +
                "                    </td>\n" +
                "                    <td width=20%>\n" +
                "                        <table width=100%>\n" +
                "                            <tr><td width=50%>\n" +
                "                                <table width=100%>\n" +
                "                                    <tr><td align=\"center\">SI</td></tr>\n" +
                "                                </table>\n" +
                "                                </td>\n" +
                "                                <td width=50%>\n" +
                "                                <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                    <tr><td align=\"center\">X</td></tr>\n" +
                "                                </table>\n" +
                "                            </td></tr>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                    <td width=20%>\n" +
                "                        <table width=100%>\n" +
                "                            <tr><td width=50%>\n" +
                "                                <table width=100%>\n" +
                "                                    <tr><td align=\"center\">NO</td></tr>\n" +
                "                                </table>\n" +
                "                                </td>\n" +
                "                                <td width=50%>\n" +
                "                                <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                    <tr><td>.</td></tr>\n" +
                "                                </table>\n" +
                "                            </td></tr>\n" +
                "                        </table>                        \n" +
                "                    </td>\n" +
                "                    <td width=20%>\n" +
                "                        <table width=100%>\n" +
                "                            <tr><td width=50%>\n" +
                "                                <table width=100%>\n" +
                "                                    <tr><td align=\"center\">TIEMPO</td></tr>\n" +
                "                                </table>\n" +
                "                                </td>\n" +
                "                                <td width=50%>\n" +
                "                                <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                    <tr><td align=\"center\">1 A??O</td></tr>\n" +
                "                                </table>\n" +
                "                            </td></tr>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "        <tr><td></td></tr>\n" +
                "        <tr><td>      \n" +
                "            <table width=100%>\n" +
                "                <tr><td width=45%>\n" +
                "                    <table width=100% >\n" +
                "                        <tr><td>Beneficios por permanencia m??nima</td></tr>                        \n" +
                "                        <tr><td>DESCUENTO 80% COSTO DE INSTALACION</td></tr>                        \n" +
                "                    </table>\n" +
                "                </td>\n" +
                "                <td width=10%></td>\n" +
                "                <td width=45%>\n" +
                "                    <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                        <tr><td>.</td></tr>\n" +
                "                        <tr><td>.</td></tr>\n" +
                "                        <tr><td>.</td></tr>\n" +
                "                        <tr><td>.</td></tr>\n" +
                "                    </table>\n" +
                "                </td></tr>                \n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "        <tr><td></td></tr>        \n" +
                "        <tr><td>Servicios adicionales que se ofrece:</td></tr>\n" +
                "        <tr><td></td></tr>        \n" +
                "        <tr><td>      \n" +
                "            <table width=100%>\n" +
                "                <tr><td width=40%>\n" +
                "                    <table width=100% >\n" +
                "                        <tr><td>.</td></tr>\n" +
                "                    </table>\n" +
                "                </td>                \n" +
                "                <td width=60%>\n" +
                "                    <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                        <td width=25%>SI</td>\n" +
                "                        <td width=25%>NO</td>\n" +
                "                        <td width=50%>OBSERVACIONES</td>\n" +
                "                    </table>\n" +
                "                </td></tr>                \n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "        <tr><td>      \n" +
                "            <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                <tr>\n" +
                "                    <td width=40%>Cuentas de correo electr??nico</td>\n" +
                "                    <td width=15%></td>\n" +
                "                    <td width=15%></td>\n" +
                "                    <td width=30%></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td width=40%>.</td>\n" +
                "                    <td width=15%></td>\n" +
                "                    <td width=15%></td>\n" +
                "                    <td width=30%></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td width=40%>.</td>\n" +
                "                    <td width=15%></td>\n" +
                "                    <td width=15%></td>\n" +
                "                    <td width=30%></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td width=40%>.</td>\n" +
                "                    <td width=15%></td>\n" +
                "                    <td width=15%></td>\n" +
                "                    <td width=30%></td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "<tr><td>Tarifas (*)</td></tr>\n" +
                "        <tr><td>Valores a pagar por una sola vez</td></tr>\n" +
                "        <tr><td>\n" +
                "            <table width=100%>\n" +
                "                <tr><td width=45%>\n" +
                "                        <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                            <tr><td align=\"right\">Valor Instalaci??n / Configuraci??n</td></tr>\n" +
                "                            <tr><td align=\"right\">Plazo para instalar/activar el servicio (horas, d??as)</td></tr>\n" +
                "                            <tr><td align=\"right\">Valor Reactivaci??n del servicio</td></tr>\n" +
                "                        </table>\n" +
                "                    </td> \n" +
                "                    <td width=10%></td>\n" +
                "                    <td width=25%>\n" +
                "                        <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                            <tr><td>.</td></tr>\n" +
                "                            <tr><td>.</td></tr>\n" +
                "                            <tr><td align=\"center\">3</td></tr>                        \n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                    <td width=10%>\n" +
                "                        <table width=100%>\n" +
                "                            <tr><td>USD</td></tr>\n" +
                "                            <tr><td>.</td></tr>\n" +
                "                            <tr><td>USD</td></tr>\n" +
                "                        </table>\n" +
                "                </td></tr>\n" +
                "            </table>\n" +
                "        </tr></td>\n" +
                "        <tr><td width=100%>\n" +
                "            <table width=100%>\n" +
                "                <tr>\n" +
                "                    <td width=45%>Valores pago mensual:</td>\n" +
                "                    <td width=10%></td>\n" +
                "                    <td width=45%>Detalle de otros valores:</td>\n" +
                "                </tr>\n" +
                "                <tr><td width=45%>\n" +
                "                        <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                            <tr>\n" +
                "                                <td align=\"right\" width=50%>.</td>\n" +
                "                                <td align=\"center\" width=50%>Valor (USD)</td>                        \n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td align=\"right\" width=50%>Valor mensual</td>\n" +
                "                                <td align=\"right\" width=50%>.</td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td align=\"right\" width=50%>Otros valores</td>\n" +
                "                                <td align=\"right\" width=50%>.</td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td align=\"right\" width=50%>Valor total</td>\n" +
                "                                <td align=\"right\" width=50%>.</td>\n" +
                "                            </tr>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                    <td width=10%></td>\n" +
                "                    <td width=45%>\n" +
                "                        <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                            <tr>\n" +
                "                                <td align=\"center\" width=50%>??tem</td>\n" +
                "                                <td align=\"center\" width=50%>Valor (USD)</td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td align=\"right\" width=50%>.</td>\n" +
                "                                <td align=\"right\" width=50%>.</td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td align=\"right\" width=50%>.</td>\n" +
                "                                <td align=\"right\" width=50%>.</td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td align=\"right\" width=50%>Total otros valores:</td>\n" +
                "                                <td align=\"right\" width=50%>.</td>\n" +
                "                            </tr>\n" +
                "                        </table>\n" +
                "                </td></tr>\n" +
                "            </table>\n" +
                "        </tr></td>        \n" +
                "        <tr><td>\n" +
                "            <table width=100%>\n" +
                "                <tr>\n" +
                "                    <td width=45%>\n" +
                "                        <table width=100%>\n" +
                "                            <tr><td>Sitio web para consulta de tarifas:</td></tr>\n" +
                "                            <tr><td>Sitio web para consulta de indicadores de calidad:</td></tr>                            \n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                    <td width=10%></td>\n" +
                "                    <td width=45%>\n" +
                "                        <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                            <tr><td align=\"center\">www.dvtelevision.com/tarifas</td></tr>\n" +
                "                            <tr><td align=\"center\">www.dvtelevision.com/calidad</td></tr>                            \n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </tr></td>\n" +
                "        <tr><td></td></tr>\n" +
                "        <tr><td>Notas</td></tr>\n" +
                "        <tr><td>* Las tarifas incluyen impuestos de Ley</td></tr>\n" +
                "        <tr><td>\n" +
                "            <table width=100%>\n" +
                "                <tr>\n" +
                "                    <td width=50% align=\"center\"><img src=\"data:image/png;base64," + firmauserpng + "\" width=\"200\" height=\"100\"></td>\n" +
                "                    <td width=50% align=\"center\"><img src=\"data:image/png;base64," + firmapng + "\" width=\"200\" height=\"100\"></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td width=50% align=\"center\">PRESTADOR</td>\n" +
                "                    <td width=50% align=\"center\">SUSCRIPTOR</td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "    </table>\n" +
                "</div>";

        return anexoa;
    }

    private String anexob(int dia, String mes, int anio){
        String firmapng = Base64.encodeToString(agrement.getSing(), Base64.DEFAULT);
        String firmauserpng = Base64.encodeToString(user.getSing(), Base64.DEFAULT);

        String anexob = "<div class=SaltoDePagina>\n" +
                "    <table width=100% style=\"font-size:13px; font-family: Arial\" border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "        <tr><td align=\"center\" ><strong>ANEXO B</strong></td></tr>\n" +
                "        <tr><td align=\"center\"><strong>COMPRA, PR??STAMOS O ARRIENDO DE EQUIPOS</strong></td></tr>        \n" +
                "    </table>\n" +
                "    <table width=100% style=\"font-size:12px; font-family: Arial\"> \n" +
                "        <tr><td></td></tr>\n" +
                "        <tr><td>\n" +
                "            <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                <tr><td >Fecha de Suscripci??n del Anexo:" + dia + " de " + mes + " de " + anio + "</td></tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "        <tr><td></br></td></tr>\n" +
                "        <tr><td>Equipos provistos por el PRESTADOR necesarios para la prestaci??n del servicio:</td></tr>\n" +
                "        <tr><td>\n" +
                "            <table width=100%>\n" +
                "                <tr>\n" +
                "                    <td width=30%>Se incluye equipos de propiedad del PRESTADOR:</td>\n" +
                "                    <td width=70%>\n" +
                "                        <table width=100%>\n" +
                "                            <tr>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100%>\n" +
                "                                        <td align=\"center\"><strong>SI</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                        <td align=\"center\"><strong>X</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100%>\n" +
                "                                        <td align=\"center\"><strong>NO</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                        <td align=\"center\"><strong>.</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "        <td><tr>\n" +
                "            <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=25% >DESCRIPCI??N</td>\n" +
                "                    <td align=\"center\" width=25% >MARCA/MODELO</td>\n" +
                "                    <td align=\"center\" width=25% >ESTADO</td>\n" +
                "                    <td align=\"center\" width=25% >VALOR COMERCIAL</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=25% >.</td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=25% >.</td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=25% >.</td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=25% >.</td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=25% >.</td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "        <td><tr>\n" +
                "            <table width=100%>\n" +
                "                <tr>\n" +
                "                    <td width=30%>Exoneraci??n del pago de instalaci??n:</td>\n" +
                "                    <td width=70%>\n" +
                "                        <table width=100%>\n" +
                "                            <tr>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100%>\n" +
                "                                        <td align=\"center\"><strong>SI</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                        <td><strong>.</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100%>\n" +
                "                                        <td align=\"center\"><strong>NO</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                        <td><strong>.</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "        <td><tr>\n" +
                "            <table width=100%>\n" +
                "                <tr>\n" +
                "                    <td width=30%>Valor de instalaci??n:</td>\n" +
                "                    <td width=70%>\n" +
                "                        <table width=100%>\n" +
                "                            <tr>\n" +
                "                                <td width=50%>\n" +
                "                                    <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                        <td><strong>.</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td width=50%>\n" +
                "                                    <table width=100%>\n" +
                "                                        <td><strong>USD</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "        <td><tr>El SUSCRIPTOR acepta la permanencia m??nima de ?????????????????? Meses por la exoneraci??n del valor de instalaci??n y en el caso de dar por terminado anticipadamente el contrato,</td></tr>\n" +
                "        <td><tr>\n" +
                "            <table width=100%>\n" +
                "                <tr>\n" +
                "                    <td width=30%>Aceptaci??n</td>\n" +
                "                    <td width=70%>\n" +
                "                        <table width=100%>\n" +
                "                            <tr>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100%>\n" +
                "                                        <td align=\"center\"><strong>SI</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                        <td><strong>.</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100%>\n" +
                "                                        <td align=\"center\"><strong>NO</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                        <td><strong>.</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "        <td><tr>Equipos arrendados por el PRESTADOR al SUSCRIPTOR que no son indispensables para prestar el servicio:</td></tr>\n" +
                "        <td><tr>\n" +
                "            <table width=100%>\n" +
                "                <tr>\n" +
                "                    <td width=30%>Se incluye equipos en alquiler:</td>\n" +
                "                    <td width=70%>\n" +
                "                        <table width=100%>\n" +
                "                            <tr>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100%>\n" +
                "                                        <td align=\"center\"><strong>SI</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                        <td><strong>.</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100%>\n" +
                "                                        <td align=\"center\"><strong>NO</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                        <td><strong>.</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "        <td><tr>\n" +
                "            <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=25% >DESCRIPCI??N</td>\n" +
                "                    <td align=\"center\" width=25% >MARCA/MODELO</td>\n" +
                "                    <td align=\"center\" width=25% >ESTADO</td>\n" +
                "                    <td align=\"center\" width=25% >VALOR MENSUAL DEL ALQUILER</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=25% >.</td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=25% >.</td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=25% >.</td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=25% >.</td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=25% >.</td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                    <td align=\"center\" width=25% ></td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "    </td></tr>\n" +
                "    <td><tr></br></td></tr>\n" +
                "    <td><tr>Equipos vendidos por el PRESTADOR al SUSCRIPTOR que no son indispensables para prestar el servicio:</td></tr>\n" +
                "    <td><tr>\n" +
                "        <table width=100%>\n" +
                "            <tr>\n" +
                "                <td width=30%>Se incluye equipos en venta:</td>\n" +
                "                <td width=70%>\n" +
                "                    <table width=100%>\n" +
                "                        <tr>\n" +
                "                            <td width=25%>\n" +
                "                                <table width=100%>\n" +
                "                                    <td align=\"center\"><strong>SI</strong></td>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                            <td width=25%>\n" +
                "                                <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                    <td><strong>.</strong></td>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                            <td width=25%>\n" +
                "                                <table width=100%>\n" +
                "                                    <td align=\"center\"><strong>NO</strong></td>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                            <td width=25%>\n" +
                "                                <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                    <td><strong>.</strong></td>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                    </table>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "    </td></tr>\n" +
                "    <td><tr>\n" +
                "        <table width=100%>\n" +
                "            <tr>\n" +
                "                <td width=30%>Tipo de venta</td>\n" +
                "                <td width=70%>\n" +
                "                    <table width=100%>\n" +
                "                        <tr>\n" +
                "                            <td width=16.66%>\n" +
                "                                <table width=100%>\n" +
                "                                    <td align=\"center\"><strong>CONTADO</strong></td>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                            <td width=16.66%>\n" +
                "                                <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                    <td><strong>.</strong></td>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                            <td width=16.66%>\n" +
                "                                <table width=100%>\n" +
                "                                    <td align=\"center\"><strong>PLAZOS</strong></td>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                            <td width=16.66%>\n" +
                "                                <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                    <td><strong>.</strong></td>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                            <td width=16.66%>\n" +
                "                                <table width=100%>\n" +
                "                                    <td align=\"center\"><strong>MESES</strong></td>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                            <td width=16.66%>\n" +
                "                                <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                    <td><strong>.</strong></td>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                    </table>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "    </td></tr>\n" +
                "    <td><tr>\n" +
                "        <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "            <tr>\n" +
                "                <td align=\"center\" width=25% >DESCRIPCI??N</td>\n" +
                "                <td align=\"center\" width=25% >MARCA/MODELO</td>\n" +
                "                <td align=\"center\" width=25% >ESTADO</td>\n" +
                "                <td align=\"center\" width=25% >VALOR DE CUOTA MENSUAL</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td align=\"center\" width=25% >.</td>\n" +
                "                <td align=\"center\" width=25% ></td>\n" +
                "                <td align=\"center\" width=25% ></td>\n" +
                "                <td align=\"center\" width=25% ></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td align=\"center\" width=25% >.</td>\n" +
                "                <td align=\"center\" width=25% ></td>\n" +
                "                <td align=\"center\" width=25% ></td>\n" +
                "                <td align=\"center\" width=25% ></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td align=\"center\" width=25% >.</td>\n" +
                "                <td align=\"center\" width=25% ></td>\n" +
                "                <td align=\"center\" width=25% ></td>\n" +
                "                <td align=\"center\" width=25% ></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td align=\"center\" width=25% >.</td>\n" +
                "                <td align=\"center\" width=25% ></td>\n" +
                "                <td align=\"center\" width=25% ></td>\n" +
                "                <td align=\"center\" width=25% ></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td align=\"center\" width=25% >.</td>\n" +
                "                <td align=\"center\" width=25% ></td>\n" +
                "                <td align=\"center\" width=25% ></td>\n" +
                "                <td align=\"center\" width=25% ></td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "    </td></tr>\n" +
                "</td></tr>\n" +
                "<td><tr></br></td></tr>\n" +
                "<td><tr>En caso de terminaci??n anticipada del contrato por parte del SUSCRIPTOR, este acepta realizar el pago del restante adeudado por concepto de venta de equipos</td></tr>\n" +
                "<td><tr>\n" +
                "    <table width=100%>\n" +
                "        <tr>\n" +
                "            <td width=30%>Aceptaci??n</td>\n" +
                "            <td width=70%>\n" +
                "                <table width=100%>\n" +
                "                    <tr>\n" +
                "                        <td width=25%>\n" +
                "                            <table width=100%>\n" +
                "                                <td align=\"center\"><strong>SI</strong></td>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                        <td width=25%>\n" +
                "                            <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                <td><strong>.</strong></td>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                        <td width=25%>\n" +
                "                            <table width=100%>\n" +
                "                                <td align=\"center\"><strong>NO</strong></td>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                        <td width=25%>\n" +
                "                            <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                <td><strong>.</strong></td>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</td></tr>\n" +
                "<tr><td></td></tr>\n" +
                "        <tr><td>Notas</td></tr>\n" +
                "        <tr><td>* Las tarifas incluyen impuestos de Ley</td></tr>\n" +
                "        <tr><td>\n" +
                "            <table width=100%>\n" +
                "                <tr>\n" +
                "                    <td width=50% align=\"center\"><img src=\"data:image/png;base64," + firmauserpng + "\" width=\"200\" height=\"100\"></td>\n" +
                "                    <td width=50% align=\"center\"><img src=\"data:image/png;base64," + firmapng + "\" width=\"200\" height=\"100\"></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td width=50% align=\"center\">PRESTADOR</td>\n" +
                "                    <td width=50% align=\"center\">SUSCRIPTOR</td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "    </table>\n" +
                "</div>";


        return anexob;
    }

    private String anexoc(int dia, String mes, int anio) {
        String firmapng = Base64.encodeToString(agrement.getSing(), Base64.DEFAULT);

        String anexoc = "<div class=SaltoDePagina>\n" +
                "    <table width=100% style=\"font-size:13px; font-family: Arial\" border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "        <tr><td align=\"center\" ><strong>ANEXO C</strong></td></tr>\n" +
                "        <tr><td align=\"center\"><strong>ACEPTACI??N DE USO DE DATOS PERSONALES</strong></td></tr>        \n" +
                "    </table>\n" +
                "    <table width=100% style=\"font-size:12px; font-family: Arial\"> \n" +
                "        <tr><td></td></tr>\n" +
                "        <tr><td>      \n" +
                "            <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                <tr><td >Fecha de Suscripci??n del Anexo:" + dia + " de " + mes + " de " + anio + "</td></tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "        <tr><td></br></td></tr>\n" +
                "        <tr><td>El SUSCRIPTOR autoriza expresamente al PRESTADOR a hacer uso de su informaci??n personal y de contacto para fines de ??ndole comercial propios de la empresa prestadora y que consiste en la difusi??n de mensajes publicitarios y comerciales relativos a servicios de telecomunicaciones adicionales que el PRESTADOR ofrece a sus clientes.</td></tr>\n" +
                "        <tr><td></br></td></tr>\n" +
                "        <tr><td>En cualaquier momento el abonado o suscriptor podra revocar  su consentimiento, sin que el prestador pueda condicionar o establecer requesitos para tal fin, adicionales a simple voluntad del abonado, suscriptor o cliente.</td></tr>\n" +
                "        <td><tr>\n" +
                "            <table width=100%>\n" +
                "                <tr>\n" +
                "                    <td width=30%>Aceptaci??n</td>\n" +
                "                    <td width=70%>\n" +
                "                        <table width=100%>\n" +
                "                            <tr>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100%>\n" +
                "                                        <td align=\"center\"><strong>SI</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                        <td align=\"center\"><strong>X</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100%>\n" +
                "                                        <td align=\"center\"><strong>NO</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                        <td><strong>.</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "        <tr><td>\n" +
                "            <table width=100%>\n" +
                "                <tr>\n" +
                "                    <td width=50% align=\"center\"><img src=\"data:image/png;base64," + firmapng + "\" width=\"200\" height=\"100\"></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\">____________________________________</td>                    \n" +
                "                </tr>\n" +
                "                <tr>                    \n" +
                "                    <td align=\"center\">SUSCRIPTOR</td>\n" +
                "                </tr>\n" +
                "                <tr>                    \n" +
                "                    <td align=\"center\"></td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "    </table>\n" +
                "</div>";

        return anexoc;

    }

    private String anexod(int dia, String mes, int anio){
        String firmapng = Base64.encodeToString(agrement.getSing(), Base64.DEFAULT);
        String firmauserpng = Base64.encodeToString(user.getSing(), Base64.DEFAULT);

        String anexod = "<div class=SaltoDePagina>\n" +
                "    <table width=100% style=\"font-size:13px; font-family: Arial\" border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "        <tr><td align=\"center\" ><strong>ANEXO D</strong></td></tr>\n" +
                "        <tr><td align=\"center\"><strong>SERVICIOS ADICIONALES, SUPLEMENTARIOS Y PROMOCIONES</strong></td></tr>        \n" +
                "    </table>\n" +
                "    <table width=100% style=\"font-size:12px; font-family: Arial\"> \n" +
                "        <tr><td></td></tr>\n" +
                "        <tr><td>      \n" +
                "            <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                <tr><td >Fecha de Suscripci??n del Anexo:" + dia + " de " + mes + " de " + anio + "</td></tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "        <tr><td></br></td></tr>\n" +
                "        <td><tr>\n" +
                "            <table width=100%>\n" +
                "                <tr>\n" +
                "                    <td width=50%>Se incluyen servicios adicionales, suplementarios o promociones:</td>\n" +
                "                    <td width=50%>\n" +
                "                        <table width=100%>\n" +
                "                            <tr>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100%>\n" +
                "                                        <td align=\"center\"><strong>SI</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                        <td><strong>.</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100%>\n" +
                "                                        <td align=\"center\"><strong>NO</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td width=25%>\n" +
                "                                    <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                        <td align=\"center\"><strong>X</strong></td>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "        <td><tr>Se incluyen servicios adicionales, suplementarios o promociones:</td></tr>\n" +
                "        <td><tr>Adicionales y suplementarios:</td></tr>\n" +
                "        <td><tr>\n" +
                "            <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=33% >SERVICIO</td>\n" +
                "                    <td align=\"center\" width=33% >VALOR COMERCIAL</td>\n" +
                "                    <td align=\"center\" width=33% >VALOR ENTREGADO</td>                    \n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=33% >.</td>\n" +
                "                    <td align=\"center\" width=33% ></td>\n" +
                "                    <td align=\"center\" width=33% ></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=33% >.</td>\n" +
                "                    <td align=\"center\" width=33% ></td>\n" +
                "                    <td align=\"center\" width=33% ></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=33% >.</td>\n" +
                "                    <td align=\"center\" width=33% ></td>\n" +
                "                    <td align=\"center\" width=33% ></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=33% >.</td>\n" +
                "                    <td align=\"center\" width=33% ></td>\n" +
                "                    <td align=\"center\" width=33% ></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=33% >.</td>\n" +
                "                    <td align=\"center\" width=33% ></td>\n" +
                "                    <td align=\"center\" width=33% ></td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "    </td></tr>\n" +
                "    <td><tr>Promociones</td></tr>\n" +
                "        <td><tr>\n" +
                "            <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=33% >PROMOCI??N</td>\n" +
                "                    <td align=\"center\" width=33% >VALOR COMERCIAL</td>\n" +
                "                    <td align=\"center\" width=33% >VALOR PROMOCI??N</td>                    \n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=33% >.</td>\n" +
                "                    <td align=\"center\" width=33% ></td>\n" +
                "                    <td align=\"center\" width=33% ></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=33% >.</td>\n" +
                "                    <td align=\"center\" width=33% ></td>\n" +
                "                    <td align=\"center\" width=33% ></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=33% >.</td>\n" +
                "                    <td align=\"center\" width=33% ></td>\n" +
                "                    <td align=\"center\" width=33% ></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=33% >.</td>\n" +
                "                    <td align=\"center\" width=33% ></td>\n" +
                "                    <td align=\"center\" width=33% ></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=33% >.</td>\n" +
                "                    <td align=\"center\" width=33% ></td>\n" +
                "                    <td align=\"center\" width=33% ></td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "    </td></tr>\n" +
                "    <tr><td></br></td></tr>\n" +
                "    <td><tr>El SUSCRIPTOR acepta la entrega de los servicios adicionales, suplementarios y promociones antes detalladas con sus correspondientes valores y que son adicionales al </td></tr>\n" +
                "    <td><tr>\n" +
                "        <table width=100%>\n" +
                "            <tr>\n" +
                "                <td width=30%>Aceptaci??n</td>\n" +
                "                <td width=70%>\n" +
                "                    <table width=100%>\n" +
                "                        <tr>\n" +
                "                            <td width=25%>\n" +
                "                                <table width=100%>\n" +
                "                                    <td align=\"center\"><strong>SI</strong></td>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                            <td width=25%>\n" +
                "                                <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                    <td><strong>.</strong></td>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                            <td width=25%>\n" +
                "                                <table width=100%>\n" +
                "                                    <td align=\"center\"><strong>NO</strong></td>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                            <td width=25%>\n" +
                "                                <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                                    <td><strong>.</strong></td>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                    </table>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "    </td></tr>\n" +
                "    <tr><td></td></tr>\n" +
                "    <tr><td></td></tr>\n" +
                "    <tr><td>Notas</td></tr>\n" +
                "    <tr><td>* Las tarifas incluyen impuestos de Ley</td></tr>\n" +
                "    <tr><td>\n" +
                "            <table width=100%>\n" +
                "                <tr>\n" +
                "                    <td width=50% align=\"center\"><img src=\"data:image/png;base64," + firmauserpng + "\" width=\"200\" height=\"100\"></td>\n" +
                "                    <td width=50% align=\"center\"><img src=\"data:image/png;base64," + firmapng + "\" width=\"200\" height=\"100\"></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td width=50% align=\"center\">PRESTADOR</td>\n" +
                "                    <td width=50% align=\"center\">SUSCRIPTOR</td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "    </table>\n" +
                "</div>";

        return anexod;

    }

    private String pagare(int dia, String mes, int anio){
        String firmapng = Base64.encodeToString(agrement.getSing(), Base64.DEFAULT);

        String pagare = "    <div class=SaltoDePagina>\n" +
                "        <table width=100% style=\"font-size:20px; font-weight:bold;\">\n" +
                "            <tr><td align=\"center\">PAGARE A LA ORDEN</td></tr>\n" +
                "        </table>\n" +
                "        <table width=100% style=\"font-size:13px;\">\n" +
                "            <tr><td align=\"justify\">Yo, " + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + " con C.I. " + partner.getIdentification_id() + ", siendo mayor de edad, identificado/a como aparece al pie de mi firma, actuando en nombre propio, por medio del presente escrito manifiesto, lo siguiente: PRIMERO: Que debo y pagar??, incondicional y solidariamente a la orden de DV TELEVISION DVTV S.A., o a la persona natural o jur??dica a quien el mencionado acreedor ceda o endose sus derechos sobre este pagar??, la suma de <span style=\"font-weight:bold;\">DOSCIENTOS D??LARES AMERICANOS. ($ 200, oo)</span>,  D??LARES AMERICANOS MONEDA LEGAL ECUATORIANA. SEGUNDO: Que el pago total de la mencionada obligaci??n se efectuar?? en un solo valor de contado, el d??a _____ del mes de ________________ del a??o _____________ en las dependencias de DV TELEVISION DVTV S.A. Ubicada en la ciudad de DAULE cant??n Daule , en las calles 9 de Octubre y Jos?? V??lez , o en su cuenta bancaria n. 30618793 del Banco Guayaquil. TERCERO: Que en caso de mora pagar?? a DV TELEVISION DVTV S.A. o a la persona natural o jur??dica a quien el mencionado acreedor ceda o endose sus derechos, intereses de mora a la m??s alta tasa permitida por la Ley, desde el d??a siguiente a la fecha de exigibilidad del presente pagar??, y hasta cuando su pago total se efect??e. CUARTO: Expresamente declaro excusado el protesto del presente pagar?? y los requerimientos judiciales o extrajudiciales para la constituci??n en mora. QUINTO: En caso de que haya lugar al recaudo judicial o extrajudicial de la obligaci??n contenida en el presente t??tulo valor ser?? a mi cargo las costas judiciales y/o los honorarios que se causen por tal raz??n.</td></tr>\n" +
                "            <tr><td align=\"justify\">En constancia de lo anterior, se suscribe en la ciudad de " + partner.getCanton() + " a los "+ dia +" d??as del mes de " + mes + " del a??o " + anio + ".</td></tr>\n" +
                "            <tr><td align=\"center\"><img src=\"data:image/png;base64," + firmapng + "\" width=\"200\" height=\"100\"></td></tr>\n" +
                "            <tr><td align=\"center\">" + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + "</td></tr>\n" +
                "            <tr><td align=\"center\">" + partner.getIdentification_id() + "</td></tr>\n" +
                "            <tr><td></br></td></tr>\n" +
                "        </table>\n" +
                "        <table width=100% style=\"font-size:20px; font-weight:bold;\">\n" +
                "            <tr><td align=\"center\">COMPROMISO DE ENTREGA DE EQUIPOS</td></tr>\n" +
                "        </table>\n" +
                "        <table width=100% style=\"font-size:13px;\">\n" +
                "            <tr><td align=\"justify\">Estimado SR. (a) " + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + " con C.I. " + partner.getIdentification_id() + " le recordamos que los equipos instalados dentro de su domicilio son de propiedad de <span style=\"font-weight:bold;\">DV NET</span>, Los cuales deber??n ser devueltos a nuestras oficinas una vez que el o los servicios contratados sean suspendidos por terminaci??n voluntaria o por suspensi??n por falta de pago.</td></tr>\n" +
                "            <tr><td align=\"justify\">Si se suspenden por falta de pago puede reactivar los servicios dentro de los 5 primeros d??as del siguiente mes antes que se genera la orden de retiro, una vez generada personal t??cnico ira a su domicilio a retirar los equipos, si no son devueltos se proceder?? tomar acciones legales y hacer efectivo el <span style=\"font-weight:bold;\">PAGAR??.</span></td></tr>\n" +
                "            <tr><td align=\"center\"><img src=\"data:image/png;base64," + firmapng + "\" width=\"200\" height=\"100\"></td></tr>\n" +
                "            <tr><td align=\"center\">" + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + "</td></tr>\n" +
                "            <tr><td align=\"center\">" + partner.getIdentification_id() + "</td></tr>\n" +
                "        </table>\n" +
                "    </div>\n";

        return pagare;
    }

    private String telefonia(){
        String telefonia = "<div class=SaltoDePagina>\n" +
                "    <table width=100% style=\"font-size:13px; font-family: Arial\">\n" +
                "        <tr><td ><strong>Estimados clientes,</strong></td></tr>\n" +
                "        <tr><td></br></td></tr>\n" +
                "        <tr><td></br></td></tr>\n" +
                "    </table>\n" +
                "    <table width=100% style=\"font-size:12px; font-family: Arial\">\n" +
                "        <tr><td align=\"justify\">Nos permitimos advertirles sobre la necesidad de que se tomen las medidas necesarias para garantizar la seguridad tecnol??gica frente a posibles vulnerabilidades en sus instalaciones como son:</td></tr>\n" +
                "        <tr><td></br></td></tr>\n" +
                "        <tr><td align=\"justify\">-\tEn sus centrales privadas PBX, evitar colocar claves de acceso al sistema de larga distancia iguales al n??mero de extensi??n.</td></tr>\n" +
                "        <tr><td></br></td></tr>\n" +
                "        <tr><td align=\"justify\">-\tControlar los privilegios para acceder remotamente a sus centrales telef??nicas privadas para el uso de llamadas internacionales.</td></tr>\n" +
                "        <tr><td></br></td></tr>\n" +
                "        <tr><td align=\"justify\">-\tControlar el consumo de llamas hacia destinos considerados de alto costo.</td></tr>\n" +
                "        <tr><td></br></td></tr>\n" +
                "        <tr><td align=\"justify\">-\tColocar las seguridades tecnol??gicas apropiadas cuando se utilice software libre para sus centrales privadas PBX que utilicen tecnolog??a IP y que tengan acceso a internet.</td></tr>        \n" +
                "        <tr><td></br></td></tr>\n" +
                "        <tr><td></br></td></tr>\n" +
                "        <tr><td align=\"justify\"><strong>DVNET ??? DVTELEVISION S.A.</strong> no ser?? responsable por el uso, consumo o violaciones indebidas que se hagan del servicio contratado. El cliente ser?? responsable por el uso, consumo o violaciones del servicio e incluso por el mal uso del mismo en caso de que no haya tomado las medidas necesarias para garantizar la seguridad tecnol??gica frente a posibles vulnerabilidades en sus instalaciones.</td></tr>\n" +
                "        <tr><td></br></td></tr>\n" +
                "        <tr><td></br></td></tr>\n" +
                "        <tr><td>Atentamente,</td></tr>\n" +
                "        <tr><td></br></td></tr>\n" +
                "        <tr><td></br></td></tr>\n" +
                "        <tr><td><strong>DVNET</strong></td></tr>\n" +
                "        <tr><td><strong>DVTELEVISION. S.A.</strong></td></tr>\n" +
                "    </table>\n" +
                "</div>";

        return  telefonia;
    }


//    private String facturacionElectronica(int dia, String mes, int anio, String fecha){
//        String firmapng = Base64.encodeToString(agrement.getSing(), Base64.DEFAULT);
//        String firmauserpng = Base64.encodeToString(user.getSing(), Base64.DEFAULT);
//        String ruc = "0992929170001";
//        if(agrement.getCompany_Id() == "1"){
//            ruc = "0911591212001";
//        }
//        String factura = "<div class=SaltoDePagina style=\"text-align: justify; font-size: 12px;\">\n" +
//                "<h1 class=\"titulo\">CONTRATO DE PRESTACI??N DE SERVICIOS DE FACTURACI??N ELECTR??NICA</h1>\n" +
//                "Conste por el presente documento un Contrato de Prestaci??n de Servicios de Facturaci??n Electr??nica, que en forma libre y voluntaria, se celebra al tenor de las siguientes cl??usulas:\n" +
//                "<h1 class=\"subtitulo\">CL??USULA PRIMERA: OTORGANTES.-</h1>\n" +
//                "Comparecen a la celebraci??n y suscripci??n del presente contrato, las siguientes partes:</br></br>\n" +
//                "<strong>1.1.</strong> Por una parte, la compa????a <strong>DV TELEVISION DVTV S.A.</strong>, con Registro ??nico de Contribuyentes (RUC) No. 0992929170001, \n" +
//                "debidamente representado por su Gerente General, el se??or Ra??l Daniel Zamora Vera, y como tal representante legal, \n" +
//                "quien suscribe, parte a la que en adelante se la podr?? denominar indistintivamente como ???DVNET??? o la ???Compa????a???; y,</br></br>\n" +
//                "<strong>1.2.</strong> Por otra parte: </br>\n" +
//                "Nombre/raz??n social: " + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + "</br>\n" +
//                "C??dula/RUC: " + partner.getIdentification_id() + " E-mail: " + partner.getEmail() + "</br>\n" +
//                "Direcci??n: " + partner.getStreet() + "</br>\n" +
//                "Provincia: " + partner.getProvincia() + " Ciudad: " + partner.getCanton() + " Cant??n: " + partner.getCanton() + " Parroquia:_______</br>\n" +
//                "Direcci??n donde ser?? prestado el servicio: " + partner.getStreet() + "</br></br>\n" +
//                "A quienes para efectos del presente, en su conjunto, se las denominar?? como ???Las Partes???.</br></br>\n" +
//                "<h1 class=\"subtitulo\">CL??USULA SEGUNDA: ANTECEDENTES.-</h1>\n" +
//                "<strong>2.1.</strong> ???DVNET??? es una compa????a an??nima legalmente constituida en el Ecuador, especializada, \n" +
//                "entre otros, en actividades de suministro de telecomunicaciones, acceso a televisi??n por cable e internet, \n" +
//                "as?? como aplicaciones derivadas.</br></br>\n" +
//                "<strong>2.2.</strong> Dentro de la prestaci??n de los productos mencionados, ???DVNET??? ofrece a sus clientes diversos \n" +
//                "servicios adicionales y opcionales, entre ellos, el Servicio de Facturaci??n Electr??nica (en adelante, ???el servicio???), \n" +
//                "el cual es proporcionado por la ???Compa????a??? a aquellos clientes que soliciten el alta/acceso o deseen adquirir el mismo.</br></br>\n" +
//                "<strong>2.3</strong> En virtud de lo expuesto, es voluntad del ???Cliente??? obtener el servicio de facturaci??n \n" +
//                "electr??nica ofrecido por ???DVNET???, por lo que se adhiere de forma expresa, plena y sin reservas a los t??rminos y \n" +
//                "condiciones que constan en el presente contrato.</br></br>\n" +
//                "<h1 class=\"subtitulo\">CL??USULA TERCERA: OBJETO.-</h1>\n" +
//                "Con los antecedentes expuestos, ???DVNET??? se obliga a prestar el Servicio de Facturaci??n Electr??nica a favor \n" +
//                "del ???Cliente???, siempre y cuando, ???el Usuario??? exprese su voluntad, a trav??s de medios f??sicos, electr??nicos \n" +
//                "y/o telef??nicos, de adquirir ???el servicio???, como adicional al suministro principal que haya contratado o de \n" +
//                "manera independiente al mismo, una vez el ???Usuario??? haya presentado su solicitud y la ???Compa????a??? verifique \n" +
//                "el cumplimiento de los requisitos legales respectivos, proceder?? a dar de alta ???el servicio???.</br></br>\n" +
//                "\n" +
//                "Para el efecto, ???DVNET??? se compromete a poner a disposici??n del ???Cliente???, tras la suscripci??n del presente \n" +
//                "instrumento, el acceso a la plataforma web (???facturaci??nelectronica.dvnet.ec???), mediante un usuario y \n" +
//                "clave provisional, en los t??rminos y condiciones que se detallar??n a continuaci??n.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CL??USULA CUARTA: CARACTER??STICAS DEL SERVICIO DE FACTURACI??N ELECTR??NICA.-</h1>\n" +
//                "<strong>4.1</strong> ???DVNET??? prestar?? ???el servicio??? al ???Cliente??? a trav??s de un sistema en l??nea, cuyo dominio \n" +
//                "ser?? ???facturaci??nelectronica.dvnet.ec??? (en adelante, la ???plataforma web???), mediante el cual el ???Cliente??? \n" +
//                "acceder?? con un usuario y una clave provisional; los que ser??n creados y entregados al ???Usuario??? a la suscripci??n \n" +
//                "del presente instrumento.</br></br>\n" +
//                "<strong>4.2</strong> Dentro de la ???plataforma web??? el ???Cliente??? podr?? generar las facturas electr??nicas que requiera, \n" +
//                "con un m??ximo de 200 documentos electr??nicos al a??o que conforman el plan b??sico.</br></br>\n" +
//                "<strong>4.3</strong> ???El Servicio??? permitir??, adem??s de la visualizaci??n en pantalla de la imagen de la factura \n" +
//                "electr??nica y de su detalle, su impresi??n y descarga en formato ???Xml???, conforme a los esquemas .xsd emitidos \n" +
//                "por el Servicio de Rentas Internas (en adelante, ???SRI???), en el ordenador personal del ???Usuario??? para sus \n" +
//                "fines pertinentes.</br></br>\n" +
//                "En el caso de existir comunicaciones ordinarias, relacionadas con el presente contrato, el ???Usuario??? recibir?? \n" +
//                "una copia en formato .pdf del comunicado que se le haya emitido, pudi??ndoselo descargar en el dispositivo electr??nico \n" +
//                "donde se encuentre habilitada la plataforma web.</br></br>\n" +
//                "<strong>4.4</strong> Para que las facturas electr??nicas emitidas por la ???plataforma web??? tengan plena vigencia \n" +
//                "debera el ???Cliente??? contar con firma electr??nica y certificado de autorizaci??n emitido por el SRI, conforme a \n" +
//                "lo que se detallar?? en la siguiente cl??usula como requisito previo para solicitar ???el servicio???.</br></br>\n" +
//                "<strong>4.5</strong> La emisi??n de las facturas electr??nicas dentro de la ???plataforma web??? ser?? de \n" +
//                "entero control de ???El Cliente???.</br></br>\n" +
//                "<strong>4.6</strong> ???El Servicio??? no incluye el almacenamiento de informaci??n, ??nicamente el de emisi??n de \n" +
//                "los documentos electr??nicos, en caso de requerir acceso a la nube se entender?? como un producto adicional, \n" +
//                "el cual tendr?? un cargo a la tarifa b??sica.</br></br>\n" +
//                "<strong>4.7</strong> ???DVNET??? realizar?? el mantenimiento de la ???plataforma web??? para el funcionamiento \n" +
//                "adecuado de la misma.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CL??USULA QUINTA: ALTA EN EL SERVICIO DE FACTURACI??N ELECTR??NICA.-</h1>\n" +
//                "<strong>5.1. Condiciones para que el ???Cliente??? acceda a ???el servicio???:</strong></br></br>\n" +
//                "<strong>a)</strong> El ???Cliente??? manifest?? a ???DVNET???, a trav??s de medios f??sicos, eletr??nicos y/o \n" +
//                "telef??nicos, su voluntad de adquirir ???el servicio???, el cual fue plasmado mediante un formulario de \n" +
//                "solicitud  proporcionado por la ???Compa????a???;</br></br>\n" +
//                "<strong>b)</strong> Asimismo, para que el ???Cliente??? pueda acceder a ???el servicio???, deber?? presentar, \n" +
//                "junto a su petici??n o suscripci??n del presente instrumento, lo siguiente:</br>\n" +
//                "<ul style= \"list-style-type: circle;\">\n" +
//                "   <li>\n" +
//                "   Certificado digital de firma electr??nica, el cual debe ser emitido o actualizado por cualquier \n" +
//                "   entidad autorizada; tales como: Banco Central del Ecuador, Consejo de la Judicatura, Security Data o ANF AC Ecuador.\n" +
//                "   </li>\n" +
//                "   <li>\n" +
//                "   Solicitud de autorizaci??n en el SRI para la emisi??n de comprobantes electr??nicos en ambientes \n" +
//                "   de ???pruebas??? y ???producci??n???.\n" +
//                "   </li>\n" +
//                "</ul></br>\n" +
//                "<strong>5.2. Acceso a ???el servicio???:</strong></br></br>\n" +
//                "El alta en el Servicio de Facturaci??n Electr??nica ser?? de car??cter voluntario para los ???Clientes??? de ???DVNET???, \n" +
//                "siendo gratuito para aquellos nuevos ???Usuarios??? que soliciten ???el servicio??? dentro de _____________ contados \n" +
//                "desde la suscripci??n del contrato del servicio principal. En dicho caso, los ???Clientes??? tendr??n acceso a \n" +
//                "???el servicio??? por un tiempo determinado de __________.  Sin embargo, para aquellos ???Usuarios??? antiguos o \n" +
//                "nuevos que deseen adquirir ???el servicio??? transcurridos los ____________, no ser?? gratuito, aplic??ndose la \n" +
//                "tarifa correspondiente al plan b??sico.</br></br>\n" +
//                "Para los ???Usuarios??? que sean titulares de varios contratos de suministro en vigor, el alta en el Servicio de \n" +
//                "Facturaci??n Electr??nica se aplicar?? individualmente por contrato, no aplic??ndose al resto de contratos de \n" +
//                "titularidad del ???Cliente???. Asimismo, Si el ???Usuario??? realizara nuevos contratos de suministro con ???DVNET???, \n" +
//                "se le ofrecer?? igualmente el alta en el servicio, para lo cual, en caso de dar su consentimiento, tendr?? que \n" +
//                "suscribir el presente instrumento adheriendose a los t??rminos y condiciones inherentes a ???el servicio???.</br></br>\n" +
//                "El alta del ???Servicio??? se realizar?? a trav??s de la aplicaci??n de ???DVNET??? (http://facturacionelectronica.dvnet.ec), \n" +
//                "tras la solicitud/petici??n del cliente, por cualquiera de los canales de atenci??n f??sicos o virtuales de la \n" +
//                "???Compa????a???. Para ello, el ???Cliente??? deber?? identificarse en el formulario de solicitud con los datos b??sicos \n" +
//                "requeridos para la creaci??n de la cuenta y contrase??a provisional que ser?? entregados al ???Cliente???, v??a correo \n" +
//                "electr??nico y/o de manera f??sica, por ???DVNET??? una vez suscrito el presente instrumento.</br></br>\n" +
//                "Se entender?? que desde la presentaci??n de la solicitud/petici??n y suscripci??n del presente instrumento el \n" +
//                "???Cliente??? ha manifestado su voluntad, y por ende consentimiento, para ser dado de alta en el Servicio de \n" +
//                "Facturaci??n Electr??nica, aceptando los t??rminos y condiciones del mismo.</br></br>\n" +
//                "Cuando ???DVNET??? permita el acceso al ???servicio??? al ???Cliente???, ??ste deber?? acceder a la plataforma \n" +
//                "(???facturaci??nelectronica.dvnet.ec???) con el usuario y contrase??a provisional proporcionados por la ???Compa????a??? \n" +
//                "y proceder a realizar el cambio de contrase??a. Una vez, efectuado, el ???Usuario??? tendr?? a entera disposici??n \n" +
//                "la ???plataforma web??? dentro de los fines y alcances de ???el servicio??? adquirido.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CL??USULA SEXTA: TARIFA PLAN B??SICO Y FORMA DE PAGO DE LOS SERVICIOS.-</h1>\n" +
//                "Las tarifas que el ???Cliente??? debe cancelar por la prestaci??n del Servicio de Facturaci??n Electr??nica, objeto \n" +
//                "del presente instrumento, a ???DVNET???, correspondiente, son los detallados en el Anexo 1, que se adjunta \n" +
//                "como habilitante en el presente contrato.</br></br>\n" +
//                "Las tarifas detalladas ser??n canceladas por el ???Cliente??? de manera ______ a <strong>???DVNET???</strong>, \n" +
//                "mediante las siguientes forma de pago:</br></br>\n" +
//                "<table class=\"tabla1\" border=\"1\"; cellpadding=\"0\"; cellspacing=\"1\";>\n" +
//                "    <tr>\n" +
//                "    <td><strong>Forma de pago</strong></td>\n" +
//                "    <td class=\"sino\">SI</td>\n" +
//                "    <td class=\"sino\">NO</td>\n" +
//                "    </tr>\n" +
//                "    <tr>\n" +
//                "    <td>Efectivo, pago directo a trav??s de ventanilla del prestador del servicio</td>\n" +
//                "    <td></td>\n" +
//                "    <td></td>\n" +
//                "    </tr>\n" +
//                "    <tr>\n" +
//                "    <td>D??bito autom??tico cuenta de ahorro, o corriente, o tarjeta de cr??dito</td>\n" +
//                "    <td></td>\n" +
//                "    <td></td>\n" +
//                "    </tr>\n" +
//                "    <tr>\n" +
//                "    <td>Pago en l??nea a trav??s Instituciones Financieras y Auxiliares Financieros \n" +
//                "    o de Pago, autorizados y/o medios electr??nicos</td>\n" +
//                "    <td></td>\n" +
//                "    <td></td>\n" +
//                "    </tr>\n" +
//                "    <tr>\n" +
//                "    <td>Cualquier otro medio de pago determinado y autorizado por la Junta de la \n" +
//                "    Pol??tica y Regulaci??n Monetaria y Financiera</td>\n" +
//                "    <td></td>\n" +
//                "    <td></td>\n" +
//                "    </tr>\n" +
//                "    </table></br></br>\n" +
//                "En caso de que el ???Cliente??? desee cambiar su modalidad de pago a otra disponible, deber?? comunicarse con ???DVNET???, \n" +
//                "a trav??s de cualquier medio autorizado de atenci??n, virtual o f??sico, a efecto de realizar el cambio requerido.</br></br>\n" +
//                "La facturaci??n por el servicio contratado iniciar?? a partir de la fecha " + fecha + " en que \n" +
//                "el ???Cliente??? tenga activado o haya sido dado de alta ???el servicio???, de conformidad el n??meral 5.2. de la cl??usula \n" +
//                "quinta del presente instrumento, m??s los impuestos que por ley correspondan. De igual manera, se facturar?? \n" +
//                "??nicamente hasta la fecha de terminaci??n del Contrato. En caso que llegas?? a suspenderse el servicio, por \n" +
//                "factores t??cnicos concernientes y/o de responsabilidad de ???DVNET???, se facturar?? por los servicios efectivamente \n" +
//                "prestados y aquellos que se justifiquen de conformidad a la normativa vigente.</br></br>\n" +
//                "En caso de que existan reajustes de la tarifa de los servicios contratados por el ???Cliente???, ???DVNET??? \n" +
//                "notificar?? con un t??rmino de 30 d??as de anticipaci??n a la modificaci??n por cualquier medio masivo, \n" +
//                "indic??ndole de manera clara las nuevas caracter??sticas, mejoras y/o tarifas a las condiciones que apliquen.</br></br>\n" +
//                "El ???Cliente??? podr?? solicitar el cambio del plan b??sico contratado, al tenor de lo dispuesto en la cl??usula \n" +
//                "siguiente. La aplicaci??n del nuevo servicio regir?? desde la activaci??n y bajo los nuevos t??rminos y \n" +
//                "condiciones del nuevo plan.</br></br>\n" +
//                "El ???Cliente??? acepta expresamente recibir la factura electr??nica emitida por ???DVNET??? por la prestaci??n de sus servicios \n" +
//                "al siguiente correo electr??nio: " + partner.getEmail() + "</br></br>\n" +
//                "En caso de mora, el ???Cliente??? se compromete, de forma expresa,  a cancelar el valor total adeudado m??s el \n" +
//                "inter??s por mora calculado con las tasas vigentes y establecidas por ???DVNET???, de acuerdo a la fecha en que \n" +
//                "debi?? efectuarse el pago, calculados desde el d??a siguiente de la fecha m??xima de pago constante en la \n" +
//                "factura, hasta el d??a efecto de pago del valor adeudado, as?? como tambi??n, los cargos de cobranza generados \n" +
//                "por ???DVNET???, conforme correspondan y aquellos que se justifiquen en virtud de la normativa vigente.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CL??USULA S??PTIMA: CARGOS ADICIONALES Y OTRAS TARIFAS.-</h1>\n" +
//                "Se entiende como ???cargos adicionales y otras tarifas??? a todos aquellos servicios que no se encuentren \n" +
//                "detallados en las cl??usulas cuarta y quinta del presente contrato, en caso de que el ???Cliente??? desee acceder \n" +
//                "a ellos, en cuanto a su valor y detalle deber??n remitirse al <strong>Anexo 3</strong>, por lo que, acepta que en el caso de \n" +
//                "los cargos, los valores respectivos ser??n sumados a la tarifa del plan b??sico contratado; y, con respecto \n" +
//                "a las ???tarifas???, si el valor corresponde a un nuevo plan, el valor inicial ser?? reemplazado por este.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CL??USULA OCTAVA: PLAZO.-</h1>\n" +
//                "El presente contrato tendr?? una permanencia m??nima de 2 (dos) a??os contados a partir de la fecha en la cual \n" +
//                "???DVNET??? otorgue el alta del ???servicio??? al ???Cliente???, esto es ___________________. El tiempo mencionado es \n" +
//                "aplicado para usuarios nuevos, dentro y fuera de los tres meses, y usuarios antiguos.</br></br>\n" +
//                "El ???Cliente??? tendr?? 3 (tres) meses, contados a partir de la fecha de suscripci??n de contrato del suministro \n" +
//                "principal, para solicitar  ???el servicio??? a ???DVNET???. En caso que el ???Cliente??? lo hici??se transcurrido el \n" +
//                "tiempo indicado, ??ste deber?? pagar un valor por implementaci??n, seg??n la tabla vigente de la ???Compa????a??? al \n" +
//                "momento de la contrataci??n.</br></br>\n" +
//                "El plazo de 2 (dos) a??os ser?? renovado de manera autom??tica, e indefinida, salvo que una de las partes \n" +
//                "exprese su voluntad de darlo por terminado, lo cual ser?? comunicado por escrito dentro de, por lo menos, 15 \n" +
//                "(quince) d??as de anticipaci??n, sin que tal hecho de lugar al pago de ninguna clase de indemnizaci??n.</br></br>\n" +
//                "En el caso en el que el ???Cliente??? quiera proceder a dar de baja ???el servicio??? deber?? cancelar todos los \n" +
//                "valores adeudados por el servicio prestado hasta el momento de su terminaci??n y, adem??s, cancelar el valor \n" +
//                "proporcional por el tiempo que faltase para cumplir el plazo aqu?? estipulado.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CL??USULA NOVENA: GARANT??A. -</h1>\n" +
//                "Durante la vigencia del presente contrato, ???DVNET??? se compromete a emplear los esfuerzos comercialmente \n" +
//                "razonables para proporcionar ???el servicio??? a ???el Cliente???, de conformidad con los est??ndares de la \n" +
//                "industria generalmente aceptados.</br></br>\n" +
//                "Sin perjuicio de lo expuesto, ???DVNET??? otorgar?? a el ???Cliente??? una garant??a en caso de que exista una \n" +
//                "falla t??cnica en la ???plataforma web??? que sea responsabilidad directa de la ???Compa????a???. En consecuencia \n" +
//                "???DVNET??? se obliga a:</br></br>\n" +
//                "<strong>9.1</strong> Brindar asistencia v??a remota, con el personal t??cnico especializado de la ???Compa????a??? \n" +
//                "dentro de las 24 horas de comunicado el suceso.</br></br>\n" +
//                "<strong>9.2</strong> Brindar asistencia de manera presencial, con el personal t??cnico especializado de la \n" +
//                "???Compa????a???, en caso de que la falla t??cnica as?? lo requiera o si pasadas las 24 horas no se hubiese solucionado.</br></br>\n" +
//                "<strong>9.3</strong> En caso de que por la falta de acceso a la ???plataforma web??? el ???Cliente??? no haya podido \n" +
//                "cumplir con sus obligaciones tributarias pertinentes, ???DVNET??? se obliga a prestar el servicio de manera gratuita \n" +
//                "por el mismo tiempo que se haya encontrado inactivo, una vez cumplido el plazo del contrato.</br></br>\n" +
//                "???DVNET??? se obliga a cumplir con la garant??a expuesta, siempre que:</br></br>\n" +
//                "<strong>a.</strong> Los dispositivos electr??nicos del ???Cliente??? se encuentren en un buen estado operacional \n" +
//                "que permita el funcionamiento ??ptimo de la ???plataforma web???.</br></br>\n" +
//                "<strong>b.</strong> El ???Cliente??? brindar?? informaci??n pertinente para la resoluci??n de problemas y, para \n" +
//                "el alojamiento en servidores propios, cualquier acceso que ???DVNET??? puede necesitar para identificar, \n" +
//                "reproducir y solventar los problemas.</br></br>\n" +
//                "Asimismo, ???DVNET??? est?? obligado ??nicamente a reanudar la ejecuci??n del servicio aqu?? contratado cuando se trate \n" +
//                "de fallas t??cnicas de la ???plataforma web??? relacionadas con su manejo directo. ???DVNET??? no estar?? obligado a \n" +
//                "cumplir con la presente garant??a en caso que el da??o o falla obedezca a un caso fortuito o fuerza mayor en los \n" +
//                "t??rminos de ley, que sea originado por causas imputables al ???Cliente??? y/o un tercero o que se incumplan los \n" +
//                "puntos a. y b. de la presente cl??usula.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CL??USULA D??CIMA: OBLIGACIONES Y RESPONSABILIDADES DE ???DVNET???.-</h1>\n" +
//                "???DVNET??? adquiere los siguientes compromisos con el ???Cliente??? que contrate el Servicio de Facturaci??n Electr??nica:</br></br>\n" +
//                "<ul style= \"list-style-type: square;\">\n" +
//                "    <li>Prestar ???el servicio??? contratado por el ???Cliente??? de forma continua y permanente, durante el tiempo de \n" +
//                "    duraci??n de este Contrato, en los t??rminos y condiciones aqu?? establecidos, salvo las situaciones de \n" +
//                "    fuerza mayor o caso fortuito, seg??n las establecidas en el art??culo 30 de C??digo Civil ecuatoriano vigente.</br></br>\n" +
//                "    </li>\n" +
//                "    <li>\n" +
//                "    ???DVNET??? estar?? exenta de responsabilidad en caso de mal uso de los servicios contratado por parte del \n" +
//                "    ???Usuario???, por lo que no estar?? obligada al pago de indemnizaci??n de ninguna clase.</br></br>\n" +
//                "    </li>\n" +
//                "    <li>\n" +
//                "    Informar sobre las caracter??sticas de los servicios ofertados, como promociones de planes, tarifas, \n" +
//                "    precios, saldos, cargos y otros servicios informativos, a trav??s de medios f??sicos y electr??nicos.</br></br>\n" +
//                "    </li>\n" +
//                "    <li>\n" +
//                "    En caso de que ???DVNET??? requiera modificar las caracter??sticas t??cnicas del ???Servicio??? contratado, \n" +
//                "    notificar?? por escrito por medios f??sicos, electr??nicos o telef??nicos a el ???Usuario???, en un \n" +
//                "    plazo no mayor a 30 (treinta) d??as de anticipaci??n a la modificaci??n.</br></br>\n" +
//                "    </li>\n" +
//                "    <li>\n" +
//                "    ???DVNET??? deber?? permitir a el ???Usuario??? modificar su contrase??a a trav??s de la propia funcionalidad \n" +
//                "    que le ofrece la web, en el momento que el ???Cliente??? lo requiera.</br></br>\n" +
//                "    </li>\n" +
//                "    <li>\n" +
//                "    ???DVNET??? se obliga a observar las m??ximas garant??as exigidas en el ??mbito legislativo.</br></br>\n" +
//                "    </li>\n" +
//                "</ul>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CL??USULA UND??CIMA: OBLIGACIONES, RESPONSABILIDADES Y PROHIBICIONES DEL ???CLIENTE???.-</h1>\n" +
//                "<strong>11.1. Obligaciones y responsabilidades:</strong></br></br>\n" +
//                "El ???Usuario??? adquiere los siguientes compromisos al contratar el Servicio de Facturaci??n Electr??nica:</br></br>\n" +
//                "<ul style= \"list-style-type: square;\">\n" +
//                "   <li>Cumplir con los t??rminos y condiciones estipulados en el presente instrumento.</br></br></li>\n" +
//                "   <li>Realizar un buen uso del ???servicio??? contratado y para los fines convenidos.</br></br></li>\n" +
//                "   <li>El ???Cliente??? ser?? responsable de la emisi??n de las facturas electr??nicas en la ???plataforma web??? \n" +
//                "   proporcionada por ???DVNET???, as?? como de llevar sus libros contables conforme a las exigencias de la \n" +
//                "   normativa tributaria vigente.</br></br></li>\n" +
//                "   <li>El ???Cliente??? ser?? responsable del almacenamiento de las facturas electr??nicas que emita dentro de \n" +
//                "   la ???plataforma web??? en cualquier dispositivo apto para aquel fin.</br></br></li>\n" +
//                "   <li>Informarse adecuadamente y de manera oportuna por los medios electr??nicos y f??sicos que ???DVNET??? \n" +
//                "   pone a su disposici??n.</br></br></li>\n" +
//                "   <li>Notificar a ???DVNET??? cuando ocurra la interrupci??n o da??o en la ???plataforma web??? concernientes \n" +
//                "   a fallas t??cnicas relacionadas directamente con la ???Compa????a???.</br></br></li>\n" +
//                "   <li>Pagar el servicio contratado y efectivamente prestado conforme lo determina el presente contrato y \n" +
//                "   el ordenamiento jur??dico vigente, en las fechas de facturaci??n correspondiente.</br></br></li>\n" +
//                "   <li>Cumplir con las obligaciones o resoluciones emitidas por el SRI y dem??s que se derivan del \n" +
//                "   ordenamiento jur??dico vigente.</br></br></li>\n" +
//                "   <li>Comunicar a ???DVNET??? cualquier cambio en la direcci??n de correo electr??nico en la que desee \n" +
//                "   recibir todas las comunicaciones y documentos pertinentes para la prestaci??n de el ???servicio???. \n" +
//                "   En caso de que el Cliente no cumpliera con este compromiso, ???DVNET??? no se responsabiliza del \n" +
//                "   correcto env??o de los mensajes.</br></br></li>\n" +
//                "   <li>Garantizar y responder, en todo caso, de la veracidad, exactitud, vigencia y autenticidad de \n" +
//                "   los datos personales facilitados a trav??s del formulario de solicitud/petici??n de alta al ???servicio???.</br></br></li>\n" +
//                "</ul>\n" +
//                "<strong>11.2. Prohibiciones:</strong></br></br>\n" +
//                "<ul>\n" +
//                "   <li>El ???Cliente??? tiene estrictamente prohibido compartir su usuario y contrase??a con terceros.</br></br></li>\n" +
//                "   <li>El ???Cliente??? no podr?? utilizar diversos dispositivos electr??nicos de manera paralela. Si mediante \n" +
//                "   el metodo de v??lidaci??n interna ???DVNET??? determina diversas direcciones IP, que se encuentren utilizando \n" +
//                "   ???el servicio??? de manera continua y simult??nea proceder?? a realizar el cargo respectivo.</br></br></li>\n" +
//                "   <li>El ???Usuario??? no podr?? utilizar el ???servicio??? para fines il??citos, fraude o perjuicio a terceros, \n" +
//                "   o que contravengan a las normativas referentes a lavado de activos.</br></br></li>\n" +
//                "</ul>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CL??USULA D??CIMA SEGUNDA: CESI??N DE DERECHO. -</h1>\n" +
//                "El ???Cliente??? no podr??, en ning??n caso, ceder, ni transferir, en todo o en parte los derechos y obligaciones \n" +
//                "que le confiere el presente contrato, sus anexos o modificaciones de los mismos, salvo que ???DVNET??? lo \n" +
//                "autorice por escrito.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CL??USULA D??CIMA TERCERA: DIVISIBILIDAD. -</h1>\n" +
//                "Las partes acuerdan que en caso de que cualquier estipulaci??n de este contrato fuera declarada \n" +
//                "inv??lida o nula por autoridad competente, dicha declaraci??n afectar?? ??nica y exclusivamente a dicha \n" +
//                "estipulaci??n, separ??ndose de este contrato y manteni??ndose el mismo v??lido en el resto de sus partes.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CL??USULA D??CIMA CUARTA: NATURALEZA JUR??DICA Y NORMATIVA TRIBUTARIA PERTINENTE. -</h1>\n" +
//                "El presente contrato de prestaci??n de servicios es de car??cter civil, el cual se fundamenta en la siguiente normativa:</br></br>\n" +
//                "<strong>14.1</strong> Ley de Comercio Electr??nico, Firmas Electr??nicas y Mensajes de Datos, publicado Registro \n" +
//                "Oficial Suplemento No. 557 el 17 de abril de 2002, con ??ltima reforma el 27 de agosto de 2021 y su Reglamento \n" +
//                "General, los cuales regulan la prestaci??n de servicios electr??nicos a trav??s de redes de informaci??n; comercio \n" +
//                "electr??nico y la protecci??n de los usuarios de estos sistemas.</br></br>\n" +
//                "<strong>14.2</strong> Resoluci??n No. NAC-DGERCGC18-00000431, emitida por el SRI la cual establece los sujetos \n" +
//                "pasivos que hasta el 2024 deben encontrarse, obligatoriamente, emitiendo comprobantes de venta, comprobantes \n" +
//                "de retenci??n y documentos complementarios de manera electr??nica.</br></br>\n" +
//                "<strong>14.3</strong> Resoluci??n No. NAC-DGERCGC18-00000175, emitida por el SRI en junio de 2018, reforma \n" +
//                "resoluci??n No. NAC-DGERCGC18-00000428, que dicta las normas para la transmisi??n de informaci??n sobre \n" +
//                "documento electr??nicos a trav??s de impresoras fiscales.</br></br>\n" +
//                "<strong>14.4</strong> Resoluci??n No. NAC-DGERCGC18-00000233, emitida por el SRI en junio de 2018, en la \n" +
//                "cual se establecen las normas para emisi??n, entrega y transmisi??n de comprobantes de venta, retenci??n y \n" +
//                "documentos complementarios expedidos por sujetos pasivos autorizados, mediante el esquema de comprobantes \n" +
//                "electr??nicos.</br></br>\n" +
//                "<strong>14.5</strong> Resoluci??n No. NAC-DGERCGC16-00000287, emitida por el SRI en julio de 2016, en la cual \n" +
//                "expide definiciones para la emisi??n de comprobantes emitidos por medios digitales o electr??nicos de pago.</br></br>\n" +
//                "<strong>14.6</strong> Resoluci??n No. NAC-DGERCGC14-00790, emitida por el SRI en octubre de 2014, la cual expide \n" +
//                "las normas para la emisi??n y autorizaci??n de comprobantes de venta, retenci??n y documentos complementaci??n \n" +
//                "mediante comprobantes electr??nicos.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CL??USULA D??CIMA QUINTA: LIMITACI??N DE LA RESPONSABILIDAD.-</h1>\n" +
//                "???DVNET??? se encuentra exento de responsabilidad en aquellos actos que no sean imputados directamente a la \n" +
//                "prestaci??n de su servicio, fuerza mayor o caso fortuito, asimismo, aquellos il??citos que pudiera ocasionar \n" +
//                "el ???Cliente??? y que ocasionen perjuicio a un tercero.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CL??USULA D??CIMA SEXTA: PROTECCI??N DE DATOS.-</h1>\n" +
//                "<strong>16.1. Recogida y tratamiento de datos de car??cter personal:</strong></br></br>\n" +
//                "La recogida y tratamiento de datos de car??cter personal recabados en el formulario de solicitud/petici??n, \n" +
//                "tienen como finalidad proporcionar el servicio de facturaci??n electr??nica solicitada.</br></br>\n" +
//                "De acuerdo con lo dispuesto en la Ley Org??nica de Protecci??n de Datos Personales y su normativa \n" +
//                "supletoria vigente, el ???Cliente??? ha expresado su consentimiento para el tratamiento de sus datos personales, \n" +
//                "para efectos de la prestaci??n del ???servicio???, es decir, las obligaciones contractuales emanadas del presente instrumento.</br></br>\n" +
//                "Los datos solicitados en los formularios/peticiones, incluidos los que consten en la ???plataforma web???, tienen \n" +
//                "car??cter obligatorio, salvo que en los mismos se indicase otra cosa, autorizando el ???Cliente??? su tratamiento \n" +
//                "para las finalidades indicadas por ???DVNET???.</br></br>\n" +
//                "En ning??n caso podr??n incluirse en el formulario de petici??n datos de car??cter personal correspondientes a \n" +
//                "terceras personas, salvo que el solicitante hubiese recabado con car??cter previo su consentimiento en los \n" +
//                "t??rminos exigidos por la Ley Org??nica de Protecci??n de Datos Personales, respondiendo con car??cter exclusivo del \n" +
//                "incumplimiento de esta obligaci??n y cualquier otra en materia de datos de car??cter personal.</br></br>\n" +
//                "<strong>16.2. Derechos de acceso, rectificaci??n, oposici??n y cancelaci??n:</strong></br></br>\n" +
//                "El Cliente puede ejercitar sus derechos de acceso, rectificaci??n, cancelaci??n y oposici??n, en los t??rminos \n" +
//                "establecidos legalmente, comunic??ndolo por escrito a ???DVNET???, exceptuando aquellas excepciones determinadas en \n" +
//                "el art??culo 18 de Ley Org??nica de Protecci??n de Datos Personales. En dicho sentido, autorizo que ???DVNET??? proporcione \n" +
//                "a las entidades pertinentes mi informaci??n crediticia, por ser una obligaci??n emanada del presente contrato.</br></br>\n" +
//                "<strong>16.3. Empleo de cookies:</strong></br></br>\n" +
//                "???DVNET??? puede emplear cookies, al objeto de facilitarle una gesti??n m??s ??gil y eficaz en de los servicios prestados.</br></br>\n" +
//                "Los cookies son un m??todo de autentificaci??n de usuarios que permiten guardar constancia del identificador \n" +
//                "asignado en el momento del registro del cliente, de manera que en lo sucesivo se evita reiterar los tr??mites \n" +
//                "propios de nuevos registros.</br></br>\n" +
//                "El cliente puede configurar el navegador de su ordenador para que le advierta del acceso de cookies y, en su caso, \n" +
//                "de impedir la recepci??n de las mismas en el disco duro.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CL??USULA D??CIMA S??PTIMA: CONFIDENCIALIDAD.-</h1>\n" +
//                "???DVNET???, su representante legal y las personas que intervengan en la ejecuci??n del presente contrato, deber??n \n" +
//                "guardar la m??s estricta confidencialidad de toda informaci??n que el ???Cliente??? detalle en sus documentos electr??nicos \n" +
//                "con motivo o por raz??n del presente instrumento, quedando expresamente prohibida su divulgaci??n a terceros, \n" +
//                "so pena de incurrir en las responsabilidades civiles y penales que conllevan su inobservancia.</br></br>\n" +
//                "La confidencialidad estipulada en esta cl??usula ser?? durante el tiempo que dure el presente contrato y subsistir??\n" +
//                "en forma indefinida despu??s de su terminaci??n.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CL??USULA D??CIMA OCTAVA: TERMINACI??N DEL CONTRATO.-</h1>\n" +
//                "???Las partes??? intervinientes acuerdan en forma rec??proca que el presente contrato, sus anexos y dem??s documentos \n" +
//                "que forman parte integrante del mismo, podr??n ser declarados terminados en los siguientes casos:</br></br>\n" +
//                "<strong>18.1 </strong>Por vencimiento del plazo del contrato, para lo cual, cualquiera de las partes podr?? \n" +
//                "notificar a la otra con 15 (quince) d??as h??biles las terminaci??n por esta causal.</br></br>\n" +
//                "<strong>18.2 </strong>Por falta de pago de 15 (quince) a 30 (treinta) d??as posteriores a la emisi??n de la \n" +
//                "factura de ???DVNET???.</br></br>\n" +
//                "<strong>18.3 </strong>Por insolvencia declarada por juez competente, concurso de acreedores, quiebra, \n" +
//                "disoluci??n o liquidaci??n, de cualquiera de las partes intervinientes.</br></br>\n" +
//                "<strong>18.4 </strong>Cuando por circunstancias imprevistas, t??cnicas o econ??micas, o causas de fuerza mayor o \n" +
//                "caso fortuito debidamente justificado, no fuere posible o conveniente para los intereses de ???Las Partes??? \n" +
//                "ejecutar total o parcialmente el contrato.</br></br>\n" +
//                "<strong>18.5 </strong>Por mandato judicial o disposici??n de autoridad administrativa competente.</br></br>\n" +
//                "<strong>18.6 </strong>Por incumplimiento del ???Cliente??? de las obligaciones previstas en el presente contrato.</br></br>\n" +
//                "<strong>18.7 </strong>Cuando el ???Cliente??? cediera total o parcialmente el contrato sin autorizaci??n de ???DVNET???.</br></br>\n" +
//                "<strong>18.8 </strong>Por muerte del ???Cliente???, lo cual, surtir?? efectos cuando documentadamente se ponga en \n" +
//                "conocimiento de ???DVNET??? del hecho acaecido.</br></br>\n" +
//                "<strong>18.9 </strong>Por alquilar, revender o negociar de cualquier forma el servicio contratado.</br></br>\n" +
//                "<strong>18.10 </strong>Si el ???Cliente??? utiliza el ???servicio??? contratado para fines distintos o para pr??cticas \n" +
//                "contrarias a la ley, las buenas costumbres, la moral o cualquier otra forma que perjudique a la ???Compa????a???</br></br>\n" +
//                "La terminaci??n por mutuo acuerdo no implica la renuncia de las obligaciones, ni derechos a favor de ???DVNET??? \n" +
//                "y del ???Cliente???. En este caso, el ???Usuario??? deber?? pagar todos los valores adeudados por los servicios prestados \n" +
//                "y, adem??s, el valor proporcional por el tiempo que faltase para cumplir el plazo aqu?? estipulado. Asimismo, ???DVNET??? \n" +
//                "proceder?? inmediatamente a suspender el servicio sin previo aviso y sin que el ???Cliente??? tenga derecho a \n" +
//                "indemnizaci??n o devoluci??n de dinero alguna.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CL??USULA D??CIMA NOVENA: JURISDICCI??N, COMPETENCIA Y SOLUCI??N DE CONTROVERSIAS. -</h1>\n" +
//                "???Las partes??? declaran expresamente que renuncian a fuero y domicilio, y convienen en que, para cualquier controversia \n" +
//                "o diferencia que surja o se relacione con la interpretaci??n o ejecuci??n del presente contrato, se someter??n \n" +
//                "a la mediaci??n del Centro de Arbitraje y Conciliaci??n de la C??mara de Comercio de Guayaquil. En caso de no \n" +
//                "llegarse a un acuerdo de mediaci??n, las partes se someter??n al arbitraje en derecho, de acuerdo al procedimiento \n" +
//                "establecido en la Ley de Arbitraje y Mediaci??n, en el Reglamento de dicho Centro y en las siguientes normas:</br></br>\n" +
//                "<strong>19.1 </strong>Las partes designar??n 1 (UN) s??lo ??rbitro, que ser?? seleccionado por sorteo, conforme \n" +
//                "lo establecido en la Ley de Arbitraje, el cual contar?? con el suplente respectivo;</br></br>\n" +
//                "<strong>19.2 </strong>El ??rbitro de dicho centro efectuar?? un arbitraje administrado en Derecho y confidencial, \n" +
//                "y quedar?? facultado para dictar medidas cautelares solicitando el auxilio de funcionarios p??blicos, judiciales, \n" +
//                "policiales y administrativos, sin que sea necesario acudir a un juez ordinario para tales efectos;</br></br>\n" +
//                "<strong>19.3 </strong>El procedimiento arbitral tendr?? lugar en las instalaciones del Centro de Arbitraje \n" +
//                "y Conciliaci??n de la C??mara de Comercio de Guayaquil;</br></br>\n" +
//                "<strong>19.4 </strong>Las ???PARTES??? renuncian a la jurisdicci??n ordinaria, se obligan acatar el laudo arbitral \n" +
//                "y se comprometen a no interponer ning??n tipo de recurso en contra del mismo;</br></br>\n" +
//                "<strong>19.5 </strong>El laudo arbitral ser?? inapelable; y,</br></br>\n" +
//                "<strong>19.6 </strong>Las costas procesales que se generen por el procedimiento arbitral, tales como derechos \n" +
//                "del Centro de Arbitraje y Conciliaci??n, honorarios de peritos, ??rbitros, abogados, etc., ser??n asumidos \n" +
//                "??ntegra y totalmente por quien determine el ??rbitro en el laudo que expida.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CL??USULA VIG??SIMA: NOTIFICACIONES.-</h1>\n" +
//                "Las notificaciones que correspondan, ser??n entregadas al ???Cliente??? a la direcci??n e correo \n" +
//                "electr??nico " + partner.getEmail() + ", as?? como en su domicilio " + partner.getStreet() + ".</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CL??USULA VIG??SIMA PRIMERA: ACEPTACI??N. -</h1>\n" +
//                "El ???Cliente??? acepta el presente contrato, anexos y dem??s documentos, en su totalidad, para lo cual deja constancia \n" +
//                "de lo anterior y firma junto a ???DVNET??? en tres ejemplares del mismo tenor, en la ciudad \n" +
//                "de " + partner.getCanton() + " a los " + dia + " d??a del mes de " + mes +" del a??o " + anio + ".</br></br>\n" +
//                "Firman las partes:</br></br></br></br>\n" +
//                "\n" +
//                "<table class=\"tabla2\" cellpadding = 0 cellspacing=\"1\">\n" +
//                "<tr>" +
//                "<td align=\"center\"><img src=\"data:image/png;base64," + firmapng + "\" width=\"200\" height=\"100\"></td>" +
//                "<td align=\"center\"><img src=\"data:image/png;base64," + firmauserpng + "\" width=\"200\" height=\"100\"></td>\n" +
//                "</tr>\n" +
//                "<tr style=\"text-align: center;\">\n" +
//                "<td>Firma del ???Cliente???</td>\n" +
//                "<td>Firma autorizada por ???DVNET???</td>\n" +
//                "</tr>\n" +
//                "<tr>\n" +
//                "<td></br></br></td>\n" +
//                "<td></br></br></td>\n" +
//                "</tr>\n" +
//                "<tr>\n" +
//                "<td>\n" +
//                "RAZ??N SOCIAL</br>\n" +
//                "RUC: " + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + "</br>\n" +
//                "Nombre y apellido: " + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + "</br>\n" +
//                "No. C??dula: " + partner.getIdentification_id() + "</br></br>\n" +
//                "</br></br>\n" +
//                "</td>\n" +
//                "<td>\n" +
//                "RUC: " + ruc + "</br>\n" +
//                "Nombre y apellido: " + user.getName() + "</br>\n" +
//                "No. C??dula: " + user.getIdentificacion() + "</br></br>\n" +
//                "</br></br>\n" +
//                "</td>\n" +
//                "</tr>\n" +
//                "</table>\n" +
//                "\n" +
//                "<h1 class=\"anexo\">\n" +
//                "ANEXO 2</br>\n" +
//                "AUTORIZACI??N DE D??BITO\n" +
//                "</h1>\n" +
//                "<strong>Aplica: </strong> Si ( &nbsp ) &nbsp No ( &nbsp )</br></br>\n" +
//                "Como cliente de ???DVNET???, adicionalmente ratifico mi compromiso de mantener los pagos de mi tarjeta de cr??dito \n" +
//                "y los fondos suficientes en mi cuenta corriente o de ahorros dentro de los plazos estipulados, a fin de cubir \n" +
//                "los valores cuyos d??bitos autom??ticos autorizo a traves del presente documento.</br></br>\n" +
//                "Asimismo, expresamente me obligo a no renovar la presente autorizaci??n sin el previo consentimiento por \n" +
//                "escrito de ???DVNET???, por lo que libero de toda responsabilidad a la entidad financiera, banco o la emisora \n" +
//                "de la tarjeta de cr??dito por los d??bitos o cargos efectuados en base a la presente autorizaci??n. De igual manera, \n" +
//                "autorizo que en caso de p??rdida, o cualquier circunstancia por la cual fuera cambiado el n??mero de la tarjeta \n" +
//                "de cr??dito, o de la cuenta antes mencionada, en caso de p??rdida, expiraci??n, perdida, o cambio de n??mero, \n" +
//                "me comprometo a notificar en forma inmediata a ???DVNET???, sobre el nuevo n??mero asignado; de tal manera que el \n" +
//                "cambio de n??mero indicado no ser?? causa para no cancelar los valores que adeude a ???DVNET???.</br></br>\n" +
//                "???DVNET??? no asume ninguna responsabilidad sobre los cargos que la instituci??n financiera por usted \n" +
//                "seleccionada le cobra por prestar ese servicio a su cliente.</br></br>\n" +
//                "El cliente declara conocer que la informaci??n suministrada es ver??dica y manifiesta su conocimiento \n" +
//                "expreso e irrevocable a la ???DVNET???, a posible relacionada de sus derechos y obligaciones o a quien pudiese \n" +
//                "ostentar a futuro a cualquier t??tulo, la calidad de acreedor de los valores adeudados por el cliente, por \n" +
//                "concepto de los servicios prestados para:</br></br>\n" +
//                "<ol>\n" +
//                "   <li>\n" +
//                "   Consultar, en cualquier tiempo, en los bur??s de informaci??n crediticia, toda la informaci??n \n" +
//                "   relevante que permite a ???DVNET??? conocer el desempe??o del cliente, como deudor y su capacidad de \n" +
//                "   pago, valorar el riesgo futuro en caso de concederle un cr??dito por el servicio a prestarse.\n" +
//                "   </li>\n" +
//                "   <li>\n" +
//                "   Reportar en los bur??s de informaci??n crediticia en forma directa o por intermedio de la Superitendencia \n" +
//                "   de Bancos y Seguros, datos relativos a:</br>\n" +
//                "   2.1 Cumplimiento oportuno o incumplimiento de las obligaciones crediticias pasadas, \n" +
//                "   presentes o futuras el cliente.</br>\n" +
//                "   2.2 Informaci??n comercial, financiera y econ??mica que el cliente haya entregado o que conste en \n" +
//                "   registros p??blicos, bases de datos p??blicas o documentos p??blicos.</br></br>  \n" +
//                "   </li>\n" +
//                "   <li>\n" +
//                "   Conservar, tanto de manera interna en la ???Compa????a???, como en los bur??s de informaci??n crediticia, \n" +
//                "   con las debidas autorizaciones y durante el periodo necesario, la informaci??n detallada en el numeral \n" +
//                "   2 de esta declaraci??n.\n" +
//                "   </li>\n" +
//                "</ol>\n" +
//                "Esta autorizaci??n expresa del cliente permitir?? a ???DVNET??? y a los bur??s de informaci??n crediticia divulgar \n" +
//                "la informaci??n mencionada para evaluar los riesgos de conceder al cliente un cr??dito por el servicio a \n" +
//                "prestar, elaborar estad??sticas, derivar, mediante modelos matem??ticos, conclusiones de ellas, y \n" +
//                "dem??s fines autorizados por la ley.</br></br>\n" +
//                "Si a pesar de existir la factibilidad t??cnica y comercial para prestar el servicio solicitado por el cliente, \n" +
//                "???DVNET??? se reserva la facultad de negar la solicitud del servicio</br></br>\n" +
//                "Lugar y fecha: " + partner.getCanton() + ", " + fecha +"</br></br></br></br>\n" +
//                "<table class=\"tabla2\" cellpadding = 0 cellspacing=\"1\">\n" +
//                "<tr style=\"text-align: center;\">\n" +
//                "<td align=\"center\"><img src=\"data:image/png;base64," + firmapng + "\" width=\"200\" height=\"100\"></td>" +
//                "<td align=\"center\"><img src=\"data:image/png;base64," + firmauserpng + "\" width=\"200\" height=\"100\"></td>\n" +
//                "</tr>\n" +
//                "<tr style=\"text-align: center;\">\n" +
//                "<td>Firma cliente</td>\n" +
//                "<td>Firma vendedor</td>\n" +
//                "</tr>\n" +
//                "<tr>\n" +
//                "<td></br></br></td>\n" +
//                "<td></br></br></td>\n" +
//                "</tr>\n" +
//                "<tr>\n" +
//                "<td>\n" +
//                "Nombre y apellido: " + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + "</br>\n" +
//                "No. C??dula: " + partner.getIdentification_id() + "</br>\n" +
//                "</td>\n" +
//                "<td>\n" +
//                "Nombre y apellido: " + user.getName() + "</br>\n" +
//                "No. C??dula: " + user.getIdentificacion() + "</br>\n" +
//                "</td>\n" +
//                "</tr>\n" +
//                "<tr>\n" +
//                "<td>\n" +
//                "</td>\n" +
//                "</tr>\n" +
//                "<tr>\n" +
//                "<td>\n" +
//                "</td>\n" +
//                "</tr>\n" +
//                "</table>\n" +
//                "</div>";
//        return factura;
//    }

    private String smart(int dia, String mes, int anio, String fecha){
        String firmapng = Base64.encodeToString(agrement.getSing(), Base64.DEFAULT);
        String firmauserpng = Base64.encodeToString(user.getSing(), Base64.DEFAULT);

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo_dvnet);
        ByteArrayOutputStream stream = new ByteArrayOutputStream(20480);
        bitmap.compress(Bitmap.CompressFormat.PNG, 60 , stream);
        String logoDvnet = Base64.encodeToString(stream.toByteArray(), Base64.DEFAULT);

        String plan = agrement.getTipoServicio() + " " + agrement.getPlan();
        if ( !agrement.getSmart_id().equals("0") ) {
            plan = agrement.getSmart();
        }

        String ruc = "0992929170001";
        if(agrement.getCompany_Id().equals("1")){
            ruc = "0911591212001";
        }

        String smarthtml = "<div class=SaltoDePagina style=\"text-align: justify; font-size: 12px;\">\n" +
                "<h1 class=\"titulo\">CONTRATO DE PRESTACI??N DE SERVICIOS DE FACTURACI??N ELECTR??NICA</h1>\n" +
                "Conste por el presente documento un Contrato de Prestaci??n de Servicios de Facturaci??n Electr??nica, que, en forma libre y voluntaria, se celebra al tenor de las siguientes cl??usulas:\n" +
                "<h1 class=\"subtitulo\">CL??USULA PRIMERA: OTORGANTES.-</h1>\n" +
                "Comparecen a la celebraci??n y suscripci??n del presente contrato, las siguientes partes:</br></br>\n" +
                "<strong>1.1.</strong> Por una parte, la compa????a <strong>DV TELEVISION DVTV S.A.</strong>, con Registro ??nico de Contribuyentes (RUC) No. 0992929170001, \n" +
                "debidamente representado por su Gerente General, el se??or Ra??l Daniel Zamora Vera, y como tal representante legal, \n" +
                "quien suscribe, parte a la que en adelante se la podr?? denominar distintivamente como ???DVNET??? o la ???Compa????a???; y,</br></br>\n" +
                "<strong>1.2.</strong> Por otra parte: </br>\n" +
                "Nombre/raz??n social: " + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + "</br>\n" +
                "C??dula/RUC: " + partner.getIdentification_id() + " E-mail: " + partner.getEmail() + "</br>\n" +
                "Direcci??n: " + partner.getStreet() + "</br>\n" +
                "Provincia: " + partner.getProvincia() + " Cant??n: " + partner.getCanton() + " Parroquia:______________</br>\n" +
                "Direcci??n donde ser?? prestado el servicio: " + partner.getStreet() + "</br></br>\n" +
                "A quienes para efectos del presente, en su conjunto, se las denominar?? como ???Las Partes???.</br></br>\n" +
                "<h1 class=\"subtitulo\">CL??USULA SEGUNDA: ANTECEDENTES.-</h1>\n" +
                "<strong>2.1.</strong> ???DVNET??? es una compa????a an??nima legalmente constituida en el Ecuador, especializada, \n" +
                "entre otros, en actividades de suministro de telecomunicaciones, acceso a televisi??n por cable e internet, \n" +
                "as?? como aplicaciones derivadas.</br></br>\n" +
                "<strong>2.2.</strong> Dentro de la prestaci??n de los productos mencionados, ???DVNET??? ofrece a sus clientes diversos \n" +
                "servicios adicionales y opcionales, entre ellos, el Servicio de Facturaci??n Electr??nica (en adelante, ???el servicio???), \n" +
                "el cual es proporcionado por la ???Compa????a??? a aquellos clientes que soliciten el alta/acceso o deseen adquirir el mismo.</br></br>\n" +
                "<strong>2.3</strong> En virtud de lo expuesto, es voluntad del ???Cliente??? obtener el servicio de facturaci??n \n" +
                "electr??nica ofrecido por ???DVNET???, por lo que se adhiere de forma expresa, plena y sin reservas a los t??rminos y \n" +
                "condiciones que constan en el presente contrato.</br></br>\n" +
                "<h1 class=\"subtitulo\">CL??USULA TERCERA: OBJETO.-</h1>\n" +
                "Con los antecedentes expuestos, ???DVNET??? se obliga a prestar el Servicio de Facturaci??n Electr??nica a favor \n" +
                "del ???Cliente???, siempre y cuando, ???el Usuario??? exprese su voluntad, a trav??s de medios f??sicos, electr??nicos \n" +
                "y/o telef??nicos, de adquirir ???el servicio???, como adicional al suministro principal que haya contratado o de \n" +
                "manera independiente al mismo, una vez el ???Usuario??? haya presentado su solicitud y la ???Compa????a??? verifique \n" +
                "el cumplimiento de los requisitos legales respectivos, proceder?? a dar de alta ???el servicio???.</br></br>\n" +
                "\n" +
                "Para el efecto, ???DVNET??? se compromete a poner a disposici??n del ???Cliente???, tras la suscripci??n del presente \n" +
                "instrumento, el acceso a la plataforma web (???facturacionelectronica.dvnet.ec???), mediante un usuario y \n" +
                "clave provisional, en los t??rminos y condiciones que se detallar??n a continuaci??n.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CL??USULA CUARTA: CARACTER??STICAS DEL SERVICIO DE FACTURACI??N ELECTR??NICA.-</h1>\n" +
                "<strong>4.1</strong> ???DVNET??? prestar?? ???el servicio??? al ???Cliente??? a trav??s de un sistema en l??nea, cuyo dominio \n" +
                "ser?? ???facturacionelectronica.dvnet.ec??? (en adelante, la ???plataforma web???), mediante el cual el ???Cliente??? \n" +
                "acceder?? con un usuario y una clave provisional; los que ser??n creados y entregados al ???Usuario??? a la suscripci??n \n" +
                "del presente instrumento.</br></br>\n" +
                "<strong>4.2</strong> Dentro de la ???plataforma web??? el ???Cliente??? podr?? generar las facturas electr??nicas que requiera, \n" +
                "con un m??ximo de ________ documentos electr??nicos al a??o que conforman el plan ___________.</br></br>\n" +
                "<strong>4.3</strong> ???El Servicio??? permitir??, adem??s de la visualizaci??n en pantalla de la imagen de la factura \n" +
                "electr??nica y de su detalle, su impresi??n y descarga en formato ???xml???, conforme a los esquemas .xsd emitidos \n" +
                "por el Servicio de Rentas Internas (en adelante, ???SRI???), en el ordenador personal del ???Usuario??? para sus \n" +
                "fines pertinentes.</br></br>\n" +
                "En el caso de existir comunicaciones ordinarias, relacionadas con el presente contrato, el ???Usuario??? recibir?? \n" +
                "una copia en formato .pdf del comunicado que se le haya emitido, pudi??ndoselo descargar en el dispositivo electr??nico \n" +
                "donde se encuentre habilitada la plataforma web.</br></br>\n" +
                "<strong>4.4</strong> Para que las facturas electr??nicas emitidas por la ???plataforma web??? tengan plena vigencia \n" +
                "deber?? el ???Cliente??? contar con firma electr??nica y certificado de autorizaci??n emitido por el SRI, conforme a \n" +
                "lo que se detallar?? en la siguiente cl??usula como requisito previo para solicitar ???el servicio???.</br></br>\n" +
                "<strong>4.5</strong> La emisi??n de las facturas electr??nicas dentro de la ???plataforma web??? ser?? de \n" +
                "entero control de ???El Cliente???.</br></br>\n" +
                "<strong>4.6</strong> ???El Servicio??? no incluye el almacenamiento de informaci??n, ??nicamente el de emisi??n de \n" +
                "los documentos electr??nicos, en caso de requerir acceso a la nube se entender?? como un producto adicional, \n" +
                "el cual tendr?? un cargo a la tarifa b??sica.</br></br>\n" +
                "<strong>4.7</strong> ???DVNET??? realizar?? el mantenimiento de la ???plataforma web??? para el funcionamiento \n" +
                "adecuado de la misma.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CL??USULA QUINTA: ALTA EN EL SERVICIO DE FACTURACI??N ELECTR??NICA.-</h1>\n" +
                "<strong>5.1. Condiciones para que el ???Cliente??? acceda a ???el servicio???:</strong></br></br>\n" +
                "<strong>a)</strong> El ???Cliente??? manifest?? a ???DVNET???, a trav??s de medios f??sicos, electr??nicos y/o \n" +
                "telef??nicos, su voluntad de adquirir ???el servicio???, el cual fue plasmado mediante un formulario de \n" +
                "solicitud proporcionada por la ???Compa????a???;</br></br>\n" +
                "<strong>b)</strong> Asimismo, para que el ???Cliente??? pueda acceder a ???el servicio???, deber?? presentar, \n" +
                "junto a su petici??n o suscripci??n del presente instrumento, lo siguiente:</br>\n" +
                "<ul style= \"list-style-type: circle;\">\n" +
                "   <li>\n" +
                "   Certificado digital de firma electr??nica, el cual debe ser emitido o actualizado por cualquier \n" +
                "   entidad autorizada; tales como: Banco Central del Ecuador, Consejo de la Judicatura, Security Data o ANF AC Ecuador.\n" +
                "   </li>\n" +
                "   <li>\n" +
                "   Solicitud de autorizaci??n en el SRI para la emisi??n de comprobantes electr??nicos en ambientes \n" +
                "   de ???pruebas??? y ???producci??n???.\n" +
                "   </li>\n" +
                "</ul></br>\n" +
                "<strong>5.2. Acceso a ???el servicio???:</strong></br></br>\n" +
                "Para la contrataci??n del Servicio de Facturaci??n Electr??nica deber?? cancelar un valor por implementaci??n,\n" +
                "esto ser?? para usuarios vigentes y nuevos.</br></br>\n" +
                "Para los ???Usuarios??? que sean titulares de varios contratos de suministro en vigor, el alta en el Servicio de \n" +
                "Facturaci??n Electr??nica se aplicar?? individualmente por contrato, no aplic??ndose al resto de contratos de \n" +
                "titularidad del ???Cliente???. Asimismo, Si el ???Usuario??? realizara nuevos contratos de suministro con ???DVNET???, \n" +
                "se le ofrecer?? igualmente el alta en el servicio, para lo cual, en caso de dar su consentimiento, tendr?? que \n" +
                "suscribir el presente instrumento adhiri??ndose a los t??rminos y condiciones inherentes a ???el servicio???.</br></br>\n" +

                "El alta del ???Servicio??? se realizar?? a trav??s de la aplicaci??n de ???DVNET??? (facturacionelectronica.dvnet.ec), \n" +
                "tras la solicitud/petici??n del cliente, por cualquiera de los canales de atenci??n f??sicos o virtuales de la \n" +
                "???Compa????a???. Para ello, el ???Cliente??? deber?? identificarse en el formulario de solicitud con los datos b??sicos \n" +
                "requeridos para la creaci??n de la cuenta y contrase??a provisional que ser??n entregados al ???Cliente???, v??a correo \n" +
                "electr??nico y/o de manera f??sica, por ???DVNET??? una vez suscrito el presente instrumento.</br></br>\n" +

                "Se entender?? que desde la presentaci??n de la solicitud/petici??n y suscripci??n del presente instrumento el \n" +
                "???Cliente??? ha manifestado su voluntad, y por ende consentimiento, para ser dado de alta en el Servicio de \n" +
                "Facturaci??n Electr??nica, aceptando los t??rminos y condiciones del mismo.</br></br>\n" +

                "Cuando ???DVNET??? permita el acceso al ???servicio??? al ???Cliente???, ??ste deber?? acceder a la plataforma \n" +
                "(???facturacionelectronica.dvnet.ec???) con el usuario y contrase??a provisional proporcionados por la ???Compa????a??? \n" +
                "y proceder a realizar el cambio de contrase??a. Una vez, efectuado, el ???Usuario??? tendr?? a entera disposici??n \n" +
                "la ???plataforma web??? dentro de los fines y alcances de ???el servicio??? adquirido.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CL??USULA SEXTA: TARIFA PLAN B??SICO Y FORMA DE PAGO DE LOS SERVICIOS.-</h1>\n" +
                "Las tarifas que el ???Cliente??? debe cancelar por la prestaci??n del Servicio de Facturaci??n Electr??nica, objeto \n" +
                "del presente instrumento, a ???DVNET???, correspondiente, son los detallados en el Anexo 1, que se adjunta como habilitante en el\n" +
                "presente contrato.</br></br>\n" +
                "Las tarifas detalladas ser??n canceladas por el ???Cliente??? de manera ________ a <strong>???DVNET???</strong>, \n" +
                "mediante las siguientes formas de pago:</br></br>\n" +
                "<table class=\"tabla1\" border=\"1\"; cellpadding=\"0\"; cellspacing=\"1\";>\n" +
                "    <tr>\n" +
                "    <td><strong>Forma de pago</strong></td>\n" +
                "    <td class=\"sino\">SI</td>\n" +
                "    <td class=\"sino\">NO</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "    <td>Efectivo, pago directo a trav??s de ventanilla del prestador del servicio</td>\n" +
                "    <td></td>\n" +
                "    <td></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "    <td>D??bito autom??tico cuenta de ahorro, o corriente, o tarjeta de cr??dito</td>\n" +
                "    <td></td>\n" +
                "    <td></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "    <td>Pago en l??nea a trav??s Instituciones Financieras y Auxiliares Financieros \n" +
                "    o de Pago, autorizados y/o medios electr??nicos</td>\n" +
                "    <td></td>\n" +
                "    <td></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "    <td>Cualquier otro medio de pago determinado y autorizado por la Junta de la \n" +
                "    Pol??tica y Regulaci??n Monetaria y Financiera</td>\n" +
                "    <td></td>\n" +
                "    <td></td>\n" +
                "    </tr>\n" +
                "    </table></br></br>\n" +
                "En caso de que el ???Cliente??? desee cambiar su modalidad de pago a otra disponible, deber?? comunicarse con ???DVNET???, \n" +
                "a trav??s de cualquier medio autorizado de atenci??n, virtual o f??sico, a efecto de realizar el cambio requerido.</br></br>\n" +

                "La facturaci??n por el servicio contratado iniciar?? a partir de la fecha " + fecha + " en que \n" +
                "el ???Cliente??? ha sido dado de alta ???el servicio???, de conformidad el numeral 5.2. de la cl??usula \n" +
                "quinta del presente instrumento, m??s los impuestos que por ley correspondan. De igual manera, se facturar?? \n" +
                "??nicamente hasta la fecha de terminaci??n del Contrato. En caso que llegase a suspenderse el servicio, por \n" +
                "factores t??cnicos concernientes y/o de responsabilidad de ???DVNET???, se facturar?? por los servicios efectivamente \n" +
                "prestados y aquellos que se justifiquen de conformidad a la normativa vigente.</br></br>\n" +

                "En caso de que existan reajustes de la tarifa de los servicios contratados por el ???Cliente???, ???DVNET??? \n" +
                "notificar?? con un t??rmino de 30 d??as de anticipaci??n a la modificaci??n por cualquier medio masivo, \n" +
                "indic??ndole de manera clara las nuevas caracter??sticas, mejoras y/o tarifas a las condiciones que apliquen.</br></br>\n" +

                "El ???Cliente??? podr?? solicitar el cambio del plan contratado, al tenor de lo dispuesto en la cl??usula \n" +
                "siguiente. La aplicaci??n del nuevo servicio regir?? desde la activaci??n y bajo los nuevos t??rminos y \n" +
                "condiciones del nuevo plan.</br></br>\n" +
                "El ???Cliente??? acepta expresamente recibir la factura electr??nica emitida por ???DVNET??? por la prestaci??n de sus servicios \n" +
                "al siguiente correo electr??nico: " + partner.getEmail() + "</br></br>\n" +

                "En caso de mora, el ???Cliente??? se compromete, de forma expresa,  a cancelar el valor total adeudado m??s el \n" +
                "inter??s por mora calculado con las tasas vigentes y establecidas por ???DVNET???, de acuerdo a la fecha en que \n" +
                "debi?? efectuarse el pago, calculados desde el d??a siguiente de la fecha m??xima de pago constante en la \n" +
                "factura, hasta el d??a efecto de pago del valor adeudado, as?? como tambi??n, los cargos de cobranza generados \n" +
                "por ???DVNET???, conforme correspondan y aquellos que se justifiquen en virtud de la normativa vigente.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CL??USULA S??PTIMA: CARGOS ADICIONALES Y OTRAS TARIFAS.-</h1>\n" +
                "Se entiende como ???cargos adicionales y otras tarifas??? a todos aquellos servicios que no se encuentren \n" +
                "detallados en las cl??usulas cuarta y quinta del presente contrato, en caso de que el ???Cliente??? desee acceder \n" +
                "a ellos, en cuanto a su valor y detalle deber??n remitirse al <strong>Anexo 3</strong>, por lo que, acepta que en el caso de \n" +
                "los cargos, los valores respectivos ser??n sumados a la tarifa del plan b??sico contratado; y, con respecto \n" +
                "a las ???tarifas???, si el valor corresponde a un nuevo plan, el valor inicial ser?? reemplazado por este.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CL??USULA OCTAVA: PLAZO.-</h1>\n" +
                "El presente contrato tendr?? una permanencia m??nima de 2 (dos) a??os contados a partir de la fecha en la cual \n" +
                "???DVNET??? otorgue el alta del ???servicio??? al ???Cliente???. El tiempo mencionado es \n" +
                "aplicado para usuarios nuevos y activos.</br></br>\n" +

                "El plazo de 2 (dos) a??os ser?? renovado de manera autom??tica, e indefinida, salvo que una de las partes \n" +
                "exprese su voluntad de darlo por terminado, lo cual ser?? comunicado por escrito dentro de, por lo menos, 15 \n" +
                "(quince) d??as de anticipaci??n, sin que tal hecho de lugar al pago de ninguna clase de indemnizaci??n.</br></br>\n" +
                "En el caso en el que el ???Cliente??? quiera proceder a dar de baja ???el servicio??? deber?? cancelar todos los \n" +
                "valores adeudados por el servicio prestado hasta el momento de su terminaci??n y, adem??s, cancelar el valor \n" +
                "proporcional por el tiempo que faltase para cumplir el plazo aqu?? estipulado.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CL??USULA NOVENA: GARANT??A.-</h1>\n" +
                "Durante la vigencia del presente contrato, ???DVNET??? se compromete a emplear los esfuerzos comercialmente \n" +
                "razonables para proporcionar ???el servicio??? a ???el Cliente???, de conformidad con los est??ndares de la \n" +
                "industria generalmente aceptados.</br></br>\n" +
                "Sin perjuicio de lo expuesto, ???DVNET??? otorgar?? a el ???Cliente??? una garant??a en caso de que exista una \n" +
                "falla t??cnica en la ???plataforma web??? que sea responsabilidad directa de la ???Compa????a???. En consecuencia \n" +
                "???DVNET??? se obliga a:</br></br>\n" +

                "<strong>9.1</strong> Brindar asistencia v??a remota, con el personal t??cnico especializado de la ???Compa????a??? \n" +
                "dentro de las 24 horas de comunicado el suceso.</br></br>\n" +

                "<strong>9.2</strong> Brindar asistencia de manera presencial, con el personal t??cnico especializado de la \n" +
                "???Compa????a???, en caso de que la falla t??cnica as?? lo requiera o si pasadas las 24 horas no se hubiese solucionado.</br></br>\n" +

                "<strong>9.3</strong> En caso de que por la falta de acceso a la ???plataforma web??? el ???Cliente??? no haya podido \n" +
                "cumplir con sus obligaciones tributarias pertinentes, ???DVNET??? se obliga a prestar el servicio de manera gratuita \n" +
                "por el mismo tiempo que se haya encontrado inactivo, una vez cumplido el plazo del contrato.</br></br>\n" +
                "???DVNET??? se obliga a cumplir con la garant??a expuesta, siempre que:</br></br>\n" +

                "<strong>a.</strong> Los dispositivos electr??nicos del ???Cliente??? se encuentren en un buen estado operacional \n" +
                "que permita el funcionamiento ??ptimo de la ???plataforma web???.</br></br>\n" +
                "<strong>b.</strong> El ???Cliente??? brindar?? informaci??n pertinente para la resoluci??n de problemas y, para \n" +
                "el alojamiento en servidores propios, cualquier acceso que ???DVNET??? puede necesitar para identificar, \n" +
                "reproducir y solventar los problemas.</br></br>\n" +
                "Asimismo, ???DVNET??? est?? obligado ??nicamente a reanudar la ejecuci??n del servicio aqu?? contratado cuando se trate \n" +
                "de fallas t??cnicas de la ???plataforma web??? relacionadas con su manejo directo. ???DVNET??? no estar?? obligado a \n" +
                "cumplir con la presente garant??a en caso que el da??o o falla obedezca a un caso fortuito o fuerza mayor en los \n" +
                "t??rminos de ley, que sea originado por causas imputables al ???Cliente??? y/o un tercero o que se incumplan los \n" +
                "puntos a. y b. de la presente cl??usula.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CL??USULA D??CIMA: OBLIGACIONES Y RESPONSABILIDADES DE ???DVNET???.-</h1>\n" +
                "???DVNET??? adquiere los siguientes compromisos con el ???Cliente??? que contrate el Servicio de Facturaci??n Electr??nica:</br></br>\n" +
                "<ul style= \"list-style-type: square;\">\n" +
                "    <li>Prestar ???el servicio??? contratado por el ???Cliente??? de forma continua y permanente, durante el tiempo de \n" +
                "    duraci??n de este Contrato, en los t??rminos y condiciones aqu?? establecidos, salvo las situaciones de \n" +
                "    fuerza mayor o caso fortuito, seg??n las establecidas en el art??culo 30 de C??digo Civil ecuatoriano vigente.</br></br>\n" +
                "    </li>\n" +
                "    <li>\n" +
                "    ???DVNET??? estar?? exenta de responsabilidad en caso de mal uso de los servicios contratado por parte del \n" +
                "    ???Usuario???, por lo que no estar?? obligada al pago de indemnizaci??n de ninguna clase.</br></br>\n" +
                "    </li>\n" +
                "    <li>\n" +
                "    Informar sobre las caracter??sticas de los servicios ofertados, como promociones de planes, tarifas, \n" +
                "    precios, saldos, cargos y otros servicios informativos, a trav??s de medios f??sicos y electr??nicos.</br></br>\n" +
                "    </li>\n" +
                "    <li>\n" +
                "    En caso de que ???DVNET??? requiera modificar las caracter??sticas t??cnicas del ???Servicio??? contratado, \n" +
                "    notificar?? por escrito por medios f??sicos, electr??nicos o telef??nicos a el ???Usuario???, en un \n" +
                "    plazo no mayor a 30 (treinta) d??as de anticipaci??n a la modificaci??n.</br></br>\n" +
                "    </li>\n" +
                "    <li>\n" +
                "    ???DVNET??? deber?? permitir a el ???Usuario??? modificar su contrase??a a trav??s de la propia funcionalidad \n" +
                "    que le ofrece la web, en el momento que el ???Cliente??? lo requiera.</br></br>\n" +
                "    </li>\n" +
                "    <li>\n" +
                "    ???DVNET??? se obliga a observar las m??ximas garant??as exigidas en el ??mbito legislativo.</br></br>\n" +
                "    </li>\n" +
                "</ul>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CL??USULA UND??CIMA: OBLIGACIONES, RESPONSABILIDADES Y PROHIBICIONES DEL ???CLIENTE???.-</h1>\n" +
                "<strong>11.1. Obligaciones y responsabilidades:</strong></br></br>\n" +
                "El ???Usuario??? adquiere los siguientes compromisos al contratar el Servicio de Facturaci??n Electr??nica:</br></br>\n" +
                "<ul style= \"list-style-type: square;\">\n" +
                "   <li>Cumplir con los t??rminos y condiciones estipulados en el presente instrumento.</br></br></li>\n" +
                "   <li>Realizar un buen uso del ???servicio??? contratado y para los fines convenidos.</br></br></li>\n" +
                "   <li>El ???Cliente??? ser?? responsable de la emisi??n de las facturas electr??nicas en la ???plataforma web??? \n" +
                "   proporcionada por ???DVNET???, as?? como de llevar sus libros contables conforme a las exigencias de la \n" +
                "   normativa tributaria vigente.</br></br></li>\n" +
                "   <li>El ???Cliente??? ser?? responsable del almacenamiento de las facturas electr??nicas que emita dentro de \n" +
                "   la ???plataforma web??? en cualquier dispositivo apto para aquel fin.</br></br></li>\n" +
                "   <li>Informarse adecuadamente y de manera oportuna por los medios electr??nicos y f??sicos que ???DVNET??? \n" +
                "   pone a su disposici??n.</br></br></li>\n" +
                "   <li>Notificar a ???DVNET??? cuando ocurra la interrupci??n o da??o en la ???plataforma web??? concernientes \n" +
                "   a fallas t??cnicas relacionadas directamente con la ???Compa????a???.</br></br></li>\n" +
                "   <li>Pagar el servicio contratado y efectivamente prestado conforme lo determina el presente contrato y \n" +
                "   el ordenamiento jur??dico vigente, en las fechas de facturaci??n correspondiente.</br></br></li>\n" +
                "   <li>Cumplir con las obligaciones o resoluciones emitidas por el SRI y dem??s que se derivan del \n" +
                "   ordenamiento jur??dico vigente.</br></br></li>\n" +
                "   <li>Comunicar a ???DVNET??? cualquier cambio en la direcci??n de correo electr??nico en la que desee \n" +
                "   recibir todas las comunicaciones y documentos pertinentes para la prestaci??n de el ???servicio???. \n" +
                "   En caso de que el Cliente no cumpliera con este compromiso, ???DVNET??? no se responsabiliza del \n" +
                "   correcto env??o de los mensajes.</br></br></li>\n" +
                "   <li>Garantizar y responder, en todo caso, de la veracidad, exactitud, vigencia y autenticidad de \n" +
                "   los datos personales facilitados a trav??s del formulario de solicitud/petici??n de alta al ???servicio???.</br></br></li>\n" +
                "</ul>\n" +
                "<strong>11.2. Prohibiciones:</strong></br></br>\n" +
                "<ul>\n" +
                "   <li>El ???Cliente??? tiene estrictamente prohibido compartir su usuario y contrase??a con terceros.</br></br></li>\n" +
                "   <li>El ???Cliente??? no podr?? utilizar diversos dispositivos electr??nicos de manera paralela. Si mediante \n" +
                "   el m??todo de validaci??n interna ???DVNET??? determina diversas direcciones IP, que se encuentren utilizando \n" +
                "   ???el servicio??? de manera continua y simult??nea proceder?? a realizar el cargo respectivo.</br></br></li>\n" +
                "   <li>El ???Usuario??? no podr?? utilizar el ???servicio??? para fines il??citos, fraude o perjuicio a terceros, \n" +
                "   o que contravengan a las normativas referentes a lavado de activos.</br></br></li>\n" +
                "</ul>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CL??USULA D??CIMA SEGUNDA: CESI??N DE DERECHO.-</h1>\n" +
                "El ???Cliente??? no podr??, en ning??n caso, ceder, ni transferir, en todo o en parte los derechos y obligaciones \n" +
                "que le confiere el presente contrato, sus anexos o modificaciones de los mismos, salvo que ???DVNET??? lo \n" +
                "autorice por escrito.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CL??USULA D??CIMA TERCERA: DIVISIBILIDAD.-</h1>\n" +
                "Las partes acuerdan que en caso de que cualquier estipulaci??n de este contrato fuera declarada \n" +
                "inv??lida o nula por autoridad competente, dicha declaraci??n afectar?? ??nica y exclusivamente a dicha \n" +
                "estipulaci??n, separ??ndose de este contrato y manteni??ndose el mismo v??lido en el resto de sus partes.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CL??USULA D??CIMA CUARTA: NATURALEZA JUR??DICA Y NORMATIVA TRIBUTARIA PERTINENTE.-</h1>\n" +
                "El presente contrato de prestaci??n de servicios es de car??cter civil, el cual se fundamenta en la siguiente normativa:</br></br>\n" +
                "<strong>14.1</strong> Ley de Comercio Electr??nico, Firmas Electr??nicas y Mensajes de Datos, publicado Registro \n" +
                "Oficial Suplemento No. 557 el 17 de abril de 2002, con ??ltima reforma el 27 de agosto de 2021 y su Reglamento \n" +
                "General, los cuales regulan la prestaci??n de servicios electr??nicos a trav??s de redes de informaci??n; comercio \n" +
                "electr??nico y la protecci??n de los usuarios de estos sistemas.</br></br>\n" +
                "<strong>14.2</strong> Resoluci??n No. NAC-DGERCGC18-00000431, emitida por el SRI la cual establece los sujetos \n" +
                "pasivos que hasta el 2024 deben encontrarse, obligatoriamente, emitiendo comprobantes de venta, comprobantes \n" +
                "de retenci??n y documentos complementarios de manera electr??nica.</br></br>\n" +
                "<strong>14.3</strong> Resoluci??n No. NAC-DGERCGC18-00000175, emitida por el SRI en junio de 2018, reforma \n" +
                "resoluci??n No. NAC-DGERCGC18-00000428, que dicta las normas para la transmisi??n de informaci??n sobre \n" +
                "documento electr??nicos a trav??s de impresoras fiscales.</br></br>\n" +
                "<strong>14.4</strong> Resoluci??n No. NAC-DGERCGC18-00000233, emitida por el SRI en junio de 2018, en la \n" +
                "cual se establecen las normas para emisi??n, entrega y transmisi??n de comprobantes de venta, retenci??n y \n" +
                "documentos complementarios expedidos por sujetos pasivos autorizados, mediante el esquema de comprobantes \n" +
                "electr??nicos.</br></br>\n" +
                "<strong>14.5</strong> Resoluci??n No. NAC-DGERCGC16-00000287, emitida por el SRI en julio de 2016, en la cual \n" +
                "expide definiciones para la emisi??n de comprobantes emitidos por medios digitales o electr??nicos de pago.</br></br>\n" +
                "<strong>14.6</strong> Resoluci??n No. NAC-DGERCGC14-00790, emitida por el SRI en octubre de 2014, la cual expide \n" +
                "las normas para la emisi??n y autorizaci??n de comprobantes de venta, retenci??n y documentos complementaci??n \n" +
                "mediante comprobantes electr??nicos.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CL??USULA D??CIMA QUINTA: LIMITACI??N DE LA RESPONSABILIDAD.-</h1>\n" +
                "???DVNET??? se encuentra exento de responsabilidad en aquellos actos que no sean imputados directamente a la \n" +
                "prestaci??n de su servicio, fuerza mayor o caso fortuito, asimismo, aquellos il??citos que pudiera ocasionar \n" +
                "el ???Cliente??? y que ocasionen perjuicio a un tercero.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CL??USULA D??CIMA SEXTA: PROTECCI??N DE DATOS.-</h1>\n" +
                "<strong>16.1. Recogida y tratamiento de datos de car??cter personal:</strong></br></br>\n" +
                "La recogida y tratamiento de datos de car??cter personal recabados en el formulario de solicitud/petici??n, \n" +
                "tienen como finalidad proporcionar el servicio de facturaci??n electr??nica solicitada.</br></br>\n" +
                "De acuerdo con lo dispuesto en la Ley Org??nica de Protecci??n de Datos Personales y su normativa \n" +
                "supletoria vigente, el ???Cliente??? ha expresado su consentimiento para el tratamiento de sus datos personales, \n" +
                "para efectos de la prestaci??n del ???servicio???, es decir, las obligaciones contractuales emanadas del presente instrumento.</br></br>\n" +
                "Los datos solicitados en los formularios/peticiones, incluidos los que consten en la ???plataforma web???, tienen \n" +
                "car??cter obligatorio, salvo que en los mismos se indicase otra cosa, autorizando el ???Cliente??? su tratamiento \n" +
                "para las finalidades indicadas por ???DVNET???.</br></br>\n" +
                "En ning??n caso podr??n incluirse en el formulario de petici??n datos de car??cter personal correspondientes a \n" +
                "terceras personas, salvo que el solicitante hubiese recabado con car??cter previo su consentimiento en los \n" +
                "t??rminos exigidos por la Ley Org??nica de Protecci??n de Datos Personales, respondiendo con car??cter exclusivo del \n" +
                "incumplimiento de esta obligaci??n y cualquier otra en materia de datos de car??cter personal.</br></br>\n" +
                "<strong>16.2. Derechos de acceso, rectificaci??n, oposici??n y cancelaci??n:</strong></br></br>\n" +
                "El Cliente puede ejercitar sus derechos de acceso, rectificaci??n, cancelaci??n y oposici??n, en los t??rminos \n" +
                "establecidos legalmente, comunic??ndolo por escrito a ???DVNET???, exceptuando aquellas excepciones determinadas en \n" +
                "el art??culo 18 de Ley Org??nica de Protecci??n de Datos Personales. En dicho sentido, autorizo que ???DVNET??? proporcione \n" +
                "a las entidades pertinentes mi informaci??n crediticia, por ser una obligaci??n emanada del presente contrato.</br></br>\n" +
                "<strong>16.3. Empleo de cookies:</strong></br></br>\n" +
                "???DVNET??? puede emplear cookies, al objeto de facilitarle una gesti??n m??s ??gil y eficaz en de los servicios prestados.</br></br>\n" +
                "Las cookies son un m??todo de autentificaci??n de usuarios que permiten guardar constancia del identificador \n" +
                "asignado en el momento del registro del cliente, de manera que en lo sucesivo se evita reiterar los tr??mites \n" +
                "propios de nuevos registros.</br></br>\n" +
                "El cliente puede configurar el navegador de su ordenador para que le advierta del acceso de cookies y, en su caso, \n" +
                "de impedir la recepci??n de las mismas en el disco duro.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CL??USULA D??CIMA S??PTIMA: CONFIDENCIALIDAD.-</h1>\n" +
                "???DVNET???, su representante legal y las personas que intervengan en la ejecuci??n del presente contrato, deber??n \n" +
                "guardar la m??s estricta confidencialidad de toda informaci??n que el ???Cliente??? detalle en sus documentos electr??nicos \n" +
                "con motivo o por raz??n del presente instrumento, quedando expresamente prohibida su divulgaci??n a terceros, \n" +
                "so pena de incurrir en las responsabilidades civiles y penales que conllevan su inobservancia.</br></br>\n" +
                "La confidencialidad estipulada en esta cl??usula ser?? durante el tiempo que dure el presente contrato y subsistir??\n" +
                "en forma indefinida despu??s de su terminaci??n.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CL??USULA D??CIMA OCTAVA: TERMINACI??N DEL CONTRATO.-</h1>\n" +
                "???Las partes??? intervinientes acuerdan en forma rec??proca que el presente contrato, sus anexos y dem??s documentos \n" +
                "que forman parte integrante del mismo, podr??n ser declarados terminados en los siguientes casos:</br></br>\n" +
                "<strong>18.1 </strong>Por vencimiento del plazo del contrato, para lo cual, cualquiera de las partes podr?? \n" +
                "notificar a la otra con 15 (quince) d??as h??biles las terminaci??n por esta causa.</br></br>\n" +
                "<strong>18.2 </strong>Por falta de pago de 15 (quince) a 30 (treinta) d??as posteriores a la emisi??n de la \n" +
                "factura de ???DVNET???.</br></br>\n" +
                "<strong>18.3 </strong>Por insolvencia declarada por juez competente, concurso de acreedores, quiebra, \n" +
                "disoluci??n o liquidaci??n, de cualquiera de las partes intervinientes.</br></br>\n" +
                "<strong>18.4 </strong>Cuando por circunstancias imprevistas, t??cnicas o econ??micas, o causas de fuerza mayor o \n" +
                "caso fortuito debidamente justificado, no fuere posible o conveniente para los intereses de ???Las Partes??? \n" +
                "ejecutar total o parcialmente el contrato.</br></br>\n" +
                "<strong>18.5 </strong>Por mandato judicial o disposici??n de autoridad administrativa competente.</br></br>\n" +
                "<strong>18.6 </strong>Por incumplimiento del ???Cliente??? de las obligaciones previstas en el presente contrato.</br></br>\n" +
                "<strong>18.7 </strong>Cuando el ???Cliente??? cediera total o parcialmente el contrato sin autorizaci??n de ???DVNET???.</br></br>\n" +
                "<strong>18.8 </strong>Por muerte del ???Cliente???, lo cual, surtir?? efectos cuando documentadamente se ponga en \n" +
                "conocimiento de ???DVNET??? del hecho acaecido.</br></br>\n" +
                "<strong>18.9 </strong>Por alquilar, revender o negociar de cualquier forma el servicio contratado.</br></br>\n" +
                "<strong>18.10 </strong>Si el ???Cliente??? utiliza el ???servicio??? contratado para fines distintos o para pr??cticas \n" +
                "contrarias a la ley, las buenas costumbres, la moral o cualquier otra forma que perjudique a la ???Compa????a???</br></br>\n" +
                "La terminaci??n por mutuo acuerdo no implica la renuncia de las obligaciones, ni derechos a favor de ???DVNET??? \n" +
                "y del ???Cliente???. En este caso, el ???Usuario??? deber?? pagar todos los valores adeudados por los servicios prestados \n" +
                "y, adem??s, el valor proporcional por el tiempo que faltase para cumplir el plazo aqu?? estipulado. Asimismo, ???DVNET??? \n" +
                "proceder?? inmediatamente a suspender el servicio sin previo aviso y sin que el ???Cliente??? tenga derecho a \n" +
                "indemnizaci??n o devoluci??n de dinero alguna.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CL??USULA D??CIMA NOVENA: JURISDICCI??N, COMPETENCIA Y SOLUCI??N DE CONTROVERSIAS.-</h1>\n" +
                "???Las partes??? declaran expresamente que renuncian a fuero y domicilio, y convienen en que, para cualquier controversia \n" +
                "o diferencia que surja o se relacione con la interpretaci??n o ejecuci??n del presente contrato, se someter??n \n" +
                "a la mediaci??n del Centro de Arbitraje y Conciliaci??n de la C??mara de Comercio de Guayaquil. En caso de no \n" +
                "llegarse a un acuerdo de mediaci??n, las partes se someter??n al arbitraje en derecho, de acuerdo al procedimiento \n" +
                "establecido en la Ley de Arbitraje y Mediaci??n, en el Reglamento de dicho Centro y en las siguientes normas:</br></br>\n" +
                "<strong>19.1 </strong>Las partes designar??n 1 (UN) s??lo ??rbitro, que ser?? seleccionado por sorteo, conforme \n" +
                "lo establecido en la Ley de Arbitraje, el cual contar?? con el suplente respectivo;</br></br>\n" +
                "<strong>19.2 </strong>El ??rbitro de dicho centro efectuar?? un arbitraje administrado en Derecho y confidencial, \n" +
                "y quedar?? facultado para dictar medidas cautelares solicitando el auxilio de funcionarios p??blicos, judiciales, \n" +
                "policiales y administrativos, sin que sea necesario acudir a un juez ordinario para tales efectos;</br></br>\n" +
                "<strong>19.3 </strong>El procedimiento arbitral tendr?? lugar en las instalaciones del Centro de Arbitraje \n" +
                "y Conciliaci??n de la C??mara de Comercio de Guayaquil;</br></br>\n" +
                "<strong>19.4 </strong>Las ???PARTES??? renuncian a la jurisdicci??n ordinaria, se obligan acatar el laudo arbitral \n" +
                "y se comprometen a no interponer ning??n tipo de recurso en contra del mismo;</br></br>\n" +
                "<strong>19.5 </strong>El laudo arbitral ser?? inapelable; y,</br></br>\n" +
                "<strong>19.6 </strong>Las costas procesales que se generen por el procedimiento arbitral, tales como derechos \n" +
                "del Centro de Arbitraje y Conciliaci??n, honorarios de peritos, ??rbitros, abogados, etc., ser??n asumidos \n" +
                "??ntegra y totalmente por quien determine el ??rbitro en el laudo que expida.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CL??USULA VIG??SIMA: NOTIFICACIONES.-</h1>\n" +
                "Las notificaciones que correspondan, ser??n entregadas al ???Cliente??? a la direcci??n de correo \n" +
                "electr??nico " + partner.getEmail() + ", as?? como en su domicilio " + partner.getStreet() + ".</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CL??USULA VIG??SIMA PRIMERA: ACEPTACI??N.-</h1>\n" +
                "El ???Cliente??? acepta el presente contrato, anexos y dem??s documentos, en su totalidad, para lo cual deja constancia \n" +
                "de lo anterior y firma junto a ???DVNET??? en tres ejemplares del mismo tenor, en la ciudad \n" +
                "de " + partner.getCanton() + " a los " + dia + " d??a del mes de " + mes +" del a??o " + anio + ".</br></br>\n" +
                "Firman las partes:</br></br></br></br>\n" +
                "\n" +
                "<table class=\"tabla2\" cellpadding = 0 cellspacing=\"1\">\n" +
                "<tr>" +
                "<td align=\"center\"><img src=\"data:image/png;base64," + firmapng + "\" width=\"200\" height=\"100\"></td>" +
                "<td align=\"center\"><img src=\"data:image/png;base64," + firmauserpng + "\" width=\"200\" height=\"100\"></td>\n" +
                "</tr>\n" +
                "<tr style=\"text-align: center;\">\n" +
                "<td>Firma del ???Cliente???</td>\n" +
                "<td>Firma autorizada por ???DVNET???</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td></br></br></td>\n" +
                "<td></br></br></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>\n" +
                "RAZ??N SOCIAL</br>\n" +
                "RUC: " + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + "</br>\n" +
                "Nombre y apellido: " + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + "</br>\n" +
                "No. C??dula: " + partner.getIdentification_id() + "</br></br>\n" +
                "</br></br>\n" +
                "</td>\n" +
                "<td>\n" +
                "RUC: " + ruc + "</br>\n" +
                "Nombre y apellido: " + user.getName() + "</br>\n" +
                "No. C??dula: " + user.getIdentificacion() + "</br></br>\n" +
                "</br></br>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "\n" +
                "<h1 class=\"anexo\">\n" +
                "ANEXO 2</br>\n" +
                "AUTORIZACI??N DE D??BITO\n" +
                "</h1>\n" +
                "<strong>Aplica: </strong> Si ( &nbsp ) &nbsp No ( &nbsp )</br></br>\n" +
                "Como cliente de ???DVNET???, adicionalmente ratifico mi compromiso de mantener los pagos de mi tarjeta de cr??dito \n" +
                "y los fondos suficientes en mi cuenta corriente o de ahorros dentro de los plazos estipulados, a fin de cubrir \n" +
                "los valores cuyos d??bitos autom??ticos autorizo a trav??s del presente documento.</br></br>\n" +

                "Asimismo, expresamente me obligo a no renovar la presente autorizaci??n sin el previo consentimiento por \n" +
                "escrito de ???DVNET???, por lo que libero de toda responsabilidad a la entidad financiera, banco o la emisora \n" +
                "de la tarjeta de cr??dito por los d??bitos o cargos efectuados en base a la presente autorizaci??n. De igual manera, \n" +
                "autorizo que, en caso de p??rdida, o cualquier circunstancia por la cual fuera cambiado el n??mero de la tarjeta \n" +
                "de cr??dito, o de la cuenta antes mencionada, en caso de p??rdida, expiraci??n o cambio de n??mero, \n" +
                "me comprometo a notificar en forma inmediata a ???DVNET???, sobre el nuevo n??mero asignado; de tal manera que el \n" +
                "cambio de n??mero indicado no ser?? causa para no cancelar los valores que adeude a ???DVNET???.</br></br>\n" +

                "???DVNET??? no asume ninguna responsabilidad sobre los cargos que la instituci??n financiera por usted \n" +
                "seleccionada le cobra por prestar ese servicio a su cliente.</br></br>\n" +
                "El cliente declara conocer que la informaci??n suministrada es ver??dica y manifiesta su conocimiento \n" +
                "expreso e irrevocable a la ???DVNET???, a posible relacionada de sus derechos y obligaciones o a quien pudiese \n" +
                "ostentar a futuro a cualquier t??tulo, la calidad de acreedor de los valores adeudados por el cliente, por \n" +
                "concepto de los servicios prestados para:</br></br>\n" +
                "<ol>\n" +
                "   <li>\n" +
                "   Consultar, en cualquier tiempo, en los bur??s de informaci??n crediticia, toda la informaci??n \n" +
                "   relevante que permite a ???DVNET??? conocer el desempe??o del cliente, como deudor y su capacidad de \n" +
                "   pago, valorar el riesgo futuro en caso de concederle un cr??dito por el servicio a prestarse.\n" +
                "   </li>\n" +
                "   <li>\n" +
                "   Reportar en los bur??s de informaci??n crediticia en forma directa o por intermedio de la Superintendencia \n" +
                "   de Bancos y Seguros, datos relativos a:</br>\n" +
                "   2.1 Cumplimiento oportuno o incumplimiento de las obligaciones crediticias pasadas, \n" +
                "   presentes o futuras el cliente.</br>\n" +
                "   2.2 Informaci??n comercial, financiera y econ??mica que el cliente haya entregado o que conste en \n" +
                "   registros p??blicos, bases de datos p??blicas o documentos p??blicos.</br></br>  \n" +
                "   </li>\n" +
                "   <li>\n" +
                "   Conservar, tanto de manera interna en la ???Compa????a???, como en los bur??s de informaci??n crediticia, \n" +
                "   con las debidas autorizaciones y durante el periodo necesario, la informaci??n detallada en el numeral \n" +
                "   2 de esta declaraci??n.\n" +
                "   </li>\n" +
                "</ol>\n" +
                "Esta autorizaci??n expresa del cliente permitir?? a ???DVNET??? y a los bur??s de informaci??n crediticia divulgar \n" +
                "la informaci??n mencionada para evaluar los riesgos de conceder al cliente un cr??dito por el servicio a \n" +
                "prestar, elaborar estad??sticas, derivar, mediante modelos matem??ticos, conclusiones de ellas, y \n" +
                "dem??s fines autorizados por la ley.</br></br>\n" +

                "Si a pesar de existir la factibilidad t??cnica y comercial para prestar el servicio solicitado por el cliente, \n" +
                "???DVNET??? se reserva la facultad de negar la solicitud del servicio</br></br>\n" +
                "Lugar y fecha: " + partner.getCanton() + ", " + fecha +"</br></br></br></br>\n" +
                "<table class=\"tabla2\" cellpadding = 0 cellspacing=\"1\">\n" +
                "<tr style=\"text-align: center;\">\n" +
                "<td align=\"center\"><img src=\"data:image/png;base64," + firmapng + "\" width=\"200\" height=\"100\"></td>" +
                "<td align=\"center\"><img src=\"data:image/png;base64," + firmauserpng + "\" width=\"200\" height=\"100\"></td>\n" +
                "</tr>\n" +
                "<tr style=\"text-align: center;\">\n" +
                "<td>Firma cliente</td>\n" +
                "<td>Firma vendedor</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td></br></br></td>\n" +
                "<td></br></br></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>\n" +
                "Nombre y Apellido: " + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + "</br>\n" +
                "No. C??dula: " + partner.getIdentification_id() + "</br>\n" +
                "</td>\n" +
                "<td>\n" +
                "Nombre y Apellido: " + user.getName() + "</br>\n" +
                "No. C??dula: " + user.getIdentificacion() + "</br>\n" +
                "</td>\n" +
                "</tr>\n" +

                "<tr>\n" +
                "<td>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table></br></br></br>\n" +


                "<table class=\"tabla1\" style=\"margin-right: auto; margin-left: auto;\" border=\"1\"; cellpadding=\"0\"; cellspacing=\"1\";>\n" +
                "<tr>\n" +
                    "<td colspan=\"6\" style=\"font-size: 13px; height: 30px;\">\n" +
                        "<div style=\"position: relative;\">\n" +
                            "<img style=\"position: absolute; z-index: 2; margin-top: -6px; max-width: 22%;\"\n" +
                                //"src=\"file:///android_asset/mipmap-xxxhdpi/logo_dvnet.png\">\n" +
                                "src=\"data:image/png;base64," + logoDvnet + " \">\n" +
                        "</div>\n" +
                        "<center><strong>ANEXO 3</strong></center>\n" +
                    "</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                    "<td colspan=\"6\" style=\"font-size: 13px; height: 30px;\">\n" +
                        "<center><strong>FACTURACION ELECTRONICA</strong></center>\n" +
                    "</td>\n" +
                "</tr>\n" +
                "<tr style=\"border-right: 1px solid white; border-left: 1px solid white; font-size: 10px;\">\n" +
                    "<td colspan=\"6\">&nbsp;</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                    "<td colspan=\"6\" style=\"padding: 4px;\">\n" +
                        "<strong>Fecha de Suscripci??n del Anexo: " + dia + " de " + mes + " de " + anio + "</strong>\n" +
                    "</td>\n" +
                "</tr>\n" +
                "<tr style=\"border-right: 1px solid white; border-left: 1px solid white; font-size: 13px;\">\n" +
                    "<td colspan=\"6\">&nbsp;</td>\n" +
                "</tr>\n" +
                "<tr style=\"border-right: 1px solid white; border-left: 1px solid white; border-top: 1px solid white;\">\n" +
                    "<td colspan=\"6\">\n" +
                        "<strong>Nombre del Plan:</strong>\n" +
                    "</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                    "<td colspan=\"6\" style=\"font-size: 13px; padding: 4px;\">" + plan + "</td>\n" +
                "</tr>\n" +
                "<tr style=\"border-right: 1px solid white; border-left: 1px solid white; font-size: 10px;\">\n" +
                    "<td colspan=\"6\">&nbsp;</td>\n" +
                "</tr>\n" +

                "<tr style=\"border-right: 1px solid white; border-left: 1px solid white; border-top: 1px solid white;\">\n" +
                    "<td colspan=\"2\">\n" +
                        "<strong>Requisitos:</strong>\n" +
                    "</td>\n" +
                    "<td style=\"border-right: 1px solid white; border-left: 1px solid white; border-top: 1px solid white;>&nbsp;</td>\n" +
                    "<td colspan=\"3\">&nbsp;</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                    "<td style=\"padding: 2px\">FIRMA ELECTRONICA</td>\n" +
                    "<td style=\"width: 10%\">&nbsp;</td>\n" +
                    "<td style=\"border-top: 2px solid white; border-bottom: 1px solid white; width: 5%;\">&nbsp;</td>\n" +
                    "<td colspan=\"2\" style=\"padding: 2px\">VALOR DE IMPLEMENTACION</td>\n" +
                    "<td style=\"width: 10%\">&nbsp;</td>\n" +
                "</tr>\n" +
                "<tr style=\"border-right: 1px solid white; border-left: 1px solid white; font-size: 10px;\">\n" +
                    "<td colspan=\"2\">&nbsp;</td>\n" +
                    "<td style=\"border-right: 1px solid white; border-left: 1px solid white; border-top: 1px solid white;>&nbsp;</td>\n" +
                    "<td colspan=\"3\">&nbsp;</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                    "<td style=\"padding: 2px\">CORREO ELECTRONICO VIGENTE</td>\n" +
                    "<td style=\"width: 10%\">&nbsp;</td>\n" +
                    "<td style=\"border-top: 1px solid white; border-bottom: 1px solid white; width: 5%;\">&nbsp;</td>\n" +
                    "<td colspan=\"2\" style=\"padding: 2px\">&nbsp;</td>\n" +
                    "<td style=\"width: 10%\">&nbsp;</td>\n" +
                "</tr>\n" +
                "<tr style=\"border-right: 1px solid white; border-left: 1px solid white; font-size: 10px;\">\n" +
                    "<td colspan=\"2\">&nbsp;</td>\n" +
                    "<td style=\"border-right: 1px solid white; border-left: 1px solid white; border-top: 1px solid white;>&nbsp;</td>\n" +
                    "<td colspan=\"3\">&nbsp;</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                    "<td style=\"padding: 2px\">OTROS</td>\n" +
                    "<td style=\"width: 10%\">&nbsp;</td>\n" +
                    "<td style=\"border-top: 1px solid white; border-bottom: 1px solid white; width: 5%;\">&nbsp;</td>\n" +
                    "<td colspan=\"2\" style=\"padding: 2px\">&nbsp;</td>\n" +
                    "<td style=\"width: 10%\">&nbsp;</td>\n" +
                "</tr>\n" +
                "<tr style=\"border-right: 1px solid white; border-left: 1px solid white; border-bottom: 1px solid white; font-size: 10px;\">\n" +
                    "<td colspan=\"6\">&nbsp;</td>\n" +
                "</tr>\n" +

                "<tr style=\"border-right: 1px solid white; border-left: 1px solid white;\">\n" +
                    "<td colspan=\"2\">\n" +
                        "<strong>R??gimen:</strong>\n" +
                    "</td>\n" +
                    "<td style=\"border-right: 1px solid white; border-left: 1px solid white; border-top: 1px solid white;>&nbsp;</td>\n" +
                    "<td colspan=\"3\">&nbsp;</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                    "<td style=\"padding: 2px\">RIMPE EMPRENDEDOR</td>\n" +
                    "<td style=\"width: 10%\">&nbsp;</td>\n" +
                    "<td style=\"border-top: 1px solid white; border-bottom: 1px solid white; width: 5%;\">&nbsp;</td>\n" +
                    "<td colspan=\"2\" style=\"padding: 2px\">RUC</td>\n" +
                    "<td style=\"width: 10%\">&nbsp;</td>\n" +
                "</tr>\n" +
                "<tr style=\"border-right: 1px solid white; border-left: 1px solid white; font-size: 10px;\">\n" +
                    "<td colspan=\"2\">&nbsp;</td>\n" +
                    "<td style=\"border-right: 1px solid white; border-left: 1px solid white; border-top: 1px solid white;>&nbsp;</td>\n" +
                    "<td colspan=\"3\">&nbsp;</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                    "<td style=\"padding: 2px\">&nbsp;</td>\n" +
                    "<td style=\"width: 10%\">&nbsp;</td>\n" +
                    "<td style=\"border-top: 1px solid white; border-bottom: 1px solid white; width: 5%;\">&nbsp;</td>\n" +
                    "<td colspan=\"2\" style=\"padding: 2px\">OTRO TIPO</td>\n" +
                    "<td style=\"width: 10%\">&nbsp;</td>\n" +
                "</tr>\n" +
                "<tr style=\"border-right: 1px solid white; border-left: 1px solid white; font-size: 10px;\">\n" +
                    "<td colspan=\"2\">&nbsp;</td>\n" +
                    "<td style=\"border-right: 1px solid white; border-left: 1px solid white; border-top: 1px solid white;>&nbsp;</td>\n" +
                    "<td colspan=\"3\">&nbsp;</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                    "<td style=\"padding: 2px; font-weight: bold;\">El contrato incluye permanencia m??nima:</td>\n" +
                    "<td style=\"width: 10%\"><center>SI</center></td>\n" +
                    "<td style=\"border-top: 1px solid white; border-bottom: 1px solid white; width: 5%;\">&nbsp;</td>\n" +
                    "<td style=\"width: 10%\"><center>NO</center></td>\n" +
                    "<td style=\"padding: 2px;\"><center>TIEMPO</center></td>\n" +
                    "<td style=\"width: 10%\">&nbsp;</td>\n" +
                "</tr>\n" +
                "<tr style=\"border-right: 1px solid white; border-left: 1px solid white; border-bottom: 1px solid white; font-size: 10px;\">\n" +
                    "<td colspan=\"6\">&nbsp;</td>\n" +
                "</tr>\n" +
            "</table></br>\n" +

            "<table class=\"tabla1\" style=\"margin-right: auto; margin-left: auto;\" border=\"1\"; cellpadding=\"0\"; cellspacing=\"1\";>\n" +
                "<tr style=\"border: 1px solid white;\">\n" +
                    "<td colspan=\"6\">\n" +
                        "<strong>Tarifas(*)</strong>\n" +
                    "</td>\n" +
                "</tr>\n" +
                "<tr style=\"border-right: 1px solid white; border-left: 1px solid white;\">\n" +
                    "<td colspan=\"2\">\n" +
                        "Valores a pagar por una sola vez\n" +
                    "</td>\n" +
                    "<td style=\"border-right: 1px solid white; border-left: 1px solid white; border-top: 1px solid white;>&nbsp;</td>\n" +
                    "<td colspan=\"3\">&nbsp;</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                    "<td colspan=\"2\" style=\" text-align: right; padding: 2px; width: 50%\">\n" +
                        "Valor Instalaci??n / Configuraci??n\n" +
                    "</td>\n" +
                    "<td style=\"border-top: 1px solid white; border-bottom: 1px solid white; width: 5%;\">&nbsp;</td>\n" +
                    "<td style=\"width: 20%\">&nbsp;</td>\n" +
                    "<td colspan=\"2\" style=\"padding: 2px; border-top: 1px solid white; border-bottom: 1px solid white; border-right: 1px solid white;\">USD</td>\n" +
                "</tr>\n" +
                "<tr style=\"border-right: 1px solid white; border-left: 1px solid white; font-size: 10px;\">\n" +
                    "<td colspan=\"2\">&nbsp;</td>\n" +
                    "<td style=\"border-right: 1px solid white; border-left: 1px solid white; border-top: 1px solid white;>&nbsp;</td>\n" +
                    "<td colspan=\"3\">&nbsp;</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                    "<td colspan=\"2\" style=\"text-align: right; padding: 2px;\">\n" +
                        "Plazo para instalar/activar el servicio (horas, d??as)\n" +
                    "</td>\n" +
                    "<td style=\"border-top: 1px solid white; border-bottom: 1px solid white; width: 5%;\">&nbsp;</td>\n" +
                        "<td style=\"width: 15%\">&nbsp;</td>\n" +
                    "<td colspan=\"2\" style=\"padding: 2px; border-top: 1px solid white; border-bottom: 1px solid white; border-right: 1px solid white;\">&nbsp;</td>\n" +
                "</tr>\n" +
                "<tr style=\"border-right: 1px solid white; border-left: 1px solid white; font-size: 10px;\">\n" +
                    "<td colspan=\"2\">&nbsp;</td>\n" +
                    "<td style=\"border-right: 1px solid white; border-left: 1px solid white; border-top: 1px solid white;>&nbsp;</td>\n" +
                    "<td colspan=\"3\">&nbsp;</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                    "<td colspan=\"2\" style=\"text-align: right; padding: 2px;\">\n" +
                        "Forma de pago\n" +
                    "</td>\n" +
                    "<td style=\"border-top: 1px solid white; border-bottom: 1px solid white; width: 5%;\">&nbsp;</td>\n" +
                    "<td style=\"width: 15%\">&nbsp;</td>\n" +
                    "<td colspan=\"2\" style=\"padding: 2px; border-top: 1px solid white; border-bottom: 1px solid white; border-right: 1px solid white;\">&nbsp;</td>\n" +
                "</tr>\n" +
                "<tr style=\"border-right: 1px solid white; border-left: 1px solid white; border-bottom: 1px solid white; font-size: 10px;\">\n" +
                    "<td colspan=\"2\">&nbsp;</td>\n" +
                    "<td style=\"border-right: 1px solid white; border-left: 1px solid white; border-top: 1px solid white;>&nbsp;</td>\n" +
                    "<td colspan=\"3\">&nbsp;</td>\n" +
                "</tr>\n" +

                "<tr style=\"border-right: 1px solid white; border-left: 1px solid white; border-top: 1px solid white;\">\n" +
                    "<td>\n" +
                        "Valores pago mensual\n" +
                    "</td>\n" +
                    "<td style=\"border-left: 1px solid white;\">&nbsp;</td>\n" +
                    "<td style=\"border-right: 1px solid white; border-left: 1px solid white; border-bottom: 1px solid white;\">&nbsp;</td>\n" +
                    "<td colspan=\"3\">\n" +
                        "Detalle de otros valores\n" +
                    "</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                    "<td style=\"border-top: 1px solid white; border-left: 1px solid white; padding: 2px\">&nbsp;</td>\n" +
                    "<td style=\"width: 20%\"><center>Valor (USD)</center></td>\n" +
                    "<td style=\"border-top: 1px solid white; border-bottom: 1px solid white; width: 5%;\">&nbsp;</td>\n" +
                    "<td colspan=\"2\"><center>Item</center></td>\n" +
                    "<td style=\"width: 20%\"><center>Valor (USD)</center></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                    "<td style=\"padding: 2px; text-align: right;\">Valor mensual</td>\n" +
                    "<td style=\"width: 20%\">&nbsp;</td>\n" +
                    "<td style=\"border-top: 1px solid white; border-bottom: 1px solid white; width: 5%;\">&nbsp;</td>\n" +
                    "<td colspan=\"2\">&nbsp;</td>\n" +
                    "<td style=\"width: 20%\">&nbsp;</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                    "<td style=\"padding: 2px; text-align: right;\">Otros valores</td>\n" +
                    "<td style=\"width: 20%\">&nbsp;</td>\n" +
                    "<td style=\"border-top: 1px solid white; border-bottom: 1px solid white; width: 5%;\">&nbsp;</td>\n" +
                    "<td colspan=\"2\">&nbsp;</td>\n" +
                    "<td style=\"width: 20%\">&nbsp;</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                    "<td style=\"padding: 2px; text-align: right;\">Valor total</td>\n" +
                    "<td style=\"width: 20%\">&nbsp;</td>\n" +
                    "<td style=\"border-top: 1px solid white; border-bottom: 1px solid white; width: 5%;\">&nbsp;</td>\n" +
                    "<td colspan=\"2\" style=\"padding: 2px; text-align: right;\">Total otros valores</td>\n" +
                    "<td style=\"width: 20%\">&nbsp;</td>\n" +
                "</tr>\n" +
                "<tr style=\"border-right: 1px solid white; border-left: 1px solid white; border-bottom: 1px solid white; font-size: 10px;\">\n" +
                    "<td colspan=\"6\">&nbsp;</td>\n" +
                "</tr>\n" +
                "<tr style=\"border-right: 1px solid white; border-left: 1px solid white; font-size: 10px;\">\n" +
                    "<td colspan=\"2\" style=\"border-bottom: 1px solid white; border-right: 1px solid white;\">&nbsp;</td>\n" +
                    "<td colspan=\"4\" style=\"border-left: 1px solid white;\">&nbsp;</td>\n" +
                "</tr>\n" +

                "<tr>\n" +
                    "<td colspan=\"2\" style=\"text-align: right; border-left: 1px solid white; border-bottom: 1px solid white; padding-right: 2px\">\n" +
                        "Sitio web para consulta de tarifas: \n" +
                    "</td>\n" +
                    "<td colspan=\"4\" style=\"text-align: center; font-size: 14px;\">\n" +
                        "www.dvnet.ec\n" +
                    "</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                    "<td colspan=\"2\" style=\"padding: 2px; text-align: right; border-left: 1px solid white; padding-right: 2px\n" +
                        "border-top: 1px solid white; border-bottom: 1px solid white;\">\n" +
                        "Sitio web para consulta de indicadores de calidad: \n" +
                    "</td>\n" +
                    "<td colspan=\"4\" style=\"text-align: center; font-size: 14px;\">\n" +
                        "www.dvnet.ec\n" +
                    "</td>\n" +
                "</tr>\n" +
                "<tr style=\"border-top: white; border-left: 1px solid white; border-right: 1px solid white; font-size: 10px;\">\n" +
                    "<td colspan=\"6\">&nbsp;</td>\n" +
                "</tr>\n" +

                "<tr>\n" +
                    "<td colspan=\"6\" style=\"text-align: left; border: 1px solid white;\">\n" +
                        "<strong>Notas</strong>\n" +
                    "</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                    "<td colspan=\"6\" style=\"text-align: left; border: 1px solid white;\">\n" +
                        "* Las tarifas incluyen impuestos de ley.\n" +
                    "</td>\n" +
                "</tr>\n" +
                "<tr style=\"border-top: white; border-left: 1px solid white; border-right: 1px solid white; font-size: 12px; border-bottom: 1px solid white;\">\n" +
                    "<td colspan=\"6\">&nbsp;</td>\n" +
                "</tr>\n" +

                "<tr>\n" +
                    "<td colspan=\"2\" width=50% align=\"center\" style=\"border: 1px solid white;\">\n" +
                        "<img src=\"data:image/png;base64," + firmauserpng + "\" width=\"180\" height=\"80\">\n" +
                    "</td>\n" +
                    "<td style=\"border-top: 1px solid white; border-bottom: 1px solid white; width: 5%;\">&nbsp;</td>\n" +
                    "<td colspan=\"3\" width=50% align=\"center\" style=\"border: 1px solid white;\">\n" +
                        "<img src=\"data:image/png;base64," + firmapng + "\" width=\"180\" height=\"80\">\n" +
                    "</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                    "<td colspan=\"2\" style=\"border: 1px solid white;\">\n" +
                        "<center>PRESTADOR</center>\n" +
                    "</td>\n" +
                    "<td style=\"border-top: 1px solid white; border-bottom: 1px solid white; width: 5%;\">&nbsp;</td>\n" +
                    "<td colspan=\"3\" style=\"border: 1px solid white;\">\n" +
                        "<center>SUSCRIPTOR</center>\n" +
                    "</td>\n" +
                "</tr>\n" +
            "</table></br></br>\n" +
            "</div>";
        return smarthtml;
    }

}
