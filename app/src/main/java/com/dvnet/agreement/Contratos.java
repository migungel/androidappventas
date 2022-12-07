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
                "                <td align=\"justify\">" + agrement.getCompany() + " Es un sistema de televisión por cable que cuenta con la autorización de la ARCOTEL para administrar el sistema de televisión pagada por suscripción, que opera en la ciudad de Daule provincia del guayas, con sucursales en los lugares de: CANTONES: NOBOL, SANTA LUCIA, LOMAS DE SARGENTILLO, PEDRO CARBO, PALESTINA, COLIMES Y SALITRE. PARROQUIAS: EL LAUREL, EL LIMONAL, SABANILLA. RECINTO: EL MATE. COMUNA: PETRILLO.</td>\n" +
                "            </tr>\n" +
                "            <tr><td></br></td></tr>\n" +
                "            <tr>\n" +
                "                <td align=\"justify\">" + agrement.getCompany() + " proveerá el servicio ZONA CANTONAL "+ agrement.getCanton() +" de televisión pagada a favor de quien suscriba el presente contrato, en adelante llamado SUSCRIPTOR siempre que cumpla con todas las obligaciones que se comprometa por este contrato, y especialmente cancele cumplidamente el valor relativo a los derechos de instalación y derecho de activación que se especifica en este contrato bajo el título de PAGUE POR UNA SOLA VEZ la tarifa mensual que corresponda; así como los impuestos que hubiere lugar.</td>\n" +
                "            </tr>\n" +
                "            <tr><td></br></td></tr>\n" +
                "            <tr>\n" +
                "                <td align=\"justify\">El SUSCRIPTOR pagará las cuotas a las que se refiere el contrato MAXIMO TRES días después de la fecha de instalación, sin necesidad de notificación previa, mediante pago directo en las oficinas de DV TELEVISION DVTV S.A., pago a algún recaudador debidamente autorizado y que porte identificación de la compañía,  según sea su conveniencia. En caso de MORA DV TELEVISION DVTV S.A. queda autorizada, a suspender el servicio y el suscriptor se compromete a cancelar los costos de reconexión debiendo en todo caso cancelar los valores atrasados, caso contrario se iniciara las acciones legales correspondientes.</td>\n" +
                "            </tr>\n" +
                "            <tr><td></br></td></tr>\n" +
                "            <tr>\n" +
                "                <td align=\"justify\">DV TELEVISION DVTV S.A. no será responsable de las posibles y eventuales suspensiones del servicio, ya sea total o parcialmente, si éstas se producen por causas de fuerza mayor o por razones ajenas a la voluntad y control de DV TELEVISION DVTV S.A., sin perjuicio que esta realizara sus mejores esfuerzos para establecer el servicio en las mejores condiciones. DV TELEVISION DVTV S.A. tampoco será responsable respecto a la calidad y contenido de la programación que se transmita a través de los diferentes canales.</td>\n" +
                "            </tr>\n" +
                "            <tr><td></br></td></tr>\n" +
                "            <tr>\n" +
                "                <td align=\"justify\">El suscriptor no podrá compartir los servicios de DV TELEVISION DVTV S.A. con terceros, ya que son para uso exclusivo y disfrute del suscriptor, quien será civil y penalmente responsable de su correcta utilización en los términos de este contrato. Si ello sucediere ya sea mediante conexiones o instalaciones a otros equipos de televisión o de sonido, DV TELEVISION DVTV S.A. queda expresamente facultada a dar por terminado el contrato, sin perjuicios que pueda ejercer todas las acciones legales a la que tenga derecho.</td>\n" +
                "            </tr>\n" +
                "            <tr><td></br></td></tr>\n" +
                "            <tr>\n" +
                "                <td align=\"justify\">DV TELEVISION DVTV S.A. podrá, a su solo juicio, variar el monto de las tarifas mensuales que el suscriptor debe cancelar por sus servicios, para el efecto deberá emitir una comunicación escrita a la última dirección declarada por el suscriptor, con no menos de quince días de anticipación a la vigencia de las nuevas tarifas.</td>\n" +
                "            </tr>\n" +
                "            <tr><td></br></td></tr>\n" +
                "            <tr>\n" +
                "                <td align=\"justify\">DV TELEVISION DVTV S.A. es propietario del cable con el que se realiza la instalación, el cual no puede exceder de 40 metros por cada una; En caso de exceder esta medida, DV TELEVISION DVTV S.A. tendrá derecho a facturar el adicional utilizado al suscriptor, y también tendrá  derecho a retirarlo en caso de que se  dé por terminado el contrato por cualquiera de las dos partes.</td>\n" +
                "            </tr>\n" +
                "            <tr><td></br></td></tr>\n" +
                "            <tr>\n" +
                "                <td align=\"justify\">“Autorizo(amos) expresa e irrevocablemente a DV TELEVISION DVTV S.A. o quien sea el futuro cesionario, beneficiario o acreedor del crédito solicitado o del documento o título cambiario que lo respalde para que obtenga cuantas veces sean necesarias, de cualquier fuente de información, incluidos los burós de crédito, mi información de riesgos crediticios, de igual forma DV TELEVISION DVTV S.A. o quien sea el futuro cesionario, beneficiario o acreedor del crédito solicitado o del documento o título cambiario que lo respalde queda expresamente autorizado para que pueda transferir o entregar dicha información a los burós de crédito y/o a la Central de Riesgos si fuere pertinente”.</td>\n" +
                "            </tr>\n" +
                "            <tr><td></br></td></tr>\n" +
                "            <tr>\n" +
                "                <td align=\"justify\">La duración mínima del presente contrato es de UN AÑO (365 días).</td>\n" +
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
                "                <td align=\"center\">Dirección: 9 de Octubre y José Vélez-Daule</td>\n" +
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
                "            <tr><td align=\"right\">¡Abriendo horizontes!</td></tr>\n" +
                "            <tr><td></br></td></tr>\n" +
                "            <tr><td>\n" +
                "                    <table width=100% style=\"font-size:12px;\">\n" +
                "                        <tr>\n" +
                "                            <td width=20%>CONTRATO Nº-</td>\n" +
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
                "\t\t<tr><td align=\"justify\">En la ciudad de " + agrement.getCanton() +", el día de hoy "+ fecha +", comparecen, a la suscripción del presente acuerdo, en adelante \"EL CONTRATO\", por una parte, la persona jurídica, DV TELEVISION DVTV S.A., al que en adelante y para efectos del presente contrato se le denominará \"EL PRESTADOR\"; y, por otra parte, el/la señor (a) " + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + " con Cédula " + partner.getIdentification_id() + " a la que en adelante y para efectos de este convenio se le denominará el \"SUSCRIPTOR\", al tenor de las siguientes clausulas:</td></tr>\t\n" +
                "\t\t<tr><td></br></td></tr>\t\t\n" +
                "\t\t<tr><td><strong>PRIMERA: DATOS DE LOS COMPARECIENTES.</strong></td></tr>\n" +
                "\t\t<tr><td>El PRESTADOR declara la siguiente información:</td></tr>\n" +
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
                "\t\t<tr><td>El SUSCRIPTOR declara la siguiente información:</td></tr>\n" +
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
                "\t\t<tr><td>EL PRESTADOR se compromete a proporcionar al ABONADO el/los siguiente (s) SERVICIO (s), para lo cual el PRESTADOR dispone de los correspondientes títulos habilitantes otorgados por ARCOTEL, de conformidad con el ordenamiento jurídico vigente:</td></tr>\n" +
                "\t\t<tr width=100%>\n" +
                "\t\t\t<td align=\"center\">\n" +
                "\t\t\t\t<table width=90% border=\"1\" cellspacing=\"0\" cellpadding=\"1\" style=\"font-size: 10ptx\">\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=65%>•\tMóvil avanzado (SMA)</td>\n" +
                "\t\t\t\t\t\t<td width=15%></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=65%>•\tMóvil avanzado a través de operador móvil virtual (OMV)</td>\n" +
                "\t\t\t\t\t\t<td width=15%></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=65%>•\tTelefonía Fija</td>\n" +
                "\t\t\t\t\t\t<td width=15%></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=65%>•\tTelecomunicaciones por satélite</td>\n" +
                "\t\t\t\t\t\t<td width=15%></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=65%>•\tValor agregado</td>\n" +
                "\t\t\t\t\t\t<td width=15%></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=65%>•\tAcceso a internet</td>\n" +
                "\t\t\t\t\t\t<td width=15% align=\"center\">X</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=65%>•\tTroncalizados</td>\n" +
                "\t\t\t\t\t\t<td width=15%></td>\n" +
                "\t\t\t\t\t</tr>\t\t\t\t\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=65%>•\tComunales</td>\n" +
                "\t\t\t\t\t\t<td width=15%></td>\n" +
                "\t\t\t\t\t</tr>\t\t\t\t\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=65%>•\tAudio y Video por suscripción</td>\n" +
                "\t\t\t\t\t\t<td width=15%></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=65%>•\tPortador</td>\n" +
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
                "\t\t\t\tEl presente contrato tendrá una duración de <span style=\"font-weight:bold;\">1 año</span> y entrará en vigencia, a partir de la fecha de instalación y prestación efectiva del servicio. La fecha inicial considerada para facturación para cada uno de los servicios contratados será la de activación del servicio.\n" +
                "\t\t\t\tLas partes se comprometen a respetar el plazo de vigencia pactado, sin perjuicio de que el SUSCRIPTOR pueda darlo por terminado unilateralmente, en cualquier tiempo, previa notificación física o electrónica, con por lo menos quince (15) días de anticipación, conforme lo dispone la Ley Orgánica de Telecomunicaciones y de Defensa del consumidor y sin que para ello esté obligado a cancelar multas o recargos de valores de ninguna naturaleza.\n" +
                "\t\t\t\tEl SUSCRIPTOR acepta la renovación automática sucesiva del contrato en las mismas condiciones de este contrato, independientemente de su derecho a terminar la relación contractual conforme la legislación aplicable, o solicitar en cualquier tiempo, con hasta quince (15) días de antelación a la fecha de renovación, su decisión de no renovación.\n" +
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
                "\t\t\t\t<br>3.1 El Anexo A \"Condiciones de Contratación\".\n" +
                "\t\t\t\t<br>3.2 El Anexo B \"Compra o arriendo de equipos\".\n" +
                "\t\t\t\t<br>3.3 El Anexo C “Aceptación de uso de datos personales”\n" +
                "\t\t\t\t<br>3.4 El Anexo D “Servicios adicionales, suplementarios y promociones”\n" +
                "\t\t\t\t<br>3.5 Las Actas de instalación y activación, de todos los servicios que se presten y que respaldarán los servicios ofrecidos.\n" +
                "\t\t</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td><strong>QUINTA: PERMANENCIA MÍNIMA.</strong></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">El SUSCRIPTOR se acoge al período de permanencia mínima de ________ en la prestación del servicio contratado.</td></tr>\n" +
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
                "\t\t\t\tLos beneficios de la permanencia mínima son:\n" +
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
                "\t\t<tr><td align=\"justify\">La permanencia mínima se acuerda, sin perjuicio de que el SUSCRIPTOR conforme lo determina la LEY ORGÁNICA DE TELECOMUNICACIONES, pueda dar por terminado el contrato en forma unilateral y anticipada, y en cualquier tiempo, previa notificación por medios físicos o electrónicos al PRESTADOR, con por lo menos quince (15) días de anticipación, para cuyo efecto deberá proceder a cancelar los servicios efectivamente prestados o por los bienes solicitados y recibidos, así como también el costo de instalación que faltare hasta la terminación del contrato.</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\t\t\n" +
                "\t\t<tr><td><strong>SEXTA: TARIFA Y FORMA DE PAGO.</strong></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">Las tarifas o valores mensuales a ser cancelados por cada uno de los servicios contratados por el SUSCRIPTOR se describe en la ficha del servicio del ANEXO A y, el pago se realizará, de la siguiente forma:</td></tr>\n" +
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
                "\t\t\t\t\t<td width=70%>Débito automático cuenta de ahorro o corriente</td>\n" +
                "\t\t\t\t\t<td width=10%><strong></strong></td>\n" +
                "\t\t\t\t\t<td width=10%><strong></strong></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td width=70%>Pago en ventanillas de locales autorizados</td>\n" +
                "\t\t\t\t\t<td width=10% align=\"center\"><strong><strong>X</strong></strong></td>\n" +
                "\t\t\t\t\t<td width=10%><strong></strong></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td width=70%>Debito con tarjeta de crédito</td>\n" +
                "\t\t\t\t\t<td width=10%><strong></strong></td>\n" +
                "\t\t\t\t\t<td width=10%><strong></strong></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td width=70%>Transferencia vía medios electrónicos</td>\n" +
                "\t\t\t\t\t<td width=10%><strong></strong></td>\n" +
                "\t\t\t\t\t<td width=10%><strong></strong></td>\n" +
                "\t\t\t\t</tr>\t\t\t\t\n" +
                "\t\t\t</table>\n" +
                "\t\t</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">\n" +
                "\t\t\t\tLa tarifa correspondiente al servicio contratado y efectivamente prestado, estarán siempre dentro de los techos tarifarios señalados por la ARCOTEL y en los títulos habilitantes correspondientes, en caso de que se establezcan, de conformidad con el ordenamiento jurídico vigente.\n" +
                "\t\t\t\tEn caso de que el SUSCRIPTOR desee cambiar su modalidad de pago a otra de las disponibles, deberá comunicar al prestador de servicio con quince (15) días de anticipación. El prestador del servicio, luego de haber sido comunicado, instrumentará la nueva forma de pago.\n" +
                "\t\t</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td><strong>SEPTIMA: COMPRA, ARRENDAMIENTO DE EQUIPOS.</strong></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">Cuando sea procedente el arrendamiento o adquisición de equipos, por parte del abonado, toda la información pertinente será detallada en un anexo adicional, suscrito por el abonado el cual contendrá los temas relacionados a las condiciones de los equipos adquiridos/arrendados, entre otras características deberá incluir: Cantidad, Precio, Marca, Estado, y las condiciones de tal adquisición o arrendamiento, particularmente el tiempo en el que se pagara el arrendamiento o la compra del equipo, el valor mensual a cancelar o las condiciones de pago.</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td><strong>OCTAVA: USO DE INFORMACIÓN PERSONAL.</strong></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">Los datos personales que los usuarios proporcionen a los prestadores de servicios del régimen general de telecomunicaciones, no podrán ser usados para la promoción comercial de servicios o productos, inclusive de la propia operadora; salvo autorización y consentimiento expreso del abonado/suscriptor, el que constara como instrumento separado y distinto al presente contrato de prestación del servicio (contrato de adhesión) a través de medios físicos o electrónicos. En dicho instrumento se deberá dejar constancia expresa de los datos personales o información que están expresamente autorizados; el plazo de autorización y el objetivo que esta utilización persigue, conforme lo dispuesto en el artículo 121 del reglamento general a la ley orgánica de Telecomunicaciones.</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td><strong>NOVENA: RECLAMO Y SOPORTE TÉCNICO.</strong></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">El SUSCRIPTOR puede solicitar soporte técnico o la formulación de reclamos por el servicio contratado a través de los siguientes medios:</td></tr>\n" +
                "\t\t<tr><td align=\"center\">\n" +
                "\t\t\t<table width=90% style=\"font-size: 10ptx\">\n" +
                "\t\t\t\t<tr><td>-\tMedio electrónico: www.dvtelevision.com, servicioalcliente@dvtelevision.com.</td></tr>\n" +
                "\t\t\t\t<tr><td>-\tOficinas de atención a usuarios: 9 DE OCTUBRE 62A Y JOSE VELEZ.</td></tr>\n" +
                "\t\t\t\t<tr><td>-\tHorarios de atención: 09:00 – 18:00 LUNES- VIERNES/ 10:00 – 14:00 PM SABADOS.</td></tr>\n" +
                "\t\t\t\t<tr><td>-\tTeléfonos: 042797023 042798122 0984929651.</td></tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">Para la atención de reclamos NO resueltos por el PRESTADOR, el SUCRIPTOR también podrá presentar sus denuncias y reclamos ante la Agencia de Regulación y Control de las Telecomunicaciones (ARCOTEL) por cualquiera de los siguientes canales de atención:</td></tr>\n" +
                "\t\t<tr><td align=\"center\">\n" +
                "\t\t\t<table width=90% style=\"font-size: 10ptx\">\n" +
                "\t\t\t\t<tr><td>-\tAtención presencial: oficinas del tas coordinaciones zonales de la ARCOTEL</td></tr>\n" +
                "\t\t\t\t<tr><td>-\tPBX Directo Matriz, Coordinaciones Zonales y oficinas técnicas.</td></tr>\n" +
                "\t\t\t\t<tr><td>-\tCall Center: llamadas gratuitas al número 1800 567-567.</td></tr>\n" +
                "\t\t\t\t<tr><td>-\tCorreos tradicional (Oficios).</td></tr>\n" +
                "\t\t\t\t<tr><td>-\tPágina web.\n" +
                "\t\t\t\t\t<br>\t\twww.arcotel.gob.ec\n" +
                "\t\t\t\t\t<br>\t\thttp://reclamoconsumidor.arcotel.gob.ec/osTicket/\n" +
                "\t\t\t\t</td></tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td><strong>DÉCIMA: TERMINACIÓN DEL CONTRATO.</strong></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">Las partes acuerdan recíprocamente que EL CONTRATO se terminará por la ejecución total de las obligaciones derivadas del mismo; por acuerdo mutuo y que conste por escrito, o cuando ocurra alguna de las siguientes causales:</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">12.1 Si cualquiera de las partes no cumple con las obligaciones del presente contrato, como son la calidad y disponibilidad del servicio acordados, el pago del precio convenido, mal uso de los equipos instalados y/o servicios prestados, etc., siempre que la parte responsable no adopte las soluciones pertinentes dentro de los quince (15) días hábiles siguientes al recibo de requerimiento escrito por la otra.</td></tr>\n" +
                "\t\t<tr><td align=\"justify\">12.2 Cuando EL SUSCRIPTOR use los servicios y/o los equipos para fines distintos a los contratados, cuando permita el uso de los mismos a terceros y/o cuando explote con ellos servicios de telecomunicaciones ilegales, y/o no autorizados por el Estado ecuatoriano, sin perjuicio de la denuncia del cometimiento de infracciones que se comentan a la Ley Orgánica de Telecomunicaciones y que deban ser sancionadas por ARCOTEL.</td></tr>\n" +
                "\t\t<tr><td align=\"justify\">12.3 En el evento de terminar el Permiso otorgado a EL PRESTADOR por el Estado Ecuatoriano.</td></tr>\n" +
                "\t\t<tr><td align=\"justify\">Sí el Contrato termina anticipadamente por causas atribuibles al SUSCRIPTOR, EL SUSCRIPTOR deberá pagar a EL PRESTADOR los servicios efectivamente prestados o los bienes solicitados y recibidos, hasta la terminación del contrato.</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td><strong>DÉCIMA PRIMERA: CONFIDENCIALIDAD Y PROPIEDAD INTELECTUAL.</strong></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">\n" +
                "\t\t\t\tEL PRESTADOR declara que sus invenciones, ideas, conceptos, secretos comerciales, información confidencial o información no divulgada y cualquier asunto relacionado con la Propiedad intelectual (registrada o no registrada), son solamente de su propiedad. EL PRESTADOR acuerda compartir y discutir ciertas partes de sus invenciones, ideas, conceptos, secretos comerciales, información confidencial o información no divulgada y cualquier asunto relacionado con la Propiedad intelectual (registrada o no registrada) de acuerdo a las necesidades del contrato de prestación de servicios y otros documentos integrantes del mismo con el SUSCRIPTOR, sin que esto signifique de ninguna manera una cesión, transferencia, licencia de uso o cualquier otra forma por la que EL PRESTADOR ceda o transfiera cualquiera de sus modalidades de la Propiedad intelectual (registrada o no registrada) a favor del SUSCRIPTOR.\n" +
                "\t\t\t\tTanto EL PRESTADOR como el SUSCRIPTOR guardarán estricta confidencialidad sobre el contenido del presente CONTRATO. Es obligación de las mismas informar a sus funcionarios, empleados, colaboradores, subcontratistas y/o terceros relacionados con el presente CONTRATO, la obligación de mantener la reserva sobre el contenido y condiciones del mismo; sobre todo de aquellos documentos y/o información que sean calificados como confidenciales.\n" +
                "\t\t</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td><strong>DÉCIMA SEGUNDA: CONTROVERSIAS.</strong></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">\n" +
                "\t\t\t\tLas diferencias que surjan de la ejecución del presente contrato, podrán ser resueltas por mutuo acuerdo entre las partes, sin perjuicio de que el ABONADO O SUSCRIPTOR acuda con su reclamo, queja o denuncia, ante las autoridades administrativas que corresponda. De no llegar a una solución, cualquiera de las partes podrá acudir ante los jueces competentes.\n" +
                "\t\t</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">\n" +
                "\t\t\t\tNo obstante lo indicado, las partes pueden pactar adicionalmente, someter sus controversias ante un centro de mediación o arbitraje, si así lo deciden expresamente, en cuyo caso el abonado/suscriptor deberá señalarlo en forma expresa.\n" +
                "\t\t</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">\n" +
                "\t\t\t\tEL ABONADO, en caso de conflicto, acepta someterse a la mediación o arbitraje (puede significar costos en los que debe incurrir el ABONADO/SUSCRIPTOR- no aplica a empresas publicas prestadoras de servicios de telecomunicaciones)\n" +
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
                "\t\t<tr><td align=\"center\">…………………………………………………………………………………………………………</td></tr>\n" +
                "\t\t<tr><td align=\"center\">Firma de aceptación de sujeción a arbitraje</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td><strong>DECIMA TERCERA: NOTIFICACIÓN Y DOMICILIO.</strong></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">Las notificaciones que corresponda, serán entregadas en el domicilio de cada una de las partes señalado en la cláusula PRIMERA del presente contrato. Cualquier cambio de domicilio debe ser comunicado por escrito a la otra parte en un plazo de 10 días, a partir del día siguiente en que el cambio se efectúe.</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td><strong>DECIMA CUARTA: EMPAQUETAMIENTO DE SERVICIOS.</strong></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">La contratación incluye empaquetamiento de servicios.</td></tr>\n" +
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
                "\t\t<tr><td align=\"justify\">Los paquetes de servicios y los beneficios de estos, así como sus tarifas son:</td></tr>\n" +
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
                "\t\t<tr><td align=\"justify\">Es parte integrante del presente contrato el anexo 1 que contiene las ´´condiciones particulares del servicio´´, así como los demás anexos y documentos que se incorporen de conformidad con el ordenamiento jurídico.</td></tr>\n" +
                "\t\t<tr><td></br></td></tr>\n" +
                "\t\t<tr><td align=\"justify\">El abonado acepta el presente contrato con sus términos y condiciones y demás documentos ANEXOS para lo cual deja constancia de lo anterior y firman junto con el PRESTADOR del mismo tenor, en la ciudad de: " + agrement.getCanton() + " a los " + dia + " días del mes " + mes + " del año "+ anio +".</td></tr>\n" +
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
                "                <tr><td >Fecha de Suscripción del Anexo: " + dia + " de "+ mes + " de " + anio + "</td></tr>\n" +
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
                "                            <td width=80%>Fibra óptica</td>\n" +
                "                            <td width=20% align=\"center\"><strong>X</strong></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=80%>Inalámbrico</td>\n" +
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
                "                            <td width=80%>Cibercafé</td>\n" +
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
                "                            <td width=80%>Mínima efectiva de bajada</td>\n" +
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
                "                            <td width=80%>Mínima efectiva de subida</td>\n" +
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
                "                        <tr><td>Nivel de compartición (1:1, 2:1, 4:1, 8:4)</td></tr>                        \n" +
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
                "                        <tr><td>El contrato incluye permanencia mínima:</td></tr>\n" +
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
                "                                    <tr><td align=\"center\">1 AÑO</td></tr>\n" +
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
                "                        <tr><td>Beneficios por permanencia mínima</td></tr>                        \n" +
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
                "                    <td width=40%>Cuentas de correo electrónico</td>\n" +
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
                "                            <tr><td align=\"right\">Valor Instalación / Configuración</td></tr>\n" +
                "                            <tr><td align=\"right\">Plazo para instalar/activar el servicio (horas, días)</td></tr>\n" +
                "                            <tr><td align=\"right\">Valor Reactivación del servicio</td></tr>\n" +
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
                "                                <td align=\"center\" width=50%>Ítem</td>\n" +
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
                "        <tr><td align=\"center\"><strong>COMPRA, PRÉSTAMOS O ARRIENDO DE EQUIPOS</strong></td></tr>        \n" +
                "    </table>\n" +
                "    <table width=100% style=\"font-size:12px; font-family: Arial\"> \n" +
                "        <tr><td></td></tr>\n" +
                "        <tr><td>\n" +
                "            <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                <tr><td >Fecha de Suscripción del Anexo:" + dia + " de " + mes + " de " + anio + "</td></tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "        <tr><td></br></td></tr>\n" +
                "        <tr><td>Equipos provistos por el PRESTADOR necesarios para la prestación del servicio:</td></tr>\n" +
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
                "                    <td align=\"center\" width=25% >DESCRIPCIÓN</td>\n" +
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
                "                    <td width=30%>Exoneración del pago de instalación:</td>\n" +
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
                "                    <td width=30%>Valor de instalación:</td>\n" +
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
                "        <td><tr>El SUSCRIPTOR acepta la permanencia mínima de ……………… Meses por la exoneración del valor de instalación y en el caso de dar por terminado anticipadamente el contrato,</td></tr>\n" +
                "        <td><tr>\n" +
                "            <table width=100%>\n" +
                "                <tr>\n" +
                "                    <td width=30%>Aceptación</td>\n" +
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
                "                    <td align=\"center\" width=25% >DESCRIPCIÓN</td>\n" +
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
                "                <td align=\"center\" width=25% >DESCRIPCIÓN</td>\n" +
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
                "<td><tr>En caso de terminación anticipada del contrato por parte del SUSCRIPTOR, este acepta realizar el pago del restante adeudado por concepto de venta de equipos</td></tr>\n" +
                "<td><tr>\n" +
                "    <table width=100%>\n" +
                "        <tr>\n" +
                "            <td width=30%>Aceptación</td>\n" +
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
                "        <tr><td align=\"center\"><strong>ACEPTACIÓN DE USO DE DATOS PERSONALES</strong></td></tr>        \n" +
                "    </table>\n" +
                "    <table width=100% style=\"font-size:12px; font-family: Arial\"> \n" +
                "        <tr><td></td></tr>\n" +
                "        <tr><td>      \n" +
                "            <table width=100% border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" +
                "                <tr><td >Fecha de Suscripción del Anexo:" + dia + " de " + mes + " de " + anio + "</td></tr>\n" +
                "            </table>\n" +
                "        </td></tr>\n" +
                "        <tr><td></br></td></tr>\n" +
                "        <tr><td>El SUSCRIPTOR autoriza expresamente al PRESTADOR a hacer uso de su información personal y de contacto para fines de índole comercial propios de la empresa prestadora y que consiste en la difusión de mensajes publicitarios y comerciales relativos a servicios de telecomunicaciones adicionales que el PRESTADOR ofrece a sus clientes.</td></tr>\n" +
                "        <tr><td></br></td></tr>\n" +
                "        <tr><td>En cualaquier momento el abonado o suscriptor podra revocar  su consentimiento, sin que el prestador pueda condicionar o establecer requesitos para tal fin, adicionales a simple voluntad del abonado, suscriptor o cliente.</td></tr>\n" +
                "        <td><tr>\n" +
                "            <table width=100%>\n" +
                "                <tr>\n" +
                "                    <td width=30%>Aceptación</td>\n" +
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
                "                <tr><td >Fecha de Suscripción del Anexo:" + dia + " de " + mes + " de " + anio + "</td></tr>\n" +
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
                "                    <td align=\"center\" width=33% >PROMOCIÓN</td>\n" +
                "                    <td align=\"center\" width=33% >VALOR COMERCIAL</td>\n" +
                "                    <td align=\"center\" width=33% >VALOR PROMOCIÓN</td>                    \n" +
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
                "                <td width=30%>Aceptación</td>\n" +
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
                "            <tr><td align=\"justify\">Yo, " + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + " con C.I. " + partner.getIdentification_id() + ", siendo mayor de edad, identificado/a como aparece al pie de mi firma, actuando en nombre propio, por medio del presente escrito manifiesto, lo siguiente: PRIMERO: Que debo y pagaré, incondicional y solidariamente a la orden de DV TELEVISION DVTV S.A., o a la persona natural o jurídica a quien el mencionado acreedor ceda o endose sus derechos sobre este pagaré, la suma de <span style=\"font-weight:bold;\">DOSCIENTOS DÓLARES AMERICANOS. ($ 200, oo)</span>,  DÓLARES AMERICANOS MONEDA LEGAL ECUATORIANA. SEGUNDO: Que el pago total de la mencionada obligación se efectuará en un solo valor de contado, el día _____ del mes de ________________ del año _____________ en las dependencias de DV TELEVISION DVTV S.A. Ubicada en la ciudad de DAULE cantón Daule , en las calles 9 de Octubre y José Vélez , o en su cuenta bancaria n. 30618793 del Banco Guayaquil. TERCERO: Que en caso de mora pagaré a DV TELEVISION DVTV S.A. o a la persona natural o jurídica a quien el mencionado acreedor ceda o endose sus derechos, intereses de mora a la más alta tasa permitida por la Ley, desde el día siguiente a la fecha de exigibilidad del presente pagaré, y hasta cuando su pago total se efectúe. CUARTO: Expresamente declaro excusado el protesto del presente pagaré y los requerimientos judiciales o extrajudiciales para la constitución en mora. QUINTO: En caso de que haya lugar al recaudo judicial o extrajudicial de la obligación contenida en el presente título valor será a mi cargo las costas judiciales y/o los honorarios que se causen por tal razón.</td></tr>\n" +
                "            <tr><td align=\"justify\">En constancia de lo anterior, se suscribe en la ciudad de " + partner.getCanton() + " a los "+ dia +" días del mes de " + mes + " del año " + anio + ".</td></tr>\n" +
                "            <tr><td align=\"center\"><img src=\"data:image/png;base64," + firmapng + "\" width=\"200\" height=\"100\"></td></tr>\n" +
                "            <tr><td align=\"center\">" + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + "</td></tr>\n" +
                "            <tr><td align=\"center\">" + partner.getIdentification_id() + "</td></tr>\n" +
                "            <tr><td></br></td></tr>\n" +
                "        </table>\n" +
                "        <table width=100% style=\"font-size:20px; font-weight:bold;\">\n" +
                "            <tr><td align=\"center\">COMPROMISO DE ENTREGA DE EQUIPOS</td></tr>\n" +
                "        </table>\n" +
                "        <table width=100% style=\"font-size:13px;\">\n" +
                "            <tr><td align=\"justify\">Estimado SR. (a) " + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + " con C.I. " + partner.getIdentification_id() + " le recordamos que los equipos instalados dentro de su domicilio son de propiedad de <span style=\"font-weight:bold;\">DV NET</span>, Los cuales deberán ser devueltos a nuestras oficinas una vez que el o los servicios contratados sean suspendidos por terminación voluntaria o por suspensión por falta de pago.</td></tr>\n" +
                "            <tr><td align=\"justify\">Si se suspenden por falta de pago puede reactivar los servicios dentro de los 5 primeros días del siguiente mes antes que se genera la orden de retiro, una vez generada personal técnico ira a su domicilio a retirar los equipos, si no son devueltos se procederá tomar acciones legales y hacer efectivo el <span style=\"font-weight:bold;\">PAGARÉ.</span></td></tr>\n" +
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
                "        <tr><td align=\"justify\">Nos permitimos advertirles sobre la necesidad de que se tomen las medidas necesarias para garantizar la seguridad tecnológica frente a posibles vulnerabilidades en sus instalaciones como son:</td></tr>\n" +
                "        <tr><td></br></td></tr>\n" +
                "        <tr><td align=\"justify\">-\tEn sus centrales privadas PBX, evitar colocar claves de acceso al sistema de larga distancia iguales al número de extensión.</td></tr>\n" +
                "        <tr><td></br></td></tr>\n" +
                "        <tr><td align=\"justify\">-\tControlar los privilegios para acceder remotamente a sus centrales telefónicas privadas para el uso de llamadas internacionales.</td></tr>\n" +
                "        <tr><td></br></td></tr>\n" +
                "        <tr><td align=\"justify\">-\tControlar el consumo de llamas hacia destinos considerados de alto costo.</td></tr>\n" +
                "        <tr><td></br></td></tr>\n" +
                "        <tr><td align=\"justify\">-\tColocar las seguridades tecnológicas apropiadas cuando se utilice software libre para sus centrales privadas PBX que utilicen tecnología IP y que tengan acceso a internet.</td></tr>        \n" +
                "        <tr><td></br></td></tr>\n" +
                "        <tr><td></br></td></tr>\n" +
                "        <tr><td align=\"justify\"><strong>DVNET – DVTELEVISION S.A.</strong> no será responsable por el uso, consumo o violaciones indebidas que se hagan del servicio contratado. El cliente será responsable por el uso, consumo o violaciones del servicio e incluso por el mal uso del mismo en caso de que no haya tomado las medidas necesarias para garantizar la seguridad tecnológica frente a posibles vulnerabilidades en sus instalaciones.</td></tr>\n" +
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
//                "<h1 class=\"titulo\">CONTRATO DE PRESTACIÓN DE SERVICIOS DE FACTURACIÓN ELECTRÓNICA</h1>\n" +
//                "Conste por el presente documento un Contrato de Prestación de Servicios de Facturación Electrónica, que en forma libre y voluntaria, se celebra al tenor de las siguientes cláusulas:\n" +
//                "<h1 class=\"subtitulo\">CLÁUSULA PRIMERA: OTORGANTES.-</h1>\n" +
//                "Comparecen a la celebración y suscripción del presente contrato, las siguientes partes:</br></br>\n" +
//                "<strong>1.1.</strong> Por una parte, la compañía <strong>DV TELEVISION DVTV S.A.</strong>, con Registro Único de Contribuyentes (RUC) No. 0992929170001, \n" +
//                "debidamente representado por su Gerente General, el señor Raúl Daniel Zamora Vera, y como tal representante legal, \n" +
//                "quien suscribe, parte a la que en adelante se la podrá denominar indistintivamente como “DVNET” o la “Compañía”; y,</br></br>\n" +
//                "<strong>1.2.</strong> Por otra parte: </br>\n" +
//                "Nombre/razón social: " + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + "</br>\n" +
//                "Cédula/RUC: " + partner.getIdentification_id() + " E-mail: " + partner.getEmail() + "</br>\n" +
//                "Dirección: " + partner.getStreet() + "</br>\n" +
//                "Provincia: " + partner.getProvincia() + " Ciudad: " + partner.getCanton() + " Cantón: " + partner.getCanton() + " Parroquia:_______</br>\n" +
//                "Dirección donde será prestado el servicio: " + partner.getStreet() + "</br></br>\n" +
//                "A quienes para efectos del presente, en su conjunto, se las denominará como “Las Partes”.</br></br>\n" +
//                "<h1 class=\"subtitulo\">CLÁUSULA SEGUNDA: ANTECEDENTES.-</h1>\n" +
//                "<strong>2.1.</strong> “DVNET” es una compañía anónima legalmente constituida en el Ecuador, especializada, \n" +
//                "entre otros, en actividades de suministro de telecomunicaciones, acceso a televisión por cable e internet, \n" +
//                "así como aplicaciones derivadas.</br></br>\n" +
//                "<strong>2.2.</strong> Dentro de la prestación de los productos mencionados, “DVNET” ofrece a sus clientes diversos \n" +
//                "servicios adicionales y opcionales, entre ellos, el Servicio de Facturación Electrónica (en adelante, “el servicio”), \n" +
//                "el cual es proporcionado por la “Compañía” a aquellos clientes que soliciten el alta/acceso o deseen adquirir el mismo.</br></br>\n" +
//                "<strong>2.3</strong> En virtud de lo expuesto, es voluntad del “Cliente” obtener el servicio de facturación \n" +
//                "electrónica ofrecido por “DVNET”, por lo que se adhiere de forma expresa, plena y sin reservas a los términos y \n" +
//                "condiciones que constan en el presente contrato.</br></br>\n" +
//                "<h1 class=\"subtitulo\">CLÁUSULA TERCERA: OBJETO.-</h1>\n" +
//                "Con los antecedentes expuestos, “DVNET” se obliga a prestar el Servicio de Facturación Electrónica a favor \n" +
//                "del “Cliente”, siempre y cuando, “el Usuario” exprese su voluntad, a través de medios físicos, electrónicos \n" +
//                "y/o telefónicos, de adquirir “el servicio”, como adicional al suministro principal que haya contratado o de \n" +
//                "manera independiente al mismo, una vez el “Usuario” haya presentado su solicitud y la “Compañía” verifique \n" +
//                "el cumplimiento de los requisitos legales respectivos, procederá a dar de alta “el servicio”.</br></br>\n" +
//                "\n" +
//                "Para el efecto, “DVNET” se compromete a poner a disposición del “Cliente”, tras la suscripción del presente \n" +
//                "instrumento, el acceso a la plataforma web (“facturaciónelectronica.dvnet.ec”), mediante un usuario y \n" +
//                "clave provisional, en los términos y condiciones que se detallarán a continuación.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CLÁUSULA CUARTA: CARACTERÍSTICAS DEL SERVICIO DE FACTURACIÓN ELECTRÓNICA.-</h1>\n" +
//                "<strong>4.1</strong> “DVNET” prestará “el servicio” al “Cliente” a través de un sistema en línea, cuyo dominio \n" +
//                "será “facturaciónelectronica.dvnet.ec” (en adelante, la “plataforma web”), mediante el cual el “Cliente” \n" +
//                "accederá con un usuario y una clave provisional; los que serán creados y entregados al “Usuario” a la suscripción \n" +
//                "del presente instrumento.</br></br>\n" +
//                "<strong>4.2</strong> Dentro de la “plataforma web” el “Cliente” podrá generar las facturas electrónicas que requiera, \n" +
//                "con un máximo de 200 documentos electrónicos al año que conforman el plan básico.</br></br>\n" +
//                "<strong>4.3</strong> “El Servicio” permitirá, además de la visualización en pantalla de la imagen de la factura \n" +
//                "electrónica y de su detalle, su impresión y descarga en formato “Xml”, conforme a los esquemas .xsd emitidos \n" +
//                "por el Servicio de Rentas Internas (en adelante, “SRI”), en el ordenador personal del “Usuario” para sus \n" +
//                "fines pertinentes.</br></br>\n" +
//                "En el caso de existir comunicaciones ordinarias, relacionadas con el presente contrato, el “Usuario” recibirá \n" +
//                "una copia en formato .pdf del comunicado que se le haya emitido, pudiéndoselo descargar en el dispositivo electrónico \n" +
//                "donde se encuentre habilitada la plataforma web.</br></br>\n" +
//                "<strong>4.4</strong> Para que las facturas electrónicas emitidas por la “plataforma web” tengan plena vigencia \n" +
//                "debera el “Cliente” contar con firma electrónica y certificado de autorización emitido por el SRI, conforme a \n" +
//                "lo que se detallará en la siguiente cláusula como requisito previo para solicitar “el servicio”.</br></br>\n" +
//                "<strong>4.5</strong> La emisión de las facturas electrónicas dentro de la “plataforma web” será de \n" +
//                "entero control de “El Cliente”.</br></br>\n" +
//                "<strong>4.6</strong> “El Servicio” no incluye el almacenamiento de información, únicamente el de emisión de \n" +
//                "los documentos electrónicos, en caso de requerir acceso a la nube se entenderá como un producto adicional, \n" +
//                "el cual tendrá un cargo a la tarifa básica.</br></br>\n" +
//                "<strong>4.7</strong> “DVNET” realizará el mantenimiento de la “plataforma web” para el funcionamiento \n" +
//                "adecuado de la misma.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CLÁUSULA QUINTA: ALTA EN EL SERVICIO DE FACTURACIÓN ELECTRÓNICA.-</h1>\n" +
//                "<strong>5.1. Condiciones para que el “Cliente” acceda a “el servicio”:</strong></br></br>\n" +
//                "<strong>a)</strong> El “Cliente” manifestó a “DVNET”, a través de medios físicos, eletrónicos y/o \n" +
//                "telefónicos, su voluntad de adquirir “el servicio”, el cual fue plasmado mediante un formulario de \n" +
//                "solicitud  proporcionado por la “Compañía”;</br></br>\n" +
//                "<strong>b)</strong> Asimismo, para que el “Cliente” pueda acceder a “el servicio”, deberá presentar, \n" +
//                "junto a su petición o suscripción del presente instrumento, lo siguiente:</br>\n" +
//                "<ul style= \"list-style-type: circle;\">\n" +
//                "   <li>\n" +
//                "   Certificado digital de firma electrónica, el cual debe ser emitido o actualizado por cualquier \n" +
//                "   entidad autorizada; tales como: Banco Central del Ecuador, Consejo de la Judicatura, Security Data o ANF AC Ecuador.\n" +
//                "   </li>\n" +
//                "   <li>\n" +
//                "   Solicitud de autorización en el SRI para la emisión de comprobantes electrónicos en ambientes \n" +
//                "   de “pruebas” y “producción”.\n" +
//                "   </li>\n" +
//                "</ul></br>\n" +
//                "<strong>5.2. Acceso a “el servicio”:</strong></br></br>\n" +
//                "El alta en el Servicio de Facturación Electrónica será de carácter voluntario para los “Clientes” de “DVNET”, \n" +
//                "siendo gratuito para aquellos nuevos “Usuarios” que soliciten “el servicio” dentro de _____________ contados \n" +
//                "desde la suscripción del contrato del servicio principal. En dicho caso, los “Clientes” tendrán acceso a \n" +
//                "“el servicio” por un tiempo determinado de __________.  Sin embargo, para aquellos “Usuarios” antiguos o \n" +
//                "nuevos que deseen adquirir “el servicio” transcurridos los ____________, no será gratuito, aplicándose la \n" +
//                "tarifa correspondiente al plan básico.</br></br>\n" +
//                "Para los “Usuarios” que sean titulares de varios contratos de suministro en vigor, el alta en el Servicio de \n" +
//                "Facturación Electrónica se aplicará individualmente por contrato, no aplicándose al resto de contratos de \n" +
//                "titularidad del “Cliente”. Asimismo, Si el “Usuario” realizara nuevos contratos de suministro con “DVNET”, \n" +
//                "se le ofrecerá igualmente el alta en el servicio, para lo cual, en caso de dar su consentimiento, tendrá que \n" +
//                "suscribir el presente instrumento adheriendose a los términos y condiciones inherentes a “el servicio”.</br></br>\n" +
//                "El alta del “Servicio” se realizará a través de la aplicación de “DVNET” (http://facturacionelectronica.dvnet.ec), \n" +
//                "tras la solicitud/petición del cliente, por cualquiera de los canales de atención físicos o virtuales de la \n" +
//                "“Compañía”. Para ello, el “Cliente” deberá identificarse en el formulario de solicitud con los datos básicos \n" +
//                "requeridos para la creación de la cuenta y contraseña provisional que será entregados al “Cliente”, vía correo \n" +
//                "electrónico y/o de manera física, por “DVNET” una vez suscrito el presente instrumento.</br></br>\n" +
//                "Se entenderá que desde la presentación de la solicitud/petición y suscripción del presente instrumento el \n" +
//                "“Cliente” ha manifestado su voluntad, y por ende consentimiento, para ser dado de alta en el Servicio de \n" +
//                "Facturación Electrónica, aceptando los términos y condiciones del mismo.</br></br>\n" +
//                "Cuando “DVNET” permita el acceso al “servicio” al “Cliente”, éste deberá acceder a la plataforma \n" +
//                "(“facturaciónelectronica.dvnet.ec”) con el usuario y contraseña provisional proporcionados por la “Compañía” \n" +
//                "y proceder a realizar el cambio de contraseña. Una vez, efectuado, el “Usuario” tendrá a entera disposición \n" +
//                "la “plataforma web” dentro de los fines y alcances de “el servicio” adquirido.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CLÁUSULA SEXTA: TARIFA PLAN BÁSICO Y FORMA DE PAGO DE LOS SERVICIOS.-</h1>\n" +
//                "Las tarifas que el “Cliente” debe cancelar por la prestación del Servicio de Facturación Electrónica, objeto \n" +
//                "del presente instrumento, a “DVNET”, correspondiente, son los detallados en el Anexo 1, que se adjunta \n" +
//                "como habilitante en el presente contrato.</br></br>\n" +
//                "Las tarifas detalladas serán canceladas por el “Cliente” de manera ______ a <strong>“DVNET”</strong>, \n" +
//                "mediante las siguientes forma de pago:</br></br>\n" +
//                "<table class=\"tabla1\" border=\"1\"; cellpadding=\"0\"; cellspacing=\"1\";>\n" +
//                "    <tr>\n" +
//                "    <td><strong>Forma de pago</strong></td>\n" +
//                "    <td class=\"sino\">SI</td>\n" +
//                "    <td class=\"sino\">NO</td>\n" +
//                "    </tr>\n" +
//                "    <tr>\n" +
//                "    <td>Efectivo, pago directo a través de ventanilla del prestador del servicio</td>\n" +
//                "    <td></td>\n" +
//                "    <td></td>\n" +
//                "    </tr>\n" +
//                "    <tr>\n" +
//                "    <td>Débito automático cuenta de ahorro, o corriente, o tarjeta de crédito</td>\n" +
//                "    <td></td>\n" +
//                "    <td></td>\n" +
//                "    </tr>\n" +
//                "    <tr>\n" +
//                "    <td>Pago en línea a través Instituciones Financieras y Auxiliares Financieros \n" +
//                "    o de Pago, autorizados y/o medios electrónicos</td>\n" +
//                "    <td></td>\n" +
//                "    <td></td>\n" +
//                "    </tr>\n" +
//                "    <tr>\n" +
//                "    <td>Cualquier otro medio de pago determinado y autorizado por la Junta de la \n" +
//                "    Política y Regulación Monetaria y Financiera</td>\n" +
//                "    <td></td>\n" +
//                "    <td></td>\n" +
//                "    </tr>\n" +
//                "    </table></br></br>\n" +
//                "En caso de que el “Cliente” desee cambiar su modalidad de pago a otra disponible, deberá comunicarse con “DVNET”, \n" +
//                "a través de cualquier medio autorizado de atención, virtual o físico, a efecto de realizar el cambio requerido.</br></br>\n" +
//                "La facturación por el servicio contratado iniciará a partir de la fecha " + fecha + " en que \n" +
//                "el “Cliente” tenga activado o haya sido dado de alta “el servicio”, de conformidad el númeral 5.2. de la cláusula \n" +
//                "quinta del presente instrumento, más los impuestos que por ley correspondan. De igual manera, se facturará \n" +
//                "únicamente hasta la fecha de terminación del Contrato. En caso que llegasé a suspenderse el servicio, por \n" +
//                "factores técnicos concernientes y/o de responsabilidad de “DVNET”, se facturará por los servicios efectivamente \n" +
//                "prestados y aquellos que se justifiquen de conformidad a la normativa vigente.</br></br>\n" +
//                "En caso de que existan reajustes de la tarifa de los servicios contratados por el “Cliente”, “DVNET” \n" +
//                "notificará con un término de 30 días de anticipación a la modificación por cualquier medio masivo, \n" +
//                "indicándole de manera clara las nuevas características, mejoras y/o tarifas a las condiciones que apliquen.</br></br>\n" +
//                "El “Cliente” podrá solicitar el cambio del plan básico contratado, al tenor de lo dispuesto en la cláusula \n" +
//                "siguiente. La aplicación del nuevo servicio regirá desde la activación y bajo los nuevos términos y \n" +
//                "condiciones del nuevo plan.</br></br>\n" +
//                "El “Cliente” acepta expresamente recibir la factura electrónica emitida por “DVNET” por la prestación de sus servicios \n" +
//                "al siguiente correo electrónio: " + partner.getEmail() + "</br></br>\n" +
//                "En caso de mora, el “Cliente” se compromete, de forma expresa,  a cancelar el valor total adeudado más el \n" +
//                "interés por mora calculado con las tasas vigentes y establecidas por “DVNET”, de acuerdo a la fecha en que \n" +
//                "debió efectuarse el pago, calculados desde el día siguiente de la fecha máxima de pago constante en la \n" +
//                "factura, hasta el día efecto de pago del valor adeudado, así como también, los cargos de cobranza generados \n" +
//                "por “DVNET”, conforme correspondan y aquellos que se justifiquen en virtud de la normativa vigente.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CLÁUSULA SÉPTIMA: CARGOS ADICIONALES Y OTRAS TARIFAS.-</h1>\n" +
//                "Se entiende como “cargos adicionales y otras tarifas” a todos aquellos servicios que no se encuentren \n" +
//                "detallados en las cláusulas cuarta y quinta del presente contrato, en caso de que el “Cliente” desee acceder \n" +
//                "a ellos, en cuanto a su valor y detalle deberán remitirse al <strong>Anexo 3</strong>, por lo que, acepta que en el caso de \n" +
//                "los cargos, los valores respectivos serán sumados a la tarifa del plan básico contratado; y, con respecto \n" +
//                "a las “tarifas”, si el valor corresponde a un nuevo plan, el valor inicial será reemplazado por este.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CLÁUSULA OCTAVA: PLAZO.-</h1>\n" +
//                "El presente contrato tendrá una permanencia mínima de 2 (dos) años contados a partir de la fecha en la cual \n" +
//                "“DVNET” otorgue el alta del “servicio” al “Cliente”, esto es ___________________. El tiempo mencionado es \n" +
//                "aplicado para usuarios nuevos, dentro y fuera de los tres meses, y usuarios antiguos.</br></br>\n" +
//                "El “Cliente” tendrá 3 (tres) meses, contados a partir de la fecha de suscripción de contrato del suministro \n" +
//                "principal, para solicitar  “el servicio” a “DVNET”. En caso que el “Cliente” lo hiciése transcurrido el \n" +
//                "tiempo indicado, éste deberá pagar un valor por implementación, según la tabla vigente de la “Compañía” al \n" +
//                "momento de la contratación.</br></br>\n" +
//                "El plazo de 2 (dos) años será renovado de manera automática, e indefinida, salvo que una de las partes \n" +
//                "exprese su voluntad de darlo por terminado, lo cual será comunicado por escrito dentro de, por lo menos, 15 \n" +
//                "(quince) días de anticipación, sin que tal hecho de lugar al pago de ninguna clase de indemnización.</br></br>\n" +
//                "En el caso en el que el “Cliente” quiera proceder a dar de baja “el servicio” deberá cancelar todos los \n" +
//                "valores adeudados por el servicio prestado hasta el momento de su terminación y, además, cancelar el valor \n" +
//                "proporcional por el tiempo que faltase para cumplir el plazo aquí estipulado.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CLÁUSULA NOVENA: GARANTÍA. -</h1>\n" +
//                "Durante la vigencia del presente contrato, “DVNET” se compromete a emplear los esfuerzos comercialmente \n" +
//                "razonables para proporcionar “el servicio” a “el Cliente”, de conformidad con los estándares de la \n" +
//                "industria generalmente aceptados.</br></br>\n" +
//                "Sin perjuicio de lo expuesto, “DVNET” otorgará a el “Cliente” una garantía en caso de que exista una \n" +
//                "falla técnica en la “plataforma web” que sea responsabilidad directa de la “Compañía”. En consecuencia \n" +
//                "“DVNET” se obliga a:</br></br>\n" +
//                "<strong>9.1</strong> Brindar asistencia vía remota, con el personal técnico especializado de la “Compañía” \n" +
//                "dentro de las 24 horas de comunicado el suceso.</br></br>\n" +
//                "<strong>9.2</strong> Brindar asistencia de manera presencial, con el personal técnico especializado de la \n" +
//                "“Compañía”, en caso de que la falla técnica así lo requiera o si pasadas las 24 horas no se hubiese solucionado.</br></br>\n" +
//                "<strong>9.3</strong> En caso de que por la falta de acceso a la “plataforma web” el “Cliente” no haya podido \n" +
//                "cumplir con sus obligaciones tributarias pertinentes, “DVNET” se obliga a prestar el servicio de manera gratuita \n" +
//                "por el mismo tiempo que se haya encontrado inactivo, una vez cumplido el plazo del contrato.</br></br>\n" +
//                "“DVNET” se obliga a cumplir con la garantía expuesta, siempre que:</br></br>\n" +
//                "<strong>a.</strong> Los dispositivos electrónicos del “Cliente” se encuentren en un buen estado operacional \n" +
//                "que permita el funcionamiento óptimo de la “plataforma web”.</br></br>\n" +
//                "<strong>b.</strong> El “Cliente” brindará información pertinente para la resolución de problemas y, para \n" +
//                "el alojamiento en servidores propios, cualquier acceso que “DVNET” puede necesitar para identificar, \n" +
//                "reproducir y solventar los problemas.</br></br>\n" +
//                "Asimismo, “DVNET” está obligado únicamente a reanudar la ejecución del servicio aquí contratado cuando se trate \n" +
//                "de fallas técnicas de la “plataforma web” relacionadas con su manejo directo. “DVNET” no estará obligado a \n" +
//                "cumplir con la presente garantía en caso que el daño o falla obedezca a un caso fortuito o fuerza mayor en los \n" +
//                "términos de ley, que sea originado por causas imputables al “Cliente” y/o un tercero o que se incumplan los \n" +
//                "puntos a. y b. de la presente cláusula.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CLÁUSULA DÉCIMA: OBLIGACIONES Y RESPONSABILIDADES DE “DVNET”.-</h1>\n" +
//                "“DVNET” adquiere los siguientes compromisos con el “Cliente” que contrate el Servicio de Facturación Electrónica:</br></br>\n" +
//                "<ul style= \"list-style-type: square;\">\n" +
//                "    <li>Prestar “el servicio” contratado por el “Cliente” de forma continua y permanente, durante el tiempo de \n" +
//                "    duración de este Contrato, en los términos y condiciones aquí establecidos, salvo las situaciones de \n" +
//                "    fuerza mayor o caso fortuito, según las establecidas en el artículo 30 de Código Civil ecuatoriano vigente.</br></br>\n" +
//                "    </li>\n" +
//                "    <li>\n" +
//                "    “DVNET” estará exenta de responsabilidad en caso de mal uso de los servicios contratado por parte del \n" +
//                "    “Usuario”, por lo que no estará obligada al pago de indemnización de ninguna clase.</br></br>\n" +
//                "    </li>\n" +
//                "    <li>\n" +
//                "    Informar sobre las características de los servicios ofertados, como promociones de planes, tarifas, \n" +
//                "    precios, saldos, cargos y otros servicios informativos, a través de medios físicos y electrónicos.</br></br>\n" +
//                "    </li>\n" +
//                "    <li>\n" +
//                "    En caso de que “DVNET” requiera modificar las características técnicas del “Servicio” contratado, \n" +
//                "    notificará por escrito por medios físicos, electrónicos o telefónicos a el “Usuario”, en un \n" +
//                "    plazo no mayor a 30 (treinta) días de anticipación a la modificación.</br></br>\n" +
//                "    </li>\n" +
//                "    <li>\n" +
//                "    “DVNET” deberá permitir a el “Usuario” modificar su contraseña a través de la propia funcionalidad \n" +
//                "    que le ofrece la web, en el momento que el “Cliente” lo requiera.</br></br>\n" +
//                "    </li>\n" +
//                "    <li>\n" +
//                "    “DVNET” se obliga a observar las máximas garantías exigidas en el ámbito legislativo.</br></br>\n" +
//                "    </li>\n" +
//                "</ul>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CLÁUSULA UNDÉCIMA: OBLIGACIONES, RESPONSABILIDADES Y PROHIBICIONES DEL “CLIENTE”.-</h1>\n" +
//                "<strong>11.1. Obligaciones y responsabilidades:</strong></br></br>\n" +
//                "El “Usuario” adquiere los siguientes compromisos al contratar el Servicio de Facturación Electrónica:</br></br>\n" +
//                "<ul style= \"list-style-type: square;\">\n" +
//                "   <li>Cumplir con los términos y condiciones estipulados en el presente instrumento.</br></br></li>\n" +
//                "   <li>Realizar un buen uso del “servicio” contratado y para los fines convenidos.</br></br></li>\n" +
//                "   <li>El “Cliente” será responsable de la emisión de las facturas electrónicas en la “plataforma web” \n" +
//                "   proporcionada por “DVNET”, así como de llevar sus libros contables conforme a las exigencias de la \n" +
//                "   normativa tributaria vigente.</br></br></li>\n" +
//                "   <li>El “Cliente” será responsable del almacenamiento de las facturas electrónicas que emita dentro de \n" +
//                "   la “plataforma web” en cualquier dispositivo apto para aquel fin.</br></br></li>\n" +
//                "   <li>Informarse adecuadamente y de manera oportuna por los medios electrónicos y físicos que “DVNET” \n" +
//                "   pone a su disposición.</br></br></li>\n" +
//                "   <li>Notificar a “DVNET” cuando ocurra la interrupción o daño en la “plataforma web” concernientes \n" +
//                "   a fallas técnicas relacionadas directamente con la “Compañía”.</br></br></li>\n" +
//                "   <li>Pagar el servicio contratado y efectivamente prestado conforme lo determina el presente contrato y \n" +
//                "   el ordenamiento jurídico vigente, en las fechas de facturación correspondiente.</br></br></li>\n" +
//                "   <li>Cumplir con las obligaciones o resoluciones emitidas por el SRI y demás que se derivan del \n" +
//                "   ordenamiento jurídico vigente.</br></br></li>\n" +
//                "   <li>Comunicar a “DVNET” cualquier cambio en la dirección de correo electrónico en la que desee \n" +
//                "   recibir todas las comunicaciones y documentos pertinentes para la prestación de el “servicio”. \n" +
//                "   En caso de que el Cliente no cumpliera con este compromiso, “DVNET” no se responsabiliza del \n" +
//                "   correcto envío de los mensajes.</br></br></li>\n" +
//                "   <li>Garantizar y responder, en todo caso, de la veracidad, exactitud, vigencia y autenticidad de \n" +
//                "   los datos personales facilitados a través del formulario de solicitud/petición de alta al “servicio”.</br></br></li>\n" +
//                "</ul>\n" +
//                "<strong>11.2. Prohibiciones:</strong></br></br>\n" +
//                "<ul>\n" +
//                "   <li>El “Cliente” tiene estrictamente prohibido compartir su usuario y contraseña con terceros.</br></br></li>\n" +
//                "   <li>El “Cliente” no podrá utilizar diversos dispositivos electrónicos de manera paralela. Si mediante \n" +
//                "   el metodo de válidación interna “DVNET” determina diversas direcciones IP, que se encuentren utilizando \n" +
//                "   “el servicio” de manera continua y simultánea procederá a realizar el cargo respectivo.</br></br></li>\n" +
//                "   <li>El “Usuario” no podrá utilizar el “servicio” para fines ilícitos, fraude o perjuicio a terceros, \n" +
//                "   o que contravengan a las normativas referentes a lavado de activos.</br></br></li>\n" +
//                "</ul>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CLÁUSULA DÉCIMA SEGUNDA: CESIÓN DE DERECHO. -</h1>\n" +
//                "El “Cliente” no podrá, en ningún caso, ceder, ni transferir, en todo o en parte los derechos y obligaciones \n" +
//                "que le confiere el presente contrato, sus anexos o modificaciones de los mismos, salvo que “DVNET” lo \n" +
//                "autorice por escrito.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CLÁUSULA DÉCIMA TERCERA: DIVISIBILIDAD. -</h1>\n" +
//                "Las partes acuerdan que en caso de que cualquier estipulación de este contrato fuera declarada \n" +
//                "inválida o nula por autoridad competente, dicha declaración afectará única y exclusivamente a dicha \n" +
//                "estipulación, separándose de este contrato y manteniéndose el mismo válido en el resto de sus partes.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CLÁUSULA DÉCIMA CUARTA: NATURALEZA JURÍDICA Y NORMATIVA TRIBUTARIA PERTINENTE. -</h1>\n" +
//                "El presente contrato de prestación de servicios es de carácter civil, el cual se fundamenta en la siguiente normativa:</br></br>\n" +
//                "<strong>14.1</strong> Ley de Comercio Electrónico, Firmas Electrónicas y Mensajes de Datos, publicado Registro \n" +
//                "Oficial Suplemento No. 557 el 17 de abril de 2002, con última reforma el 27 de agosto de 2021 y su Reglamento \n" +
//                "General, los cuales regulan la prestación de servicios electrónicos a través de redes de información; comercio \n" +
//                "electrónico y la protección de los usuarios de estos sistemas.</br></br>\n" +
//                "<strong>14.2</strong> Resolución No. NAC-DGERCGC18-00000431, emitida por el SRI la cual establece los sujetos \n" +
//                "pasivos que hasta el 2024 deben encontrarse, obligatoriamente, emitiendo comprobantes de venta, comprobantes \n" +
//                "de retención y documentos complementarios de manera electrónica.</br></br>\n" +
//                "<strong>14.3</strong> Resolución No. NAC-DGERCGC18-00000175, emitida por el SRI en junio de 2018, reforma \n" +
//                "resolución No. NAC-DGERCGC18-00000428, que dicta las normas para la transmisión de información sobre \n" +
//                "documento electrónicos a través de impresoras fiscales.</br></br>\n" +
//                "<strong>14.4</strong> Resolución No. NAC-DGERCGC18-00000233, emitida por el SRI en junio de 2018, en la \n" +
//                "cual se establecen las normas para emisión, entrega y transmisión de comprobantes de venta, retención y \n" +
//                "documentos complementarios expedidos por sujetos pasivos autorizados, mediante el esquema de comprobantes \n" +
//                "electrónicos.</br></br>\n" +
//                "<strong>14.5</strong> Resolución No. NAC-DGERCGC16-00000287, emitida por el SRI en julio de 2016, en la cual \n" +
//                "expide definiciones para la emisión de comprobantes emitidos por medios digitales o electrónicos de pago.</br></br>\n" +
//                "<strong>14.6</strong> Resolución No. NAC-DGERCGC14-00790, emitida por el SRI en octubre de 2014, la cual expide \n" +
//                "las normas para la emisión y autorización de comprobantes de venta, retención y documentos complementación \n" +
//                "mediante comprobantes electrónicos.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CLÁUSULA DÉCIMA QUINTA: LIMITACIÓN DE LA RESPONSABILIDAD.-</h1>\n" +
//                "“DVNET” se encuentra exento de responsabilidad en aquellos actos que no sean imputados directamente a la \n" +
//                "prestación de su servicio, fuerza mayor o caso fortuito, asimismo, aquellos ilícitos que pudiera ocasionar \n" +
//                "el “Cliente” y que ocasionen perjuicio a un tercero.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CLÁUSULA DÉCIMA SEXTA: PROTECCIÓN DE DATOS.-</h1>\n" +
//                "<strong>16.1. Recogida y tratamiento de datos de carácter personal:</strong></br></br>\n" +
//                "La recogida y tratamiento de datos de carácter personal recabados en el formulario de solicitud/petición, \n" +
//                "tienen como finalidad proporcionar el servicio de facturación electrónica solicitada.</br></br>\n" +
//                "De acuerdo con lo dispuesto en la Ley Orgánica de Protección de Datos Personales y su normativa \n" +
//                "supletoria vigente, el “Cliente” ha expresado su consentimiento para el tratamiento de sus datos personales, \n" +
//                "para efectos de la prestación del “servicio”, es decir, las obligaciones contractuales emanadas del presente instrumento.</br></br>\n" +
//                "Los datos solicitados en los formularios/peticiones, incluidos los que consten en la “plataforma web”, tienen \n" +
//                "carácter obligatorio, salvo que en los mismos se indicase otra cosa, autorizando el “Cliente” su tratamiento \n" +
//                "para las finalidades indicadas por “DVNET”.</br></br>\n" +
//                "En ningún caso podrán incluirse en el formulario de petición datos de carácter personal correspondientes a \n" +
//                "terceras personas, salvo que el solicitante hubiese recabado con carácter previo su consentimiento en los \n" +
//                "términos exigidos por la Ley Orgánica de Protección de Datos Personales, respondiendo con carácter exclusivo del \n" +
//                "incumplimiento de esta obligación y cualquier otra en materia de datos de carácter personal.</br></br>\n" +
//                "<strong>16.2. Derechos de acceso, rectificación, oposición y cancelación:</strong></br></br>\n" +
//                "El Cliente puede ejercitar sus derechos de acceso, rectificación, cancelación y oposición, en los términos \n" +
//                "establecidos legalmente, comunicándolo por escrito a “DVNET”, exceptuando aquellas excepciones determinadas en \n" +
//                "el artículo 18 de Ley Orgánica de Protección de Datos Personales. En dicho sentido, autorizo que “DVNET” proporcione \n" +
//                "a las entidades pertinentes mi información crediticia, por ser una obligación emanada del presente contrato.</br></br>\n" +
//                "<strong>16.3. Empleo de cookies:</strong></br></br>\n" +
//                "“DVNET” puede emplear cookies, al objeto de facilitarle una gestión más ágil y eficaz en de los servicios prestados.</br></br>\n" +
//                "Los cookies son un método de autentificación de usuarios que permiten guardar constancia del identificador \n" +
//                "asignado en el momento del registro del cliente, de manera que en lo sucesivo se evita reiterar los trámites \n" +
//                "propios de nuevos registros.</br></br>\n" +
//                "El cliente puede configurar el navegador de su ordenador para que le advierta del acceso de cookies y, en su caso, \n" +
//                "de impedir la recepción de las mismas en el disco duro.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CLÁUSULA DÉCIMA SÉPTIMA: CONFIDENCIALIDAD.-</h1>\n" +
//                "“DVNET”, su representante legal y las personas que intervengan en la ejecución del presente contrato, deberán \n" +
//                "guardar la más estricta confidencialidad de toda información que el “Cliente” detalle en sus documentos electrónicos \n" +
//                "con motivo o por razón del presente instrumento, quedando expresamente prohibida su divulgación a terceros, \n" +
//                "so pena de incurrir en las responsabilidades civiles y penales que conllevan su inobservancia.</br></br>\n" +
//                "La confidencialidad estipulada en esta cláusula será durante el tiempo que dure el presente contrato y subsistirá\n" +
//                "en forma indefinida después de su terminación.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CLÁUSULA DÉCIMA OCTAVA: TERMINACIÓN DEL CONTRATO.-</h1>\n" +
//                "“Las partes” intervinientes acuerdan en forma recíproca que el presente contrato, sus anexos y demás documentos \n" +
//                "que forman parte integrante del mismo, podrán ser declarados terminados en los siguientes casos:</br></br>\n" +
//                "<strong>18.1 </strong>Por vencimiento del plazo del contrato, para lo cual, cualquiera de las partes podrá \n" +
//                "notificar a la otra con 15 (quince) días hábiles las terminación por esta causal.</br></br>\n" +
//                "<strong>18.2 </strong>Por falta de pago de 15 (quince) a 30 (treinta) días posteriores a la emisión de la \n" +
//                "factura de “DVNET”.</br></br>\n" +
//                "<strong>18.3 </strong>Por insolvencia declarada por juez competente, concurso de acreedores, quiebra, \n" +
//                "disolución o liquidación, de cualquiera de las partes intervinientes.</br></br>\n" +
//                "<strong>18.4 </strong>Cuando por circunstancias imprevistas, técnicas o económicas, o causas de fuerza mayor o \n" +
//                "caso fortuito debidamente justificado, no fuere posible o conveniente para los intereses de “Las Partes” \n" +
//                "ejecutar total o parcialmente el contrato.</br></br>\n" +
//                "<strong>18.5 </strong>Por mandato judicial o disposición de autoridad administrativa competente.</br></br>\n" +
//                "<strong>18.6 </strong>Por incumplimiento del “Cliente” de las obligaciones previstas en el presente contrato.</br></br>\n" +
//                "<strong>18.7 </strong>Cuando el “Cliente” cediera total o parcialmente el contrato sin autorización de “DVNET”.</br></br>\n" +
//                "<strong>18.8 </strong>Por muerte del “Cliente”, lo cual, surtirá efectos cuando documentadamente se ponga en \n" +
//                "conocimiento de “DVNET” del hecho acaecido.</br></br>\n" +
//                "<strong>18.9 </strong>Por alquilar, revender o negociar de cualquier forma el servicio contratado.</br></br>\n" +
//                "<strong>18.10 </strong>Si el “Cliente” utiliza el “servicio” contratado para fines distintos o para prácticas \n" +
//                "contrarias a la ley, las buenas costumbres, la moral o cualquier otra forma que perjudique a la “Compañía”</br></br>\n" +
//                "La terminación por mutuo acuerdo no implica la renuncia de las obligaciones, ni derechos a favor de “DVNET” \n" +
//                "y del “Cliente”. En este caso, el “Usuario” deberá pagar todos los valores adeudados por los servicios prestados \n" +
//                "y, además, el valor proporcional por el tiempo que faltase para cumplir el plazo aquí estipulado. Asimismo, “DVNET” \n" +
//                "procederá inmediatamente a suspender el servicio sin previo aviso y sin que el “Cliente” tenga derecho a \n" +
//                "indemnización o devolución de dinero alguna.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CLÁUSULA DÉCIMA NOVENA: JURISDICCIÓN, COMPETENCIA Y SOLUCIÓN DE CONTROVERSIAS. -</h1>\n" +
//                "“Las partes” declaran expresamente que renuncian a fuero y domicilio, y convienen en que, para cualquier controversia \n" +
//                "o diferencia que surja o se relacione con la interpretación o ejecución del presente contrato, se someterán \n" +
//                "a la mediación del Centro de Arbitraje y Conciliación de la Cámara de Comercio de Guayaquil. En caso de no \n" +
//                "llegarse a un acuerdo de mediación, las partes se someterán al arbitraje en derecho, de acuerdo al procedimiento \n" +
//                "establecido en la Ley de Arbitraje y Mediación, en el Reglamento de dicho Centro y en las siguientes normas:</br></br>\n" +
//                "<strong>19.1 </strong>Las partes designarán 1 (UN) sólo árbitro, que será seleccionado por sorteo, conforme \n" +
//                "lo establecido en la Ley de Arbitraje, el cual contará con el suplente respectivo;</br></br>\n" +
//                "<strong>19.2 </strong>El árbitro de dicho centro efectuará un arbitraje administrado en Derecho y confidencial, \n" +
//                "y quedará facultado para dictar medidas cautelares solicitando el auxilio de funcionarios públicos, judiciales, \n" +
//                "policiales y administrativos, sin que sea necesario acudir a un juez ordinario para tales efectos;</br></br>\n" +
//                "<strong>19.3 </strong>El procedimiento arbitral tendrá lugar en las instalaciones del Centro de Arbitraje \n" +
//                "y Conciliación de la Cámara de Comercio de Guayaquil;</br></br>\n" +
//                "<strong>19.4 </strong>Las “PARTES” renuncian a la jurisdicción ordinaria, se obligan acatar el laudo arbitral \n" +
//                "y se comprometen a no interponer ningún tipo de recurso en contra del mismo;</br></br>\n" +
//                "<strong>19.5 </strong>El laudo arbitral será inapelable; y,</br></br>\n" +
//                "<strong>19.6 </strong>Las costas procesales que se generen por el procedimiento arbitral, tales como derechos \n" +
//                "del Centro de Arbitraje y Conciliación, honorarios de peritos, árbitros, abogados, etc., serán asumidos \n" +
//                "íntegra y totalmente por quien determine el Árbitro en el laudo que expida.</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CLÁUSULA VIGÉSIMA: NOTIFICACIONES.-</h1>\n" +
//                "Las notificaciones que correspondan, serán entregadas al “Cliente” a la dirección e correo \n" +
//                "electrónico " + partner.getEmail() + ", así como en su domicilio " + partner.getStreet() + ".</br></br>\n" +
//                "\n" +
//                "<h1 class=\"subtitulo\">CLÁUSULA VIGÉSIMA PRIMERA: ACEPTACIÓN. -</h1>\n" +
//                "El “Cliente” acepta el presente contrato, anexos y demás documentos, en su totalidad, para lo cual deja constancia \n" +
//                "de lo anterior y firma junto a “DVNET” en tres ejemplares del mismo tenor, en la ciudad \n" +
//                "de " + partner.getCanton() + " a los " + dia + " día del mes de " + mes +" del año " + anio + ".</br></br>\n" +
//                "Firman las partes:</br></br></br></br>\n" +
//                "\n" +
//                "<table class=\"tabla2\" cellpadding = 0 cellspacing=\"1\">\n" +
//                "<tr>" +
//                "<td align=\"center\"><img src=\"data:image/png;base64," + firmapng + "\" width=\"200\" height=\"100\"></td>" +
//                "<td align=\"center\"><img src=\"data:image/png;base64," + firmauserpng + "\" width=\"200\" height=\"100\"></td>\n" +
//                "</tr>\n" +
//                "<tr style=\"text-align: center;\">\n" +
//                "<td>Firma del “Cliente”</td>\n" +
//                "<td>Firma autorizada por “DVNET”</td>\n" +
//                "</tr>\n" +
//                "<tr>\n" +
//                "<td></br></br></td>\n" +
//                "<td></br></br></td>\n" +
//                "</tr>\n" +
//                "<tr>\n" +
//                "<td>\n" +
//                "RAZÓN SOCIAL</br>\n" +
//                "RUC: " + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + "</br>\n" +
//                "Nombre y apellido: " + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + "</br>\n" +
//                "No. Cédula: " + partner.getIdentification_id() + "</br></br>\n" +
//                "</br></br>\n" +
//                "</td>\n" +
//                "<td>\n" +
//                "RUC: " + ruc + "</br>\n" +
//                "Nombre y apellido: " + user.getName() + "</br>\n" +
//                "No. Cédula: " + user.getIdentificacion() + "</br></br>\n" +
//                "</br></br>\n" +
//                "</td>\n" +
//                "</tr>\n" +
//                "</table>\n" +
//                "\n" +
//                "<h1 class=\"anexo\">\n" +
//                "ANEXO 2</br>\n" +
//                "AUTORIZACIÓN DE DÉBITO\n" +
//                "</h1>\n" +
//                "<strong>Aplica: </strong> Si ( &nbsp ) &nbsp No ( &nbsp )</br></br>\n" +
//                "Como cliente de “DVNET”, adicionalmente ratifico mi compromiso de mantener los pagos de mi tarjeta de crédito \n" +
//                "y los fondos suficientes en mi cuenta corriente o de ahorros dentro de los plazos estipulados, a fin de cubir \n" +
//                "los valores cuyos débitos automáticos autorizo a traves del presente documento.</br></br>\n" +
//                "Asimismo, expresamente me obligo a no renovar la presente autorización sin el previo consentimiento por \n" +
//                "escrito de “DVNET”, por lo que libero de toda responsabilidad a la entidad financiera, banco o la emisora \n" +
//                "de la tarjeta de crédito por los débitos o cargos efectuados en base a la presente autorización. De igual manera, \n" +
//                "autorizo que en caso de pérdida, o cualquier circunstancia por la cual fuera cambiado el número de la tarjeta \n" +
//                "de crédito, o de la cuenta antes mencionada, en caso de pérdida, expiración, perdida, o cambio de número, \n" +
//                "me comprometo a notificar en forma inmediata a “DVNET”, sobre el nuevo número asignado; de tal manera que el \n" +
//                "cambio de número indicado no será causa para no cancelar los valores que adeude a “DVNET”.</br></br>\n" +
//                "“DVNET” no asume ninguna responsabilidad sobre los cargos que la institución financiera por usted \n" +
//                "seleccionada le cobra por prestar ese servicio a su cliente.</br></br>\n" +
//                "El cliente declara conocer que la información suministrada es verídica y manifiesta su conocimiento \n" +
//                "expreso e irrevocable a la “DVNET”, a posible relacionada de sus derechos y obligaciones o a quien pudiese \n" +
//                "ostentar a futuro a cualquier título, la calidad de acreedor de los valores adeudados por el cliente, por \n" +
//                "concepto de los servicios prestados para:</br></br>\n" +
//                "<ol>\n" +
//                "   <li>\n" +
//                "   Consultar, en cualquier tiempo, en los burós de información crediticia, toda la información \n" +
//                "   relevante que permite a “DVNET” conocer el desempeño del cliente, como deudor y su capacidad de \n" +
//                "   pago, valorar el riesgo futuro en caso de concederle un crédito por el servicio a prestarse.\n" +
//                "   </li>\n" +
//                "   <li>\n" +
//                "   Reportar en los burós de información crediticia en forma directa o por intermedio de la Superitendencia \n" +
//                "   de Bancos y Seguros, datos relativos a:</br>\n" +
//                "   2.1 Cumplimiento oportuno o incumplimiento de las obligaciones crediticias pasadas, \n" +
//                "   presentes o futuras el cliente.</br>\n" +
//                "   2.2 Información comercial, financiera y económica que el cliente haya entregado o que conste en \n" +
//                "   registros públicos, bases de datos públicas o documentos públicos.</br></br>  \n" +
//                "   </li>\n" +
//                "   <li>\n" +
//                "   Conservar, tanto de manera interna en la “Compañía”, como en los burós de información crediticia, \n" +
//                "   con las debidas autorizaciones y durante el periodo necesario, la información detallada en el numeral \n" +
//                "   2 de esta declaración.\n" +
//                "   </li>\n" +
//                "</ol>\n" +
//                "Esta autorización expresa del cliente permitirá a “DVNET” y a los burós de información crediticia divulgar \n" +
//                "la información mencionada para evaluar los riesgos de conceder al cliente un crédito por el servicio a \n" +
//                "prestar, elaborar estadísticas, derivar, mediante modelos matemáticos, conclusiones de ellas, y \n" +
//                "demás fines autorizados por la ley.</br></br>\n" +
//                "Si a pesar de existir la factibilidad técnica y comercial para prestar el servicio solicitado por el cliente, \n" +
//                "“DVNET” se reserva la facultad de negar la solicitud del servicio</br></br>\n" +
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
//                "No. Cédula: " + partner.getIdentification_id() + "</br>\n" +
//                "</td>\n" +
//                "<td>\n" +
//                "Nombre y apellido: " + user.getName() + "</br>\n" +
//                "No. Cédula: " + user.getIdentificacion() + "</br>\n" +
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
                "<h1 class=\"titulo\">CONTRATO DE PRESTACIÓN DE SERVICIOS DE FACTURACIÓN ELECTRÓNICA</h1>\n" +
                "Conste por el presente documento un Contrato de Prestación de Servicios de Facturación Electrónica, que, en forma libre y voluntaria, se celebra al tenor de las siguientes cláusulas:\n" +
                "<h1 class=\"subtitulo\">CLÁUSULA PRIMERA: OTORGANTES.-</h1>\n" +
                "Comparecen a la celebración y suscripción del presente contrato, las siguientes partes:</br></br>\n" +
                "<strong>1.1.</strong> Por una parte, la compañía <strong>DV TELEVISION DVTV S.A.</strong>, con Registro Único de Contribuyentes (RUC) No. 0992929170001, \n" +
                "debidamente representado por su Gerente General, el señor Raúl Daniel Zamora Vera, y como tal representante legal, \n" +
                "quien suscribe, parte a la que en adelante se la podrá denominar distintivamente como “DVNET” o la “Compañía”; y,</br></br>\n" +
                "<strong>1.2.</strong> Por otra parte: </br>\n" +
                "Nombre/razón social: " + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + "</br>\n" +
                "Cédula/RUC: " + partner.getIdentification_id() + " E-mail: " + partner.getEmail() + "</br>\n" +
                "Dirección: " + partner.getStreet() + "</br>\n" +
                "Provincia: " + partner.getProvincia() + " Cantón: " + partner.getCanton() + " Parroquia:______________</br>\n" +
                "Dirección donde será prestado el servicio: " + partner.getStreet() + "</br></br>\n" +
                "A quienes para efectos del presente, en su conjunto, se las denominará como “Las Partes”.</br></br>\n" +
                "<h1 class=\"subtitulo\">CLÁUSULA SEGUNDA: ANTECEDENTES.-</h1>\n" +
                "<strong>2.1.</strong> “DVNET” es una compañía anónima legalmente constituida en el Ecuador, especializada, \n" +
                "entre otros, en actividades de suministro de telecomunicaciones, acceso a televisión por cable e internet, \n" +
                "así como aplicaciones derivadas.</br></br>\n" +
                "<strong>2.2.</strong> Dentro de la prestación de los productos mencionados, “DVNET” ofrece a sus clientes diversos \n" +
                "servicios adicionales y opcionales, entre ellos, el Servicio de Facturación Electrónica (en adelante, “el servicio”), \n" +
                "el cual es proporcionado por la “Compañía” a aquellos clientes que soliciten el alta/acceso o deseen adquirir el mismo.</br></br>\n" +
                "<strong>2.3</strong> En virtud de lo expuesto, es voluntad del “Cliente” obtener el servicio de facturación \n" +
                "electrónica ofrecido por “DVNET”, por lo que se adhiere de forma expresa, plena y sin reservas a los términos y \n" +
                "condiciones que constan en el presente contrato.</br></br>\n" +
                "<h1 class=\"subtitulo\">CLÁUSULA TERCERA: OBJETO.-</h1>\n" +
                "Con los antecedentes expuestos, “DVNET” se obliga a prestar el Servicio de Facturación Electrónica a favor \n" +
                "del “Cliente”, siempre y cuando, “el Usuario” exprese su voluntad, a través de medios físicos, electrónicos \n" +
                "y/o telefónicos, de adquirir “el servicio”, como adicional al suministro principal que haya contratado o de \n" +
                "manera independiente al mismo, una vez el “Usuario” haya presentado su solicitud y la “Compañía” verifique \n" +
                "el cumplimiento de los requisitos legales respectivos, procederá a dar de alta “el servicio”.</br></br>\n" +
                "\n" +
                "Para el efecto, “DVNET” se compromete a poner a disposición del “Cliente”, tras la suscripción del presente \n" +
                "instrumento, el acceso a la plataforma web (“facturacionelectronica.dvnet.ec”), mediante un usuario y \n" +
                "clave provisional, en los términos y condiciones que se detallarán a continuación.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CLÁUSULA CUARTA: CARACTERÍSTICAS DEL SERVICIO DE FACTURACIÓN ELECTRÓNICA.-</h1>\n" +
                "<strong>4.1</strong> “DVNET” prestará “el servicio” al “Cliente” a través de un sistema en línea, cuyo dominio \n" +
                "será “facturacionelectronica.dvnet.ec” (en adelante, la “plataforma web”), mediante el cual el “Cliente” \n" +
                "accederá con un usuario y una clave provisional; los que serán creados y entregados al “Usuario” a la suscripción \n" +
                "del presente instrumento.</br></br>\n" +
                "<strong>4.2</strong> Dentro de la “plataforma web” el “Cliente” podrá generar las facturas electrónicas que requiera, \n" +
                "con un máximo de ________ documentos electrónicos al año que conforman el plan ___________.</br></br>\n" +
                "<strong>4.3</strong> “El Servicio” permitirá, además de la visualización en pantalla de la imagen de la factura \n" +
                "electrónica y de su detalle, su impresión y descarga en formato “xml”, conforme a los esquemas .xsd emitidos \n" +
                "por el Servicio de Rentas Internas (en adelante, “SRI”), en el ordenador personal del “Usuario” para sus \n" +
                "fines pertinentes.</br></br>\n" +
                "En el caso de existir comunicaciones ordinarias, relacionadas con el presente contrato, el “Usuario” recibirá \n" +
                "una copia en formato .pdf del comunicado que se le haya emitido, pudiéndoselo descargar en el dispositivo electrónico \n" +
                "donde se encuentre habilitada la plataforma web.</br></br>\n" +
                "<strong>4.4</strong> Para que las facturas electrónicas emitidas por la “plataforma web” tengan plena vigencia \n" +
                "deberá el “Cliente” contar con firma electrónica y certificado de autorización emitido por el SRI, conforme a \n" +
                "lo que se detallará en la siguiente cláusula como requisito previo para solicitar “el servicio”.</br></br>\n" +
                "<strong>4.5</strong> La emisión de las facturas electrónicas dentro de la “plataforma web” será de \n" +
                "entero control de “El Cliente”.</br></br>\n" +
                "<strong>4.6</strong> “El Servicio” no incluye el almacenamiento de información, únicamente el de emisión de \n" +
                "los documentos electrónicos, en caso de requerir acceso a la nube se entenderá como un producto adicional, \n" +
                "el cual tendrá un cargo a la tarifa básica.</br></br>\n" +
                "<strong>4.7</strong> “DVNET” realizará el mantenimiento de la “plataforma web” para el funcionamiento \n" +
                "adecuado de la misma.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CLÁUSULA QUINTA: ALTA EN EL SERVICIO DE FACTURACIÓN ELECTRÓNICA.-</h1>\n" +
                "<strong>5.1. Condiciones para que el “Cliente” acceda a “el servicio”:</strong></br></br>\n" +
                "<strong>a)</strong> El “Cliente” manifestó a “DVNET”, a través de medios físicos, electrónicos y/o \n" +
                "telefónicos, su voluntad de adquirir “el servicio”, el cual fue plasmado mediante un formulario de \n" +
                "solicitud proporcionada por la “Compañía”;</br></br>\n" +
                "<strong>b)</strong> Asimismo, para que el “Cliente” pueda acceder a “el servicio”, deberá presentar, \n" +
                "junto a su petición o suscripción del presente instrumento, lo siguiente:</br>\n" +
                "<ul style= \"list-style-type: circle;\">\n" +
                "   <li>\n" +
                "   Certificado digital de firma electrónica, el cual debe ser emitido o actualizado por cualquier \n" +
                "   entidad autorizada; tales como: Banco Central del Ecuador, Consejo de la Judicatura, Security Data o ANF AC Ecuador.\n" +
                "   </li>\n" +
                "   <li>\n" +
                "   Solicitud de autorización en el SRI para la emisión de comprobantes electrónicos en ambientes \n" +
                "   de “pruebas” y “producción”.\n" +
                "   </li>\n" +
                "</ul></br>\n" +
                "<strong>5.2. Acceso a “el servicio”:</strong></br></br>\n" +
                "Para la contratación del Servicio de Facturación Electrónica deberá cancelar un valor por implementación,\n" +
                "esto será para usuarios vigentes y nuevos.</br></br>\n" +
                "Para los “Usuarios” que sean titulares de varios contratos de suministro en vigor, el alta en el Servicio de \n" +
                "Facturación Electrónica se aplicará individualmente por contrato, no aplicándose al resto de contratos de \n" +
                "titularidad del “Cliente”. Asimismo, Si el “Usuario” realizara nuevos contratos de suministro con “DVNET”, \n" +
                "se le ofrecerá igualmente el alta en el servicio, para lo cual, en caso de dar su consentimiento, tendrá que \n" +
                "suscribir el presente instrumento adhiriéndose a los términos y condiciones inherentes a “el servicio”.</br></br>\n" +

                "El alta del “Servicio” se realizará a través de la aplicación de “DVNET” (facturacionelectronica.dvnet.ec), \n" +
                "tras la solicitud/petición del cliente, por cualquiera de los canales de atención físicos o virtuales de la \n" +
                "“Compañía”. Para ello, el “Cliente” deberá identificarse en el formulario de solicitud con los datos básicos \n" +
                "requeridos para la creación de la cuenta y contraseña provisional que serán entregados al “Cliente”, vía correo \n" +
                "electrónico y/o de manera física, por “DVNET” una vez suscrito el presente instrumento.</br></br>\n" +

                "Se entenderá que desde la presentación de la solicitud/petición y suscripción del presente instrumento el \n" +
                "“Cliente” ha manifestado su voluntad, y por ende consentimiento, para ser dado de alta en el Servicio de \n" +
                "Facturación Electrónica, aceptando los términos y condiciones del mismo.</br></br>\n" +

                "Cuando “DVNET” permita el acceso al “servicio” al “Cliente”, éste deberá acceder a la plataforma \n" +
                "(“facturacionelectronica.dvnet.ec”) con el usuario y contraseña provisional proporcionados por la “Compañía” \n" +
                "y proceder a realizar el cambio de contraseña. Una vez, efectuado, el “Usuario” tendrá a entera disposición \n" +
                "la “plataforma web” dentro de los fines y alcances de “el servicio” adquirido.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CLÁUSULA SEXTA: TARIFA PLAN BÁSICO Y FORMA DE PAGO DE LOS SERVICIOS.-</h1>\n" +
                "Las tarifas que el “Cliente” debe cancelar por la prestación del Servicio de Facturación Electrónica, objeto \n" +
                "del presente instrumento, a “DVNET”, correspondiente, son los detallados en el Anexo 1, que se adjunta como habilitante en el\n" +
                "presente contrato.</br></br>\n" +
                "Las tarifas detalladas serán canceladas por el “Cliente” de manera ________ a <strong>“DVNET”</strong>, \n" +
                "mediante las siguientes formas de pago:</br></br>\n" +
                "<table class=\"tabla1\" border=\"1\"; cellpadding=\"0\"; cellspacing=\"1\";>\n" +
                "    <tr>\n" +
                "    <td><strong>Forma de pago</strong></td>\n" +
                "    <td class=\"sino\">SI</td>\n" +
                "    <td class=\"sino\">NO</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "    <td>Efectivo, pago directo a través de ventanilla del prestador del servicio</td>\n" +
                "    <td></td>\n" +
                "    <td></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "    <td>Débito automático cuenta de ahorro, o corriente, o tarjeta de crédito</td>\n" +
                "    <td></td>\n" +
                "    <td></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "    <td>Pago en línea a través Instituciones Financieras y Auxiliares Financieros \n" +
                "    o de Pago, autorizados y/o medios electrónicos</td>\n" +
                "    <td></td>\n" +
                "    <td></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "    <td>Cualquier otro medio de pago determinado y autorizado por la Junta de la \n" +
                "    Política y Regulación Monetaria y Financiera</td>\n" +
                "    <td></td>\n" +
                "    <td></td>\n" +
                "    </tr>\n" +
                "    </table></br></br>\n" +
                "En caso de que el “Cliente” desee cambiar su modalidad de pago a otra disponible, deberá comunicarse con “DVNET”, \n" +
                "a través de cualquier medio autorizado de atención, virtual o físico, a efecto de realizar el cambio requerido.</br></br>\n" +

                "La facturación por el servicio contratado iniciará a partir de la fecha " + fecha + " en que \n" +
                "el “Cliente” ha sido dado de alta “el servicio”, de conformidad el numeral 5.2. de la cláusula \n" +
                "quinta del presente instrumento, más los impuestos que por ley correspondan. De igual manera, se facturará \n" +
                "únicamente hasta la fecha de terminación del Contrato. En caso que llegase a suspenderse el servicio, por \n" +
                "factores técnicos concernientes y/o de responsabilidad de “DVNET”, se facturará por los servicios efectivamente \n" +
                "prestados y aquellos que se justifiquen de conformidad a la normativa vigente.</br></br>\n" +

                "En caso de que existan reajustes de la tarifa de los servicios contratados por el “Cliente”, “DVNET” \n" +
                "notificará con un término de 30 días de anticipación a la modificación por cualquier medio masivo, \n" +
                "indicándole de manera clara las nuevas características, mejoras y/o tarifas a las condiciones que apliquen.</br></br>\n" +

                "El “Cliente” podrá solicitar el cambio del plan contratado, al tenor de lo dispuesto en la cláusula \n" +
                "siguiente. La aplicación del nuevo servicio regirá desde la activación y bajo los nuevos términos y \n" +
                "condiciones del nuevo plan.</br></br>\n" +
                "El “Cliente” acepta expresamente recibir la factura electrónica emitida por “DVNET” por la prestación de sus servicios \n" +
                "al siguiente correo electrónico: " + partner.getEmail() + "</br></br>\n" +

                "En caso de mora, el “Cliente” se compromete, de forma expresa,  a cancelar el valor total adeudado más el \n" +
                "interés por mora calculado con las tasas vigentes y establecidas por “DVNET”, de acuerdo a la fecha en que \n" +
                "debió efectuarse el pago, calculados desde el día siguiente de la fecha máxima de pago constante en la \n" +
                "factura, hasta el día efecto de pago del valor adeudado, así como también, los cargos de cobranza generados \n" +
                "por “DVNET”, conforme correspondan y aquellos que se justifiquen en virtud de la normativa vigente.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CLÁUSULA SÉPTIMA: CARGOS ADICIONALES Y OTRAS TARIFAS.-</h1>\n" +
                "Se entiende como “cargos adicionales y otras tarifas” a todos aquellos servicios que no se encuentren \n" +
                "detallados en las cláusulas cuarta y quinta del presente contrato, en caso de que el “Cliente” desee acceder \n" +
                "a ellos, en cuanto a su valor y detalle deberán remitirse al <strong>Anexo 3</strong>, por lo que, acepta que en el caso de \n" +
                "los cargos, los valores respectivos serán sumados a la tarifa del plan básico contratado; y, con respecto \n" +
                "a las “tarifas”, si el valor corresponde a un nuevo plan, el valor inicial será reemplazado por este.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CLÁUSULA OCTAVA: PLAZO.-</h1>\n" +
                "El presente contrato tendrá una permanencia mínima de 2 (dos) años contados a partir de la fecha en la cual \n" +
                "“DVNET” otorgue el alta del “servicio” al “Cliente”. El tiempo mencionado es \n" +
                "aplicado para usuarios nuevos y activos.</br></br>\n" +

                "El plazo de 2 (dos) años será renovado de manera automática, e indefinida, salvo que una de las partes \n" +
                "exprese su voluntad de darlo por terminado, lo cual será comunicado por escrito dentro de, por lo menos, 15 \n" +
                "(quince) días de anticipación, sin que tal hecho de lugar al pago de ninguna clase de indemnización.</br></br>\n" +
                "En el caso en el que el “Cliente” quiera proceder a dar de baja “el servicio” deberá cancelar todos los \n" +
                "valores adeudados por el servicio prestado hasta el momento de su terminación y, además, cancelar el valor \n" +
                "proporcional por el tiempo que faltase para cumplir el plazo aquí estipulado.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CLÁUSULA NOVENA: GARANTÍA.-</h1>\n" +
                "Durante la vigencia del presente contrato, “DVNET” se compromete a emplear los esfuerzos comercialmente \n" +
                "razonables para proporcionar “el servicio” a “el Cliente”, de conformidad con los estándares de la \n" +
                "industria generalmente aceptados.</br></br>\n" +
                "Sin perjuicio de lo expuesto, “DVNET” otorgará a el “Cliente” una garantía en caso de que exista una \n" +
                "falla técnica en la “plataforma web” que sea responsabilidad directa de la “Compañía”. En consecuencia \n" +
                "“DVNET” se obliga a:</br></br>\n" +

                "<strong>9.1</strong> Brindar asistencia vía remota, con el personal técnico especializado de la “Compañía” \n" +
                "dentro de las 24 horas de comunicado el suceso.</br></br>\n" +

                "<strong>9.2</strong> Brindar asistencia de manera presencial, con el personal técnico especializado de la \n" +
                "“Compañía”, en caso de que la falla técnica así lo requiera o si pasadas las 24 horas no se hubiese solucionado.</br></br>\n" +

                "<strong>9.3</strong> En caso de que por la falta de acceso a la “plataforma web” el “Cliente” no haya podido \n" +
                "cumplir con sus obligaciones tributarias pertinentes, “DVNET” se obliga a prestar el servicio de manera gratuita \n" +
                "por el mismo tiempo que se haya encontrado inactivo, una vez cumplido el plazo del contrato.</br></br>\n" +
                "“DVNET” se obliga a cumplir con la garantía expuesta, siempre que:</br></br>\n" +

                "<strong>a.</strong> Los dispositivos electrónicos del “Cliente” se encuentren en un buen estado operacional \n" +
                "que permita el funcionamiento óptimo de la “plataforma web”.</br></br>\n" +
                "<strong>b.</strong> El “Cliente” brindará información pertinente para la resolución de problemas y, para \n" +
                "el alojamiento en servidores propios, cualquier acceso que “DVNET” puede necesitar para identificar, \n" +
                "reproducir y solventar los problemas.</br></br>\n" +
                "Asimismo, “DVNET” está obligado únicamente a reanudar la ejecución del servicio aquí contratado cuando se trate \n" +
                "de fallas técnicas de la “plataforma web” relacionadas con su manejo directo. “DVNET” no estará obligado a \n" +
                "cumplir con la presente garantía en caso que el daño o falla obedezca a un caso fortuito o fuerza mayor en los \n" +
                "términos de ley, que sea originado por causas imputables al “Cliente” y/o un tercero o que se incumplan los \n" +
                "puntos a. y b. de la presente cláusula.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CLÁUSULA DÉCIMA: OBLIGACIONES Y RESPONSABILIDADES DE “DVNET”.-</h1>\n" +
                "“DVNET” adquiere los siguientes compromisos con el “Cliente” que contrate el Servicio de Facturación Electrónica:</br></br>\n" +
                "<ul style= \"list-style-type: square;\">\n" +
                "    <li>Prestar “el servicio” contratado por el “Cliente” de forma continua y permanente, durante el tiempo de \n" +
                "    duración de este Contrato, en los términos y condiciones aquí establecidos, salvo las situaciones de \n" +
                "    fuerza mayor o caso fortuito, según las establecidas en el artículo 30 de Código Civil ecuatoriano vigente.</br></br>\n" +
                "    </li>\n" +
                "    <li>\n" +
                "    “DVNET” estará exenta de responsabilidad en caso de mal uso de los servicios contratado por parte del \n" +
                "    “Usuario”, por lo que no estará obligada al pago de indemnización de ninguna clase.</br></br>\n" +
                "    </li>\n" +
                "    <li>\n" +
                "    Informar sobre las características de los servicios ofertados, como promociones de planes, tarifas, \n" +
                "    precios, saldos, cargos y otros servicios informativos, a través de medios físicos y electrónicos.</br></br>\n" +
                "    </li>\n" +
                "    <li>\n" +
                "    En caso de que “DVNET” requiera modificar las características técnicas del “Servicio” contratado, \n" +
                "    notificará por escrito por medios físicos, electrónicos o telefónicos a el “Usuario”, en un \n" +
                "    plazo no mayor a 30 (treinta) días de anticipación a la modificación.</br></br>\n" +
                "    </li>\n" +
                "    <li>\n" +
                "    “DVNET” deberá permitir a el “Usuario” modificar su contraseña a través de la propia funcionalidad \n" +
                "    que le ofrece la web, en el momento que el “Cliente” lo requiera.</br></br>\n" +
                "    </li>\n" +
                "    <li>\n" +
                "    “DVNET” se obliga a observar las máximas garantías exigidas en el ámbito legislativo.</br></br>\n" +
                "    </li>\n" +
                "</ul>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CLÁUSULA UNDÉCIMA: OBLIGACIONES, RESPONSABILIDADES Y PROHIBICIONES DEL “CLIENTE”.-</h1>\n" +
                "<strong>11.1. Obligaciones y responsabilidades:</strong></br></br>\n" +
                "El “Usuario” adquiere los siguientes compromisos al contratar el Servicio de Facturación Electrónica:</br></br>\n" +
                "<ul style= \"list-style-type: square;\">\n" +
                "   <li>Cumplir con los términos y condiciones estipulados en el presente instrumento.</br></br></li>\n" +
                "   <li>Realizar un buen uso del “servicio” contratado y para los fines convenidos.</br></br></li>\n" +
                "   <li>El “Cliente” será responsable de la emisión de las facturas electrónicas en la “plataforma web” \n" +
                "   proporcionada por “DVNET”, así como de llevar sus libros contables conforme a las exigencias de la \n" +
                "   normativa tributaria vigente.</br></br></li>\n" +
                "   <li>El “Cliente” será responsable del almacenamiento de las facturas electrónicas que emita dentro de \n" +
                "   la “plataforma web” en cualquier dispositivo apto para aquel fin.</br></br></li>\n" +
                "   <li>Informarse adecuadamente y de manera oportuna por los medios electrónicos y físicos que “DVNET” \n" +
                "   pone a su disposición.</br></br></li>\n" +
                "   <li>Notificar a “DVNET” cuando ocurra la interrupción o daño en la “plataforma web” concernientes \n" +
                "   a fallas técnicas relacionadas directamente con la “Compañía”.</br></br></li>\n" +
                "   <li>Pagar el servicio contratado y efectivamente prestado conforme lo determina el presente contrato y \n" +
                "   el ordenamiento jurídico vigente, en las fechas de facturación correspondiente.</br></br></li>\n" +
                "   <li>Cumplir con las obligaciones o resoluciones emitidas por el SRI y demás que se derivan del \n" +
                "   ordenamiento jurídico vigente.</br></br></li>\n" +
                "   <li>Comunicar a “DVNET” cualquier cambio en la dirección de correo electrónico en la que desee \n" +
                "   recibir todas las comunicaciones y documentos pertinentes para la prestación de el “servicio”. \n" +
                "   En caso de que el Cliente no cumpliera con este compromiso, “DVNET” no se responsabiliza del \n" +
                "   correcto envío de los mensajes.</br></br></li>\n" +
                "   <li>Garantizar y responder, en todo caso, de la veracidad, exactitud, vigencia y autenticidad de \n" +
                "   los datos personales facilitados a través del formulario de solicitud/petición de alta al “servicio”.</br></br></li>\n" +
                "</ul>\n" +
                "<strong>11.2. Prohibiciones:</strong></br></br>\n" +
                "<ul>\n" +
                "   <li>El “Cliente” tiene estrictamente prohibido compartir su usuario y contraseña con terceros.</br></br></li>\n" +
                "   <li>El “Cliente” no podrá utilizar diversos dispositivos electrónicos de manera paralela. Si mediante \n" +
                "   el método de validación interna “DVNET” determina diversas direcciones IP, que se encuentren utilizando \n" +
                "   “el servicio” de manera continua y simultánea procederá a realizar el cargo respectivo.</br></br></li>\n" +
                "   <li>El “Usuario” no podrá utilizar el “servicio” para fines ilícitos, fraude o perjuicio a terceros, \n" +
                "   o que contravengan a las normativas referentes a lavado de activos.</br></br></li>\n" +
                "</ul>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CLÁUSULA DÉCIMA SEGUNDA: CESIÓN DE DERECHO.-</h1>\n" +
                "El “Cliente” no podrá, en ningún caso, ceder, ni transferir, en todo o en parte los derechos y obligaciones \n" +
                "que le confiere el presente contrato, sus anexos o modificaciones de los mismos, salvo que “DVNET” lo \n" +
                "autorice por escrito.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CLÁUSULA DÉCIMA TERCERA: DIVISIBILIDAD.-</h1>\n" +
                "Las partes acuerdan que en caso de que cualquier estipulación de este contrato fuera declarada \n" +
                "inválida o nula por autoridad competente, dicha declaración afectará única y exclusivamente a dicha \n" +
                "estipulación, separándose de este contrato y manteniéndose el mismo válido en el resto de sus partes.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CLÁUSULA DÉCIMA CUARTA: NATURALEZA JURÍDICA Y NORMATIVA TRIBUTARIA PERTINENTE.-</h1>\n" +
                "El presente contrato de prestación de servicios es de carácter civil, el cual se fundamenta en la siguiente normativa:</br></br>\n" +
                "<strong>14.1</strong> Ley de Comercio Electrónico, Firmas Electrónicas y Mensajes de Datos, publicado Registro \n" +
                "Oficial Suplemento No. 557 el 17 de abril de 2002, con última reforma el 27 de agosto de 2021 y su Reglamento \n" +
                "General, los cuales regulan la prestación de servicios electrónicos a través de redes de información; comercio \n" +
                "electrónico y la protección de los usuarios de estos sistemas.</br></br>\n" +
                "<strong>14.2</strong> Resolución No. NAC-DGERCGC18-00000431, emitida por el SRI la cual establece los sujetos \n" +
                "pasivos que hasta el 2024 deben encontrarse, obligatoriamente, emitiendo comprobantes de venta, comprobantes \n" +
                "de retención y documentos complementarios de manera electrónica.</br></br>\n" +
                "<strong>14.3</strong> Resolución No. NAC-DGERCGC18-00000175, emitida por el SRI en junio de 2018, reforma \n" +
                "resolución No. NAC-DGERCGC18-00000428, que dicta las normas para la transmisión de información sobre \n" +
                "documento electrónicos a través de impresoras fiscales.</br></br>\n" +
                "<strong>14.4</strong> Resolución No. NAC-DGERCGC18-00000233, emitida por el SRI en junio de 2018, en la \n" +
                "cual se establecen las normas para emisión, entrega y transmisión de comprobantes de venta, retención y \n" +
                "documentos complementarios expedidos por sujetos pasivos autorizados, mediante el esquema de comprobantes \n" +
                "electrónicos.</br></br>\n" +
                "<strong>14.5</strong> Resolución No. NAC-DGERCGC16-00000287, emitida por el SRI en julio de 2016, en la cual \n" +
                "expide definiciones para la emisión de comprobantes emitidos por medios digitales o electrónicos de pago.</br></br>\n" +
                "<strong>14.6</strong> Resolución No. NAC-DGERCGC14-00790, emitida por el SRI en octubre de 2014, la cual expide \n" +
                "las normas para la emisión y autorización de comprobantes de venta, retención y documentos complementación \n" +
                "mediante comprobantes electrónicos.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CLÁUSULA DÉCIMA QUINTA: LIMITACIÓN DE LA RESPONSABILIDAD.-</h1>\n" +
                "“DVNET” se encuentra exento de responsabilidad en aquellos actos que no sean imputados directamente a la \n" +
                "prestación de su servicio, fuerza mayor o caso fortuito, asimismo, aquellos ilícitos que pudiera ocasionar \n" +
                "el “Cliente” y que ocasionen perjuicio a un tercero.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CLÁUSULA DÉCIMA SEXTA: PROTECCIÓN DE DATOS.-</h1>\n" +
                "<strong>16.1. Recogida y tratamiento de datos de carácter personal:</strong></br></br>\n" +
                "La recogida y tratamiento de datos de carácter personal recabados en el formulario de solicitud/petición, \n" +
                "tienen como finalidad proporcionar el servicio de facturación electrónica solicitada.</br></br>\n" +
                "De acuerdo con lo dispuesto en la Ley Orgánica de Protección de Datos Personales y su normativa \n" +
                "supletoria vigente, el “Cliente” ha expresado su consentimiento para el tratamiento de sus datos personales, \n" +
                "para efectos de la prestación del “servicio”, es decir, las obligaciones contractuales emanadas del presente instrumento.</br></br>\n" +
                "Los datos solicitados en los formularios/peticiones, incluidos los que consten en la “plataforma web”, tienen \n" +
                "carácter obligatorio, salvo que en los mismos se indicase otra cosa, autorizando el “Cliente” su tratamiento \n" +
                "para las finalidades indicadas por “DVNET”.</br></br>\n" +
                "En ningún caso podrán incluirse en el formulario de petición datos de carácter personal correspondientes a \n" +
                "terceras personas, salvo que el solicitante hubiese recabado con carácter previo su consentimiento en los \n" +
                "términos exigidos por la Ley Orgánica de Protección de Datos Personales, respondiendo con carácter exclusivo del \n" +
                "incumplimiento de esta obligación y cualquier otra en materia de datos de carácter personal.</br></br>\n" +
                "<strong>16.2. Derechos de acceso, rectificación, oposición y cancelación:</strong></br></br>\n" +
                "El Cliente puede ejercitar sus derechos de acceso, rectificación, cancelación y oposición, en los términos \n" +
                "establecidos legalmente, comunicándolo por escrito a “DVNET”, exceptuando aquellas excepciones determinadas en \n" +
                "el artículo 18 de Ley Orgánica de Protección de Datos Personales. En dicho sentido, autorizo que “DVNET” proporcione \n" +
                "a las entidades pertinentes mi información crediticia, por ser una obligación emanada del presente contrato.</br></br>\n" +
                "<strong>16.3. Empleo de cookies:</strong></br></br>\n" +
                "“DVNET” puede emplear cookies, al objeto de facilitarle una gestión más ágil y eficaz en de los servicios prestados.</br></br>\n" +
                "Las cookies son un método de autentificación de usuarios que permiten guardar constancia del identificador \n" +
                "asignado en el momento del registro del cliente, de manera que en lo sucesivo se evita reiterar los trámites \n" +
                "propios de nuevos registros.</br></br>\n" +
                "El cliente puede configurar el navegador de su ordenador para que le advierta del acceso de cookies y, en su caso, \n" +
                "de impedir la recepción de las mismas en el disco duro.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CLÁUSULA DÉCIMA SÉPTIMA: CONFIDENCIALIDAD.-</h1>\n" +
                "“DVNET”, su representante legal y las personas que intervengan en la ejecución del presente contrato, deberán \n" +
                "guardar la más estricta confidencialidad de toda información que el “Cliente” detalle en sus documentos electrónicos \n" +
                "con motivo o por razón del presente instrumento, quedando expresamente prohibida su divulgación a terceros, \n" +
                "so pena de incurrir en las responsabilidades civiles y penales que conllevan su inobservancia.</br></br>\n" +
                "La confidencialidad estipulada en esta cláusula será durante el tiempo que dure el presente contrato y subsistirá\n" +
                "en forma indefinida después de su terminación.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CLÁUSULA DÉCIMA OCTAVA: TERMINACIÓN DEL CONTRATO.-</h1>\n" +
                "“Las partes” intervinientes acuerdan en forma recíproca que el presente contrato, sus anexos y demás documentos \n" +
                "que forman parte integrante del mismo, podrán ser declarados terminados en los siguientes casos:</br></br>\n" +
                "<strong>18.1 </strong>Por vencimiento del plazo del contrato, para lo cual, cualquiera de las partes podrá \n" +
                "notificar a la otra con 15 (quince) días hábiles las terminación por esta causa.</br></br>\n" +
                "<strong>18.2 </strong>Por falta de pago de 15 (quince) a 30 (treinta) días posteriores a la emisión de la \n" +
                "factura de “DVNET”.</br></br>\n" +
                "<strong>18.3 </strong>Por insolvencia declarada por juez competente, concurso de acreedores, quiebra, \n" +
                "disolución o liquidación, de cualquiera de las partes intervinientes.</br></br>\n" +
                "<strong>18.4 </strong>Cuando por circunstancias imprevistas, técnicas o económicas, o causas de fuerza mayor o \n" +
                "caso fortuito debidamente justificado, no fuere posible o conveniente para los intereses de “Las Partes” \n" +
                "ejecutar total o parcialmente el contrato.</br></br>\n" +
                "<strong>18.5 </strong>Por mandato judicial o disposición de autoridad administrativa competente.</br></br>\n" +
                "<strong>18.6 </strong>Por incumplimiento del “Cliente” de las obligaciones previstas en el presente contrato.</br></br>\n" +
                "<strong>18.7 </strong>Cuando el “Cliente” cediera total o parcialmente el contrato sin autorización de “DVNET”.</br></br>\n" +
                "<strong>18.8 </strong>Por muerte del “Cliente”, lo cual, surtirá efectos cuando documentadamente se ponga en \n" +
                "conocimiento de “DVNET” del hecho acaecido.</br></br>\n" +
                "<strong>18.9 </strong>Por alquilar, revender o negociar de cualquier forma el servicio contratado.</br></br>\n" +
                "<strong>18.10 </strong>Si el “Cliente” utiliza el “servicio” contratado para fines distintos o para prácticas \n" +
                "contrarias a la ley, las buenas costumbres, la moral o cualquier otra forma que perjudique a la “Compañía”</br></br>\n" +
                "La terminación por mutuo acuerdo no implica la renuncia de las obligaciones, ni derechos a favor de “DVNET” \n" +
                "y del “Cliente”. En este caso, el “Usuario” deberá pagar todos los valores adeudados por los servicios prestados \n" +
                "y, además, el valor proporcional por el tiempo que faltase para cumplir el plazo aquí estipulado. Asimismo, “DVNET” \n" +
                "procederá inmediatamente a suspender el servicio sin previo aviso y sin que el “Cliente” tenga derecho a \n" +
                "indemnización o devolución de dinero alguna.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CLÁUSULA DÉCIMA NOVENA: JURISDICCIÓN, COMPETENCIA Y SOLUCIÓN DE CONTROVERSIAS.-</h1>\n" +
                "“Las partes” declaran expresamente que renuncian a fuero y domicilio, y convienen en que, para cualquier controversia \n" +
                "o diferencia que surja o se relacione con la interpretación o ejecución del presente contrato, se someterán \n" +
                "a la mediación del Centro de Arbitraje y Conciliación de la Cámara de Comercio de Guayaquil. En caso de no \n" +
                "llegarse a un acuerdo de mediación, las partes se someterán al arbitraje en derecho, de acuerdo al procedimiento \n" +
                "establecido en la Ley de Arbitraje y Mediación, en el Reglamento de dicho Centro y en las siguientes normas:</br></br>\n" +
                "<strong>19.1 </strong>Las partes designarán 1 (UN) sólo árbitro, que será seleccionado por sorteo, conforme \n" +
                "lo establecido en la Ley de Arbitraje, el cual contará con el suplente respectivo;</br></br>\n" +
                "<strong>19.2 </strong>El árbitro de dicho centro efectuará un arbitraje administrado en Derecho y confidencial, \n" +
                "y quedará facultado para dictar medidas cautelares solicitando el auxilio de funcionarios públicos, judiciales, \n" +
                "policiales y administrativos, sin que sea necesario acudir a un juez ordinario para tales efectos;</br></br>\n" +
                "<strong>19.3 </strong>El procedimiento arbitral tendrá lugar en las instalaciones del Centro de Arbitraje \n" +
                "y Conciliación de la Cámara de Comercio de Guayaquil;</br></br>\n" +
                "<strong>19.4 </strong>Las “PARTES” renuncian a la jurisdicción ordinaria, se obligan acatar el laudo arbitral \n" +
                "y se comprometen a no interponer ningún tipo de recurso en contra del mismo;</br></br>\n" +
                "<strong>19.5 </strong>El laudo arbitral será inapelable; y,</br></br>\n" +
                "<strong>19.6 </strong>Las costas procesales que se generen por el procedimiento arbitral, tales como derechos \n" +
                "del Centro de Arbitraje y Conciliación, honorarios de peritos, árbitros, abogados, etc., serán asumidos \n" +
                "íntegra y totalmente por quien determine el Árbitro en el laudo que expida.</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CLÁUSULA VIGÉSIMA: NOTIFICACIONES.-</h1>\n" +
                "Las notificaciones que correspondan, serán entregadas al “Cliente” a la dirección de correo \n" +
                "electrónico " + partner.getEmail() + ", así como en su domicilio " + partner.getStreet() + ".</br></br>\n" +
                "\n" +
                "<h1 class=\"subtitulo\">CLÁUSULA VIGÉSIMA PRIMERA: ACEPTACIÓN.-</h1>\n" +
                "El “Cliente” acepta el presente contrato, anexos y demás documentos, en su totalidad, para lo cual deja constancia \n" +
                "de lo anterior y firma junto a “DVNET” en tres ejemplares del mismo tenor, en la ciudad \n" +
                "de " + partner.getCanton() + " a los " + dia + " día del mes de " + mes +" del año " + anio + ".</br></br>\n" +
                "Firman las partes:</br></br></br></br>\n" +
                "\n" +
                "<table class=\"tabla2\" cellpadding = 0 cellspacing=\"1\">\n" +
                "<tr>" +
                "<td align=\"center\"><img src=\"data:image/png;base64," + firmapng + "\" width=\"200\" height=\"100\"></td>" +
                "<td align=\"center\"><img src=\"data:image/png;base64," + firmauserpng + "\" width=\"200\" height=\"100\"></td>\n" +
                "</tr>\n" +
                "<tr style=\"text-align: center;\">\n" +
                "<td>Firma del “Cliente”</td>\n" +
                "<td>Firma autorizada por “DVNET”</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td></br></br></td>\n" +
                "<td></br></br></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>\n" +
                "RAZÓN SOCIAL</br>\n" +
                "RUC: " + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + "</br>\n" +
                "Nombre y apellido: " + partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName() + "</br>\n" +
                "No. Cédula: " + partner.getIdentification_id() + "</br></br>\n" +
                "</br></br>\n" +
                "</td>\n" +
                "<td>\n" +
                "RUC: " + ruc + "</br>\n" +
                "Nombre y apellido: " + user.getName() + "</br>\n" +
                "No. Cédula: " + user.getIdentificacion() + "</br></br>\n" +
                "</br></br>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "\n" +
                "<h1 class=\"anexo\">\n" +
                "ANEXO 2</br>\n" +
                "AUTORIZACIÓN DE DÉBITO\n" +
                "</h1>\n" +
                "<strong>Aplica: </strong> Si ( &nbsp ) &nbsp No ( &nbsp )</br></br>\n" +
                "Como cliente de “DVNET”, adicionalmente ratifico mi compromiso de mantener los pagos de mi tarjeta de crédito \n" +
                "y los fondos suficientes en mi cuenta corriente o de ahorros dentro de los plazos estipulados, a fin de cubrir \n" +
                "los valores cuyos débitos automáticos autorizo a través del presente documento.</br></br>\n" +

                "Asimismo, expresamente me obligo a no renovar la presente autorización sin el previo consentimiento por \n" +
                "escrito de “DVNET”, por lo que libero de toda responsabilidad a la entidad financiera, banco o la emisora \n" +
                "de la tarjeta de crédito por los débitos o cargos efectuados en base a la presente autorización. De igual manera, \n" +
                "autorizo que, en caso de pérdida, o cualquier circunstancia por la cual fuera cambiado el número de la tarjeta \n" +
                "de crédito, o de la cuenta antes mencionada, en caso de pérdida, expiración o cambio de número, \n" +
                "me comprometo a notificar en forma inmediata a “DVNET”, sobre el nuevo número asignado; de tal manera que el \n" +
                "cambio de número indicado no será causa para no cancelar los valores que adeude a “DVNET”.</br></br>\n" +

                "“DVNET” no asume ninguna responsabilidad sobre los cargos que la institución financiera por usted \n" +
                "seleccionada le cobra por prestar ese servicio a su cliente.</br></br>\n" +
                "El cliente declara conocer que la información suministrada es verídica y manifiesta su conocimiento \n" +
                "expreso e irrevocable a la “DVNET”, a posible relacionada de sus derechos y obligaciones o a quien pudiese \n" +
                "ostentar a futuro a cualquier título, la calidad de acreedor de los valores adeudados por el cliente, por \n" +
                "concepto de los servicios prestados para:</br></br>\n" +
                "<ol>\n" +
                "   <li>\n" +
                "   Consultar, en cualquier tiempo, en los burós de información crediticia, toda la información \n" +
                "   relevante que permite a “DVNET” conocer el desempeño del cliente, como deudor y su capacidad de \n" +
                "   pago, valorar el riesgo futuro en caso de concederle un crédito por el servicio a prestarse.\n" +
                "   </li>\n" +
                "   <li>\n" +
                "   Reportar en los burós de información crediticia en forma directa o por intermedio de la Superintendencia \n" +
                "   de Bancos y Seguros, datos relativos a:</br>\n" +
                "   2.1 Cumplimiento oportuno o incumplimiento de las obligaciones crediticias pasadas, \n" +
                "   presentes o futuras el cliente.</br>\n" +
                "   2.2 Información comercial, financiera y económica que el cliente haya entregado o que conste en \n" +
                "   registros públicos, bases de datos públicas o documentos públicos.</br></br>  \n" +
                "   </li>\n" +
                "   <li>\n" +
                "   Conservar, tanto de manera interna en la “Compañía”, como en los burós de información crediticia, \n" +
                "   con las debidas autorizaciones y durante el periodo necesario, la información detallada en el numeral \n" +
                "   2 de esta declaración.\n" +
                "   </li>\n" +
                "</ol>\n" +
                "Esta autorización expresa del cliente permitirá a “DVNET” y a los burós de información crediticia divulgar \n" +
                "la información mencionada para evaluar los riesgos de conceder al cliente un crédito por el servicio a \n" +
                "prestar, elaborar estadísticas, derivar, mediante modelos matemáticos, conclusiones de ellas, y \n" +
                "demás fines autorizados por la ley.</br></br>\n" +

                "Si a pesar de existir la factibilidad técnica y comercial para prestar el servicio solicitado por el cliente, \n" +
                "“DVNET” se reserva la facultad de negar la solicitud del servicio</br></br>\n" +
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
                "No. Cédula: " + partner.getIdentification_id() + "</br>\n" +
                "</td>\n" +
                "<td>\n" +
                "Nombre y Apellido: " + user.getName() + "</br>\n" +
                "No. Cédula: " + user.getIdentificacion() + "</br>\n" +
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
                        "<strong>Fecha de Suscripción del Anexo: " + dia + " de " + mes + " de " + anio + "</strong>\n" +
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
                        "<strong>Régimen:</strong>\n" +
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
                    "<td style=\"padding: 2px; font-weight: bold;\">El contrato incluye permanencia mínima:</td>\n" +
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
                        "Valor Instalación / Configuración\n" +
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
                        "Plazo para instalar/activar el servicio (horas, días)\n" +
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
