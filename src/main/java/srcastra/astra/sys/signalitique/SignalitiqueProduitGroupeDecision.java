/*
 * SignalitiqueFournisseurGroupeDecision.java
 *
 * Created on 22 août 2002, 13:30
 */

package srcastra.astra.sys.signalitique;
import srcastra.astra.sys.Transaction;
import srcastra.astra.sys.Logger;
import srcastra.astra.sys.rmi.utils.Poolconnection;
import java.rmi.RemoteException;
import srcastra.astra.sys.classetransfert.*;
import srcastra.astra.sys.compress.CompressArray;
import java.util.ArrayList;
import java.sql.*;
import srcastra.astra.sys.rmi.utils.ServeurBuffer;
import srcastra.astra.sys.rmi.Exception.*;

/**
 *
 * @author  rene
 */
public class SignalitiqueProduitGroupeDecision implements srcastra.astra.sys.signalitique.Signalitique {
    
    /** Creates a new instance of SignalitiqueFournisseurGroupeDecision */
    public SignalitiqueProduitGroupeDecision() { 
    }
    
    public Object ChargeObject(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {
        String sqlrequete=null;
        Object returnValue = null;
        Gestionerreur_T tmperreur = new Gestionerreur_T();
        Gestionerreur_T tmperreur2 = new Gestionerreur_T();
     
        sqlrequete="SELECT g.gncleunik,g.frcleunik,g.ttcleunik,g.itcleunik,g.aecleunik,g.gndatetimecrea,g.gndatetimemodif,g.gncodetvavente,g.gntvainclusvente,g.gncodetvaachat,g.gntvainclusachat,g.gnnbravanteche,g.gnacompteminpp,g.gnprodstockiata,g.gnprodstockautre,g.gnpccomvente,g.gncominclpvent,g.gnpcacompte,g.gnpccomachat,g.gnpcclerepvend,g.gnpcclerepconcept,g.gnpcclerepmmere,g.snumerosessioncrea,g.snumerosessionmodif,g.frgtcleunik,g.gncomptevente,g.gncompteachat,g.gnfracomptepc,g.gnfrnbda,g.gnfrsoldepc,g.gnfrnbds,g.gnfranchise,g.caecleunik,g.tecleunik,v.vavaleurtva,v1.vavaleurtva FROM groupedecisionproduit  g,valeurtva v,valeurtva v1 where  lignecleunik="+objcleunik+" AND ctpro_cleunik ="+cas+"  and g.gncodetvavente=v.vacleunik and g.gncodetvaachat=v1.vacleunik;";
        //sqlrequete="SELECT * from groupedecision where frgtcleunik="+objcleunik+";";
   
        ResultSet tmpresult=Transaction.execrequete3(sqlrequete,connect.getConuser(),tmperreur);
        if(tmperreur.getErreurcode()==10000) {
            try{
                tmpresult.last();
                int tmpNbrLigne=tmpresult.getRow();
                if(tmpNbrLigne!=0) {
                    tmpresult.first();
                    String[] numSession=Transaction.renvNomUserPourSession(connect.getConuser(),tmpresult.getString(22),tmpresult.getString(23));
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    /* les 2 nveaux checkBox */   
                    Grpdecision_T tmpGrpDec=new Grpdecision_T(tmpresult.getInt(1),tmpresult.getInt(2),tmpresult.getInt(3),tmpresult.getInt(4),tmpresult.getInt(5), tmpresult.getDate(6),tmpresult.getDate(7),tmpresult.getInt(8),tmpresult.getInt(9),tmpresult.getInt(10),tmpresult.getInt(11),tmpresult.getInt(12),tmpresult.getFloat(13),tmpresult.getInt(14),tmpresult.getInt(15),tmpresult.getFloat(16),tmpresult.getInt(17),tmpresult.getFloat(18),tmpresult.getFloat(19),tmpresult.getFloat(20),tmpresult.getFloat(21),tmpresult.getFloat(22),tmpresult.getString(23),tmpresult.getString(24),tmpresult.getInt(25),tmpresult.getInt(26),tmpresult.getInt(27),tmpresult.getFloat(28),tmpresult.getInt(29),tmpresult.getInt(30),tmpresult.getInt(31),tmpresult.getInt(32),tmpresult.getInt(33),tmpresult.getInt(34),numSession[0],numSession[1], 0, 0);
                    tmpGrpDec.setValeurGenFloat1(tmpresult.getFloat(35));
                    tmpGrpDec.setValeurGenFloat2(tmpresult.getFloat(36));
                    tmpGrpDec.setErreurcode(tmperreur.getErreurcode());
                    tmpGrpDec.setErreurmessage(tmperreur.getErreurmessage());
                     String req="SELECT g.gncomptevente,c.ce_num,c.cate_cleunik FROM groupedecisionproduit g,compte c WHERE g.gncomptevente=c.ce_cleunik AND g.lignecleunik="+objcleunik+" AND g.ctpro_cleunik ="+cas+";";
                    ResultSet tmp2=Transaction.execrequete3(req,connect.getConuser(),tmperreur2);
                    tmp2.first();
                    tmpGrpDec.setIntitulecomptev(tmp2.getInt(2));
                    tmpGrpDec.setCategoriecomptev(tmp2.getInt(3));
                    req="SELECT g.gncompteachat,c.ce_num,c.cate_cleunik FROM groupedecisionproduit g,compte c WHERE g.gncompteachat=c.ce_cleunik AND g.lignecleunik="+objcleunik+" AND g.ctpro_cleunik ="+cas+";";
                    tmp2=Transaction.execrequete3(req,connect.getConuser(),tmperreur2);
                    tmp2.first();
                    tmpGrpDec.setIntitulecomptea(tmp2.getInt(2));
                    tmpGrpDec.setCategoriecomptea(tmp2.getInt(3));                    
                    returnValue=(Grpdecision_T) tmpGrpDec;
                }
                tmpresult.close();
            }
            catch(SQLException e){
                Logger.getDefaultLogger().log(Logger.LOG_WARNING,"Erreur dans  ChargeGroupeDefProduit: "+e);
            }
            catch(Exception e1){
                Logger.getDefaultLogger().log(Logger.LOG_WARNING,"erreur dans ChargeGroupeDefProduit :"+e1);
            }
        }
        else{
            Grpdecision_T GrpDecVide=new Grpdecision_T();
            GrpDecVide.setErreurcode(tmperreur.getErreurcode());
            GrpDecVide.setErreurmessage(tmperreur.getErreurmessage());
            returnValue=(Grpdecision_T) GrpDecVide;
        }
        return returnValue;
    }    
    private java.util.ArrayList renvIntituleGrpdecision(int urlmcleunik,Connection usercon,int cas,int caecleunik) {
        String tmplangue;
        String sqlrequete=null;
        ArrayList tmpList;
        if(cas==1)
            sqlrequete="select t.tecleunik,t.tetraduction from traductionintitule t where  t.lmcleunik="+ urlmcleunik+" and t.caecleunik="+caecleunik+" order by t.tetraduction;";
        else if(cas==2)
            sqlrequete="select t.tecleunik,t.tetraduction from traductionintitule t where  t.lmcleunik="+ urlmcleunik+" and t.caecleunik="+caecleunik+" order by t.tetraduction;";
        tmpList=Transaction.generecombostest(sqlrequete,usercon);
        return tmpList;
    }
    
    public Object ChargeObjectPopup(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for FournisseurGroupeDecision");
    }
    
    public Object chargeObjetCombo(int objectCleunik, int urcleunik, int urlmcleunik, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for FournisseurGroupeDecision");
    }
    
    public Gestionerreur_T insertObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for FournisseurGroupeDecision");
    }
    
    public Gestionerreur_T modifyObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        String sqlrequete;
        Gestionerreur_T tmpret=null;
        Grpdecision_T grpd=(Grpdecision_T)objdp;
        try{
            sqlrequete="update groupedecisionproduit set ttcleunik="+grpd.getTtcleunik()+",itcleunik="+grpd.getItcleunik()+",aecleunik="+grpd.getAecleunik()+",gndatetimemodif=NOW(),gncodetvavente="+grpd.getGncodetvavente()+","+
            "gntvainclusvente="+grpd.getGntvainclusvente()+",gncodetvaachat="+grpd.getGncodetvaachat()+",gntvainclusachat="+grpd.getGntvainclusachat()+",gnnbravanteche="+grpd.getGnnbravanteche()+",gnacompteminpp="+grpd.getGnacompteminpp()+",gnprodstockiata="+grpd.getGnprodstockiata()+",gnprodstockautre="+grpd.getGnprodstockautre()+",gnpccomvente="+grpd.getGnpccomvente()+",gncominclpvent="+grpd.getGncominclpvent()+",gnpcacompte="+grpd.getGnpcacompte()+",gnpccomachat="+grpd.getGnpccomachat()+",gnpcclerepvend="+grpd.getGnpcclerepvend()+",gnpcclerepconcept="+grpd.getGnpcclerepconcept()+",gnpcclerepmmere="+grpd.getGnpcclerepmmere()+",snumerosessionmodif='"+connect.getUrnumerosession()+"',gncomptevente='"+grpd.getGncomptevente()+"',gncompteachat='"+grpd.getGncompteachat()+"',gnfracomptepc="+grpd.getGnfracomptepc()+",gnfrnbda="+grpd.getGnfrnbda()+",gnfrnbds="+grpd.getGnfrnbds()+",gnfranchise="+grpd.getGnfranchise()+" where gncleunik="+grpd.getGncleunik()+";";
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"Connexion du user"+connect.getConuser());         
            tmpret=Transaction.execrequeteinsert(sqlrequete,connect.getConuser());           
        }
        catch(Exception i) {
            Logger.getDefaultLogger().log(Logger.LOG_WARNING,"Exception :"+i);
        }
        return tmpret;
    }
    
    public java.util.ArrayList renvIntitule(int urlmcleunik, int urcleunik, int cas, int caecleunik, Poolconnection connect) throws RemoteException {
        return renvIntituleGrpdecision(urlmcleunik,connect.getConuser(),cas,caecleunik);
    }
    
    public CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for FournisseurGroupeDecision");
    }
    
    public ArrayList renvcombo(int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for FournisseurGroupeDecision");
    }
    
    public void deleteSignaletique(long objectCleunik, int typeObjec, Poolconnection connect) throws ServeurSqlFailure {
    }
    
}
