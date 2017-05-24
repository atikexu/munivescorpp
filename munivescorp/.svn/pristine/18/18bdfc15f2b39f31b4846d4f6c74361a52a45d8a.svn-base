/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.nasqa.values.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Eccopacondori
 */
@Entity
@Table(name = "distribucion", catalog = "nasqa_values", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Distribucion.findAll", query = "SELECT d FROM Distribucion d"),
    @NamedQuery(name = "Distribucion.findById", query = "SELECT d FROM Distribucion d WHERE d.id = :id"),
 //   @NamedQuery(name = "Distribucion.findByIdImport", query = "SELECT d FROM Distribucion d WHERE d.idImport = :idImport"),
    @NamedQuery(name = "Distribucion.findByCodBar", query = "SELECT d FROM Distribucion d WHERE d.codBar = :codBar"),
    @NamedQuery(name = "Distribucion.findByCodCli", query = "SELECT d FROM Distribucion d WHERE d.codCli = :codCli"),
    @NamedQuery(name = "Distribucion.findByNomCli", query = "SELECT d FROM Distribucion d WHERE d.nomCli = :nomCli"),
    @NamedQuery(name = "Distribucion.findByCodProVal", query = "SELECT d FROM Distribucion d WHERE d.codProVal = :codProVal"),
    @NamedQuery(name = "Distribucion.findByDesProVal", query = "SELECT d FROM Distribucion d WHERE d.desProVal = :desProVal"),
    @NamedQuery(name = "Distribucion.findByOrdPro", query = "SELECT d FROM Distribucion d WHERE d.ordPro = :ordPro"),
    @NamedQuery(name = "Distribucion.findByFecPro", query = "SELECT d FROM Distribucion d WHERE d.fecPro = :fecPro"),
    @NamedQuery(name = "Distribucion.findByValTip", query = "SELECT d FROM Distribucion d WHERE d.valTip = :valTip"),
    @NamedQuery(name = "Distribucion.findByValTipEmi", query = "SELECT d FROM Distribucion d WHERE d.valTipEmi = :valTipEmi"),
    @NamedQuery(name = "Distribucion.findByValDesEmi", query = "SELECT d FROM Distribucion d WHERE d.valDesEmi = :valDesEmi"),
    @NamedQuery(name = "Distribucion.findByValFecEmi", query = "SELECT d FROM Distribucion d WHERE d.valFecEmi = :valFecEmi"),
    @NamedQuery(name = "Distribucion.findByValNroIde", query = "SELECT d FROM Distribucion d WHERE d.valNroIde = :valNroIde"),
    @NamedQuery(name = "Distribucion.findByValOtrId1", query = "SELECT d FROM Distribucion d WHERE d.valOtrId1 = :valOtrId1"),
    @NamedQuery(name = "Distribucion.findByValOtrId2", query = "SELECT d FROM Distribucion d WHERE d.valOtrId2 = :valOtrId2"),
    @NamedQuery(name = "Distribucion.findByValOtrId3", query = "SELECT d FROM Distribucion d WHERE d.valOtrId3 = :valOtrId3"),
    @NamedQuery(name = "Distribucion.findByTitNomApe", query = "SELECT d FROM Distribucion d WHERE d.titNomApe = :titNomApe"),
    @NamedQuery(name = "Distribucion.findByTitTipDoc", query = "SELECT d FROM Distribucion d WHERE d.titTipDoc = :titTipDoc"),
    @NamedQuery(name = "Distribucion.findByTitNumDoc", query = "SELECT d FROM Distribucion d WHERE d.titNumDoc = :titNumDoc"),
    @NamedQuery(name = "Distribucion.findByDesNomApe", query = "SELECT d FROM Distribucion d WHERE d.desNomApe = :desNomApe"),
    @NamedQuery(name = "Distribucion.findByDirDomDes", query = "SELECT d FROM Distribucion d WHERE d.dirDomDes = :dirDomDes"),
    @NamedQuery(name = "Distribucion.findByDirDomRef", query = "SELECT d FROM Distribucion d WHERE d.dirDomRef = :dirDomRef"),
    @NamedQuery(name = "Distribucion.findByDirDomPos", query = "SELECT d FROM Distribucion d WHERE d.dirDomPos = :dirDomPos"),
    @NamedQuery(name = "Distribucion.findByDirDomDis", query = "SELECT d FROM Distribucion d WHERE d.dirDomDis = :dirDomDis"),
    @NamedQuery(name = "Distribucion.findByDirDomPro", query = "SELECT d FROM Distribucion d WHERE d.dirDomPro = :dirDomPro"),
    @NamedQuery(name = "Distribucion.findByDirDomDep", query = "SELECT d FROM Distribucion d WHERE d.dirDomDep = :dirDomDep"),
    @NamedQuery(name = "Distribucion.findByDirDomUbi", query = "SELECT d FROM Distribucion d WHERE d.dirDomUbi = :dirDomUbi"),
    @NamedQuery(name = "Distribucion.findByDirLabDes", query = "SELECT d FROM Distribucion d WHERE d.dirLabDes = :dirLabDes"),
    @NamedQuery(name = "Distribucion.findByDirLabRef", query = "SELECT d FROM Distribucion d WHERE d.dirLabRef = :dirLabRef"),
    @NamedQuery(name = "Distribucion.findByDirLabPos", query = "SELECT d FROM Distribucion d WHERE d.dirLabPos = :dirLabPos"),
    @NamedQuery(name = "Distribucion.findByDirLabDis", query = "SELECT d FROM Distribucion d WHERE d.dirLabDis = :dirLabDis"),
    @NamedQuery(name = "Distribucion.findByDirLabPro", query = "SELECT d FROM Distribucion d WHERE d.dirLabPro = :dirLabPro"),
    @NamedQuery(name = "Distribucion.findByDirLabDep", query = "SELECT d FROM Distribucion d WHERE d.dirLabDep = :dirLabDep"),
    @NamedQuery(name = "Distribucion.findByDirLabUbi", query = "SELECT d FROM Distribucion d WHERE d.dirLabUbi = :dirLabUbi"),
    @NamedQuery(name = "Distribucion.findByDirLabEmp", query = "SELECT d FROM Distribucion d WHERE d.dirLabEmp = :dirLabEmp"),
    @NamedQuery(name = "Distribucion.findByDirOpcDes", query = "SELECT d FROM Distribucion d WHERE d.dirOpcDes = :dirOpcDes"),
    @NamedQuery(name = "Distribucion.findByDirOpcRef", query = "SELECT d FROM Distribucion d WHERE d.dirOpcRef = :dirOpcRef"),
    @NamedQuery(name = "Distribucion.findByDirOpcPos", query = "SELECT d FROM Distribucion d WHERE d.dirOpcPos = :dirOpcPos"),
    @NamedQuery(name = "Distribucion.findByDirOpcDis", query = "SELECT d FROM Distribucion d WHERE d.dirOpcDis = :dirOpcDis"),
    @NamedQuery(name = "Distribucion.findByDirOpcPro", query = "SELECT d FROM Distribucion d WHERE d.dirOpcPro = :dirOpcPro"),
    @NamedQuery(name = "Distribucion.findByDirOpcDep", query = "SELECT d FROM Distribucion d WHERE d.dirOpcDep = :dirOpcDep"),
    @NamedQuery(name = "Distribucion.findByDirOpcUbi", query = "SELECT d FROM Distribucion d WHERE d.dirOpcUbi = :dirOpcUbi"),
    @NamedQuery(name = "Distribucion.findByTitTlfDom", query = "SELECT d FROM Distribucion d WHERE d.titTlfDom = :titTlfDom"),
    @NamedQuery(name = "Distribucion.findByTitTlfLab", query = "SELECT d FROM Distribucion d WHERE d.titTlfLab = :titTlfLab"),
    @NamedQuery(name = "Distribucion.findByTitTlfOpc", query = "SELECT d FROM Distribucion d WHERE d.titTlfOpc = :titTlfOpc"),
    @NamedQuery(name = "Distribucion.findByTitTlfMov", query = "SELECT d FROM Distribucion d WHERE d.titTlfMov = :titTlfMov"),
    @NamedQuery(name = "Distribucion.findByMd1TipDoc", query = "SELECT d FROM Distribucion d WHERE d.md1TipDoc = :md1TipDoc"),
    @NamedQuery(name = "Distribucion.findByMd1NumDoc", query = "SELECT d FROM Distribucion d WHERE d.md1NumDoc = :md1NumDoc"),
    @NamedQuery(name = "Distribucion.findByMd1NomApe", query = "SELECT d FROM Distribucion d WHERE d.md1NomApe = :md1NomApe"),
    @NamedQuery(name = "Distribucion.findByMd1NumTlf", query = "SELECT d FROM Distribucion d WHERE d.md1NumTlf = :md1NumTlf"),
    @NamedQuery(name = "Distribucion.findByMd2TipDoc", query = "SELECT d FROM Distribucion d WHERE d.md2TipDoc = :md2TipDoc"),
    @NamedQuery(name = "Distribucion.findByMd2NumDoc", query = "SELECT d FROM Distribucion d WHERE d.md2NumDoc = :md2NumDoc"),
    @NamedQuery(name = "Distribucion.findByMd2NomApe", query = "SELECT d FROM Distribucion d WHERE d.md2NomApe = :md2NomApe"),
    @NamedQuery(name = "Distribucion.findByMd2NumTlf", query = "SELECT d FROM Distribucion d WHERE d.md2NumTlf = :md2NumTlf"),
    @NamedQuery(name = "Distribucion.findByIndSit", query = "SELECT d FROM Distribucion d WHERE d.indSit = :indSit"),
    @NamedQuery(name = "Distribucion.findByDesSit", query = "SELECT d FROM Distribucion d WHERE d.desSit = :desSit"),
    @NamedQuery(name = "Distribucion.findByIndEst", query = "SELECT d FROM Distribucion d WHERE d.indEst = :indEst"),
    @NamedQuery(name = "Distribucion.findByDesEst", query = "SELECT d FROM Distribucion d WHERE d.desEst = :desEst"),
    @NamedQuery(name = "Distribucion.findByCodMot", query = "SELECT d FROM Distribucion d WHERE d.codMot = :codMot"),
    @NamedQuery(name = "Distribucion.findByDesMot", query = "SELECT d FROM Distribucion d WHERE d.desMot = :desMot"),
    @NamedQuery(name = "Distribucion.findByFecMot", query = "SELECT d FROM Distribucion d WHERE d.fecMot = :fecMot"),
    @NamedQuery(name = "Distribucion.findByNroHoj", query = "SELECT d FROM Distribucion d WHERE d.nroHoj = :nroHoj"),
    @NamedQuery(name = "Distribucion.findByFecHoj", query = "SELECT d FROM Distribucion d WHERE d.fecHoj = :fecHoj"),
    @NamedQuery(name = "Distribucion.findByNroRen", query = "SELECT d FROM Distribucion d WHERE d.nroRen = :nroRen"),
    @NamedQuery(name = "Distribucion.findByFecRen", query = "SELECT d FROM Distribucion d WHERE d.fecRen = :fecRen"),
    @NamedQuery(name = "Distribucion.findByFecEnt", query = "SELECT d FROM Distribucion d WHERE d.fecEnt = :fecEnt"),
    @NamedQuery(name = "Distribucion.findByPerRec", query = "SELECT d FROM Distribucion d WHERE d.perRec = :perRec"),
    @NamedQuery(name = "Distribucion.findByIndVin", query = "SELECT d FROM Distribucion d WHERE d.indVin = :indVin"),
    @NamedQuery(name = "Distribucion.findByDesVin", query = "SELECT d FROM Distribucion d WHERE d.desVin = :desVin"),
    @NamedQuery(name = "Distribucion.findByResNroVis", query = "SELECT d FROM Distribucion d WHERE d.resNroVis = :resNroVis"),
    @NamedQuery(name = "Distribucion.findByResPriVis", query = "SELECT d FROM Distribucion d WHERE d.resPriVis = :resPriVis"),
    @NamedQuery(name = "Distribucion.findByResSegVis", query = "SELECT d FROM Distribucion d WHERE d.resSegVis = :resSegVis"),
    @NamedQuery(name = "Distribucion.findByResTerVis", query = "SELECT d FROM Distribucion d WHERE d.resTerVis = :resTerVis"),
    @NamedQuery(name = "Distribucion.findByResNroCoo", query = "SELECT d FROM Distribucion d WHERE d.resNroCoo = :resNroCoo"),
    @NamedQuery(name = "Distribucion.findByResPriCoo", query = "SELECT d FROM Distribucion d WHERE d.resPriCoo = :resPriCoo"),
    @NamedQuery(name = "Distribucion.findByResSegCoo", query = "SELECT d FROM Distribucion d WHERE d.resSegCoo = :resSegCoo"),
    @NamedQuery(name = "Distribucion.findByResTerCoo", query = "SELECT d FROM Distribucion d WHERE d.resTerCoo = :resTerCoo"),
    @NamedQuery(name = "Distribucion.findByResNroLla", query = "SELECT d FROM Distribucion d WHERE d.resNroLla = :resNroLla"),
    @NamedQuery(name = "Distribucion.findByImgCarEst", query = "SELECT d FROM Distribucion d WHERE d.imgCarEst = :imgCarEst"),
    @NamedQuery(name = "Distribucion.findByImgCarNom", query = "SELECT d FROM Distribucion d WHERE d.imgCarNom = :imgCarNom"),
    @NamedQuery(name = "Distribucion.findByImgCarFec", query = "SELECT d FROM Distribucion d WHERE d.imgCarFec = :imgCarFec"),
    @NamedQuery(name = "Distribucion.findByImgDocEst", query = "SELECT d FROM Distribucion d WHERE d.imgDocEst = :imgDocEst"),
    @NamedQuery(name = "Distribucion.findByImgDocNom", query = "SELECT d FROM Distribucion d WHERE d.imgDocNom = :imgDocNom"),
    @NamedQuery(name = "Distribucion.findByImgDocFec", query = "SELECT d FROM Distribucion d WHERE d.imgDocFec = :imgDocFec"),
    @NamedQuery(name = "Distribucion.findByTraCpr", query = "SELECT d FROM Distribucion d WHERE d.traCpr = :traCpr"),
    @NamedQuery(name = "Distribucion.findByTraCsp", query = "SELECT d FROM Distribucion d WHERE d.traCsp = :traCsp"),
    @NamedQuery(name = "Distribucion.findByTraCcl", query = "SELECT d FROM Distribucion d WHERE d.traCcl = :traCcl"),
    @NamedQuery(name = "Distribucion.findByTraRet", query = "SELECT d FROM Distribucion d WHERE d.traRet = :traRet"),
    @NamedQuery(name = "Distribucion.findByTraEmp", query = "SELECT d FROM Distribucion d WHERE d.traEmp = :traEmp"),
    @NamedQuery(name = "Distribucion.findByTraNod", query = "SELECT d FROM Distribucion d WHERE d.traNod = :traNod"),
    @NamedQuery(name = "Distribucion.findByTraNhj", query = "SELECT d FROM Distribucion d WHERE d.traNhj = :traNhj"),
    @NamedQuery(name = "Distribucion.findByTraCxy", query = "SELECT d FROM Distribucion d WHERE d.traCxy = :traCxy"),
    @NamedQuery(name = "Distribucion.findByTraCua", query = "SELECT d FROM Distribucion d WHERE d.traCua = :traCua"),
    @NamedQuery(name = "Distribucion.findByTraUbi", query = "SELECT d FROM Distribucion d WHERE d.traUbi = :traUbi"),
    @NamedQuery(name = "Distribucion.findByTraDie", query = "SELECT d FROM Distribucion d WHERE d.traDie = :traDie"),
    @NamedQuery(name = "Distribucion.findByTraRe1", query = "SELECT d FROM Distribucion d WHERE d.traRe1 = :traRe1"),
    @NamedQuery(name = "Distribucion.findByTraRe2", query = "SELECT d FROM Distribucion d WHERE d.traRe2 = :traRe2"),
    @NamedQuery(name = "Distribucion.findByTraRe3", query = "SELECT d FROM Distribucion d WHERE d.traRe3 = :traRe3"),
    @NamedQuery(name = "Distribucion.findByTraRe4", query = "SELECT d FROM Distribucion d WHERE d.traRe4 = :traRe4"),
    @NamedQuery(name = "Distribucion.findByExtUsuCre", query = "SELECT d FROM Distribucion d WHERE d.extUsuCre = :extUsuCre"),
    @NamedQuery(name = "Distribucion.findByExtFecCre", query = "SELECT d FROM Distribucion d WHERE d.extFecCre = :extFecCre"),
    @NamedQuery(name = "Distribucion.findByExtFecMod", query = "SELECT d FROM Distribucion d WHERE d.extFecMod = :extFecMod"),
    @NamedQuery(name = "Distribucion.findByExtFlgStt", query = "SELECT d FROM Distribucion d WHERE d.extFlgStt = :extFlgStt")})
public class Distribucion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;   
    @Column(name = "id_import", nullable = true)
    private int idImport;
    @Basic(optional = false)
    @Column(name = "cod_bar", nullable = false, length = 30)
    private String codBar;
    @Basic(optional = false)
    @Column(name = "cod_cli", nullable = false, length = 30)
    private String codCli;
    @Basic(optional = false)
    @Column(name = "nom_cli", nullable = false, length = 60)
    private String nomCli;
    @Basic(optional = false)
    @Column(name = "cod_pro_val", nullable = false, length = 6)
    private String codProVal;
    @Basic(optional = false)
    @Column(name = "des_pro_val", nullable = false, length = 60)
    private String desProVal;
    @Basic(optional = false)
    @Column(name = "ord_pro", nullable = false)
    private int ordPro;
    @Basic(optional = false)
    @Column(name = "fec_pro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecPro;
    @Column(name = "val_tip", length = 10)
    private String valTip;
    @Column(name = "val_tip_emi", length = 10)
    private String valTipEmi;
    @Column(name = "val_des_emi", length = 50)
    private String valDesEmi;
    @Column(name = "val_fec_emi")
    @Temporal(TemporalType.DATE)
    private Date valFecEmi;
    @Column(name = "val_nro_ide", length = 30)
    private String valNroIde;
    @Column(name = "val_otr_id1", length = 250)
    private String valOtrId1;
    @Column(name = "val_otr_id2", length = 250)
    private String valOtrId2;
    @Column(name = "val_otr_id3", length = 250)
    private String valOtrId3;
    @Column(name = "tit_nom_ape", length = 100)
    private String titNomApe;
    @Column(name = "tit_tip_doc", length = 20)
    private String titTipDoc;
    @Column(name = "tit_num_doc", length = 20)
    private String titNumDoc;
    @Column(name = "des_nom_ape", length = 100)
    private String desNomApe;
    @Column(name = "dir_dom_des", length = 250)
    private String dirDomDes;
    @Column(name = "dir_dom_ref", length = 250)
    private String dirDomRef;
    @Column(name = "dir_dom_pos", length = 20)
    private String dirDomPos;
    @Column(name = "dir_dom_dis", length = 50)
    private String dirDomDis;
    @Column(name = "dir_dom_pro", length = 50)
    private String dirDomPro;
    @Column(name = "dir_dom_dep", length = 50)
    private String dirDomDep;
    @Column(name = "dir_dom_ubi", length = 6)
    private String dirDomUbi;
    @Column(name = "dir_lab_des", length = 250)
    private String dirLabDes;
    @Column(name = "dir_lab_ref", length = 250)
    private String dirLabRef;
    @Column(name = "dir_lab_pos", length = 20)
    private String dirLabPos;
    @Column(name = "dir_lab_dis", length = 50)
    private String dirLabDis;
    @Column(name = "dir_lab_pro", length = 50)
    private String dirLabPro;
    @Column(name = "dir_lab_dep", length = 50)
    private String dirLabDep;
    @Column(name = "dir_lab_ubi", length = 6)
    private String dirLabUbi;
    @Column(name = "dir_lab_emp", length = 250)
    private String dirLabEmp;
    @Column(name = "dir_opc_des", length = 250)
    private String dirOpcDes;
    @Column(name = "dir_opc_ref", length = 250)
    private String dirOpcRef;
    @Column(name = "dir_opc_pos", length = 20)
    private String dirOpcPos;
    @Column(name = "dir_opc_dis", length = 50)
    private String dirOpcDis;
    @Column(name = "dir_opc_pro", length = 50)
    private String dirOpcPro;
    @Column(name = "dir_opc_dep", length = 50)
    private String dirOpcDep;
    @Column(name = "dir_opc_ubi", length = 6)
    private String dirOpcUbi;
    @Column(name = "tit_tlf_dom", length = 20)
    private String titTlfDom;
    @Column(name = "tit_tlf_lab", length = 20)
    private String titTlfLab;
    @Column(name = "tit_tlf_opc", length = 20)
    private String titTlfOpc;
    @Column(name = "tit_tlf_mov", length = 20)
    private String titTlfMov;
    @Column(name = "md1_tip_doc", length = 20)
    private String md1TipDoc;
    @Column(name = "md1_num_doc", length = 20)
    private String md1NumDoc;
    @Column(name = "md1_nom_ape", length = 100)
    private String md1NomApe;
    @Column(name = "md1_num_tlf", length = 20)
    private String md1NumTlf;
    @Column(name = "md2_tip_doc", length = 20)
    private String md2TipDoc;
    @Column(name = "md2_num_doc", length = 20)
    private String md2NumDoc;
    @Column(name = "md2_nom_ape", length = 100)
    private String md2NomApe;
    @Column(name = "md2_num_tlf", length = 20)
    private String md2NumTlf;
    @Basic(optional = false)
    @Column(name = "ind_sit", nullable = false, length = 2)
    private String indSit;
    @Basic(optional = false)
    @Column(name = "des_sit", nullable = false, length = 50)
    private String desSit;
    @Basic(optional = false)
    @Column(name = "ind_est", nullable = false, length = 2)
    private String indEst;
    @Basic(optional = false)
    @Column(name = "des_est", nullable = false, length = 50)
    private String desEst;
    @Column(name = "cod_mot", length = 2)
    private String codMot;
    @Column(name = "des_mot", length = 50)
    private String desMot;
    @Column(name = "fec_mot")
    @Temporal(TemporalType.DATE)
    private Date fecMot;
    @Column(name = "nro_hoj")
    private Integer nroHoj;
    @Column(name = "fec_hoj")
    @Temporal(TemporalType.DATE)
    private Date fecHoj;
    @Column(name = "nro_ren")
    private Integer nroRen;
    @Column(name = "fec_ren")
    @Temporal(TemporalType.DATE)
    private Date fecRen;
    @Column(name = "fec_ent")
    @Temporal(TemporalType.DATE)
    private Date fecEnt;
    @Column(name = "per_rec", length = 60)
    private String perRec;
    @Column(name = "ind_vin", length = 2)
    private String indVin;
    @Column(name = "des_vin", length = 50)
    private String desVin;
    @Column(name = "res_nro_vis")
    private Integer resNroVis;
    @Column(name = "res_pri_vis")
    @Temporal(TemporalType.DATE)
    private Date resPriVis;
    @Column(name = "res_seg_vis")
    @Temporal(TemporalType.DATE)
    private Date resSegVis;
    @Column(name = "res_ter_vis")
    @Temporal(TemporalType.DATE)
    private Date resTerVis;
    @Column(name = "res_nro_coo")
    private Integer resNroCoo;
    @Column(name = "res_pri_coo")
    @Temporal(TemporalType.DATE)
    private Date resPriCoo;
    @Column(name = "res_seg_coo")
    @Temporal(TemporalType.DATE)
    private Date resSegCoo;
    @Column(name = "res_ter_coo")
    @Temporal(TemporalType.DATE)
    private Date resTerCoo;
    @Column(name = "res_nro_lla")
    private Integer resNroLla;
    @Column(name = "img_car_est")
    private Integer imgCarEst;
    @Column(name = "img_car_nom", length = 250)
    private String imgCarNom;
    @Column(name = "img_car_fec")
    @Temporal(TemporalType.DATE)
    private Date imgCarFec;
    @Column(name = "img_doc_est")
    private Integer imgDocEst;
    @Column(name = "img_doc_nom", length = 250)
    private String imgDocNom;
    @Column(name = "img_doc_fec")
    @Temporal(TemporalType.DATE)
    private Date imgDocFec;
    @Column(name = "tra_cpr", length = 254)
    private String traCpr;
    @Column(name = "tra_csp", length = 254)
    private String traCsp;
    @Column(name = "tra_ccl", length = 254)
    private String traCcl;
    @Column(name = "tra_ret", length = 254)
    private String traRet;
    @Column(name = "tra_emp", length = 254)
    private String traEmp;
    @Column(name = "tra_nod", length = 254)
    private String traNod;
    @Column(name = "tra_nhj", length = 254)
    private String traNhj;
    @Column(name = "tra_cxy", length = 254)
    private String traCxy;
    @Column(name = "tra_cua", length = 254)
    private String traCua;
    @Column(name = "tra_ubi", length = 254)
    private String traUbi;
    @Column(name = "tra_die", length = 254)
    private String traDie;
    @Column(name = "tra_re1", length = 254)
    private String traRe1;
    @Column(name = "tra_re2", length = 254)
    private String traRe2;
    @Column(name = "tra_re3", length = 254)
    private String traRe3;
    @Column(name = "tra_re4", length = 254)
    private String traRe4;
    @Basic(optional = false)
    @Column(name = "ext_usu_cre", nullable = false)
    private int extUsuCre;
    @Basic(optional = false)
    @Column(name = "ext_fec_cre", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date extFecCre;
    @Basic(optional = false)
    @Column(name = "ext_fec_mod", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date extFecMod;
    @Basic(optional = false)
    @Column(name = "ext_flg_stt", nullable = false, length = 2)
    private String extFlgStt;
    @Basic(optional = false)
    @Column(name = "fec_ultima", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecUltima;
    @Basic(optional = false)
    @Column(name = "fec_ultima_visita", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecUltimaVisita;
    @Column(name = "res_ultima_visita", length = 50)
    private String resUltimaVisita;
    @Column(name = "lugar_ultimo", length = 50)
    private String lugarUltimo;
    @Column(name = "tipo_entrega", length = 50)
    private String tipoEntrega;
    
    
    public Distribucion() {
    }

    public Distribucion(Integer id) {
        this.id = id;
    }

    public Distribucion(Integer id, int idImport, String codBar, String codCli, String nomCli, String codProVal, String desProVal, int ordPro, Date fecPro, String indSit, String desSit, String indEst, String desEst, int extUsuCre, Date extFecCre, Date extFecMod, String extFlgStt,Date fecUltima,Date fecUltimaVisita,String resUltimaVisita,String lugarUltimo,String tipoEntrega) {
        this.id = id;
        this.idImport = idImport;
        this.codBar = codBar;
        this.codCli = codCli;
        this.nomCli = nomCli;
        this.codProVal = codProVal;
        this.desProVal = desProVal;
        this.ordPro = ordPro;
        this.fecPro = fecPro;
        this.indSit = indSit;
        this.desSit = desSit;
        this.indEst = indEst;
        this.desEst = desEst;
        this.extUsuCre = extUsuCre;
        this.extFecCre = extFecCre;
        this.extFecMod = extFecMod;
        this.extFlgStt = extFlgStt;
        this.fecUltima = fecUltima;
        this.fecUltimaVisita = fecUltimaVisita;
        this.resUltimaVisita = resUltimaVisita;
        this.lugarUltimo = lugarUltimo;
        this.tipoEntrega = tipoEntrega;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdImport() {
        return idImport;
    }

    public void setIdImport(int idImport) {
        this.idImport = idImport;
    }

    public String getCodBar() {
        return codBar;
    }

    public void setCodBar(String codBar) {
        this.codBar = codBar;
    }

    public String getCodCli() {
        return codCli;
    }

    public void setCodCli(String codCli) {
        this.codCli = codCli;
    }

    public String getNomCli() {
        return nomCli;
    }

    public void setNomCli(String nomCli) {
        this.nomCli = nomCli;
    }

    public String getCodProVal() {
        return codProVal;
    }

    public void setCodProVal(String codProVal) {
        this.codProVal = codProVal;
    }

    public String getDesProVal() {
        return desProVal;
    }

    public void setDesProVal(String desProVal) {
        this.desProVal = desProVal;
    }

    public int getOrdPro() {
        return ordPro;
    }

    public void setOrdPro(int ordPro) {
        this.ordPro = ordPro;
    }

    public Date getFecPro() {
        return fecPro;
    }

    public void setFecPro(Date fecPro) {
        this.fecPro = fecPro;
    }

    public String getValTip() {
        return valTip;
    }

    public void setValTip(String valTip) {
        this.valTip = valTip;
    }

    public String getValTipEmi() {
        return valTipEmi;
    }

    public void setValTipEmi(String valTipEmi) {
        this.valTipEmi = valTipEmi;
    }

    public String getValDesEmi() {
        return valDesEmi;
    }

    public void setValDesEmi(String valDesEmi) {
        this.valDesEmi = valDesEmi;
    }

    public Date getValFecEmi() {
        return valFecEmi;
    }

    public void setValFecEmi(Date valFecEmi) {
        this.valFecEmi = valFecEmi;
    }

    public String getValNroIde() {
        return valNroIde;
    }

    public void setValNroIde(String valNroIde) {
        this.valNroIde = valNroIde;
    }

    public String getValOtrId1() {
        return valOtrId1;
    }

    public void setValOtrId1(String valOtrId1) {
        this.valOtrId1 = valOtrId1;
    }

    public String getValOtrId2() {
        return valOtrId2;
    }

    public void setValOtrId2(String valOtrId2) {
        this.valOtrId2 = valOtrId2;
    }

    public String getValOtrId3() {
        return valOtrId3;
    }

    public void setValOtrId3(String valOtrId3) {
        this.valOtrId3 = valOtrId3;
    }

    public String getTitNomApe() {
        return titNomApe;
    }

    public void setTitNomApe(String titNomApe) {
        this.titNomApe = titNomApe;
    }

    public String getTitTipDoc() {
        return titTipDoc;
    }

    public void setTitTipDoc(String titTipDoc) {
        this.titTipDoc = titTipDoc;
    }

    public String getTitNumDoc() {
        return titNumDoc;
    }

    public void setTitNumDoc(String titNumDoc) {
        this.titNumDoc = titNumDoc;
    }

    public String getDesNomApe() {
        return desNomApe;
    }

    public void setDesNomApe(String desNomApe) {
        this.desNomApe = desNomApe;
    }

    public String getDirDomDes() {
        return dirDomDes;
    }

    public void setDirDomDes(String dirDomDes) {
        this.dirDomDes = dirDomDes;
    }

    public String getDirDomRef() {
        return dirDomRef;
    }

    public void setDirDomRef(String dirDomRef) {
        this.dirDomRef = dirDomRef;
    }

    public String getDirDomPos() {
        return dirDomPos;
    }

    public void setDirDomPos(String dirDomPos) {
        this.dirDomPos = dirDomPos;
    }

    public String getDirDomDis() {
        return dirDomDis;
    }

    public void setDirDomDis(String dirDomDis) {
        this.dirDomDis = dirDomDis;
    }

    public String getDirDomPro() {
        return dirDomPro;
    }

    public void setDirDomPro(String dirDomPro) {
        this.dirDomPro = dirDomPro;
    }

    public String getDirDomDep() {
        return dirDomDep;
    }

    public void setDirDomDep(String dirDomDep) {
        this.dirDomDep = dirDomDep;
    }

    public String getDirDomUbi() {
        return dirDomUbi;
    }

    public void setDirDomUbi(String dirDomUbi) {
        this.dirDomUbi = dirDomUbi;
    }

    public String getDirLabDes() {
        return dirLabDes;
    }

    public void setDirLabDes(String dirLabDes) {
        this.dirLabDes = dirLabDes;
    }

    public String getDirLabRef() {
        return dirLabRef;
    }

    public void setDirLabRef(String dirLabRef) {
        this.dirLabRef = dirLabRef;
    }

    public String getDirLabPos() {
        return dirLabPos;
    }

    public void setDirLabPos(String dirLabPos) {
        this.dirLabPos = dirLabPos;
    }

    public String getDirLabDis() {
        return dirLabDis;
    }

    public void setDirLabDis(String dirLabDis) {
        this.dirLabDis = dirLabDis;
    }

    public String getDirLabPro() {
        return dirLabPro;
    }

    public void setDirLabPro(String dirLabPro) {
        this.dirLabPro = dirLabPro;
    }

    public String getDirLabDep() {
        return dirLabDep;
    }

    public void setDirLabDep(String dirLabDep) {
        this.dirLabDep = dirLabDep;
    }

    public String getDirLabUbi() {
        return dirLabUbi;
    }

    public void setDirLabUbi(String dirLabUbi) {
        this.dirLabUbi = dirLabUbi;
    }

    public String getDirLabEmp() {
        return dirLabEmp;
    }

    public void setDirLabEmp(String dirLabEmp) {
        this.dirLabEmp = dirLabEmp;
    }

    public String getDirOpcDes() {
        return dirOpcDes;
    }

    public void setDirOpcDes(String dirOpcDes) {
        this.dirOpcDes = dirOpcDes;
    }

    public String getDirOpcRef() {
        return dirOpcRef;
    }

    public void setDirOpcRef(String dirOpcRef) {
        this.dirOpcRef = dirOpcRef;
    }

    public String getDirOpcPos() {
        return dirOpcPos;
    }

    public void setDirOpcPos(String dirOpcPos) {
        this.dirOpcPos = dirOpcPos;
    }

    public String getDirOpcDis() {
        return dirOpcDis;
    }

    public void setDirOpcDis(String dirOpcDis) {
        this.dirOpcDis = dirOpcDis;
    }

    public String getDirOpcPro() {
        return dirOpcPro;
    }

    public void setDirOpcPro(String dirOpcPro) {
        this.dirOpcPro = dirOpcPro;
    }

    public String getDirOpcDep() {
        return dirOpcDep;
    }

    public void setDirOpcDep(String dirOpcDep) {
        this.dirOpcDep = dirOpcDep;
    }

    public String getDirOpcUbi() {
        return dirOpcUbi;
    }

    public void setDirOpcUbi(String dirOpcUbi) {
        this.dirOpcUbi = dirOpcUbi;
    }

    public String getTitTlfDom() {
        return titTlfDom;
    }

    public void setTitTlfDom(String titTlfDom) {
        this.titTlfDom = titTlfDom;
    }

    public String getTitTlfLab() {
        return titTlfLab;
    }

    public void setTitTlfLab(String titTlfLab) {
        this.titTlfLab = titTlfLab;
    }

    public String getTitTlfOpc() {
        return titTlfOpc;
    }

    public void setTitTlfOpc(String titTlfOpc) {
        this.titTlfOpc = titTlfOpc;
    }

    public String getTitTlfMov() {
        return titTlfMov;
    }

    public void setTitTlfMov(String titTlfMov) {
        this.titTlfMov = titTlfMov;
    }

    public String getMd1TipDoc() {
        return md1TipDoc;
    }

    public void setMd1TipDoc(String md1TipDoc) {
        this.md1TipDoc = md1TipDoc;
    }

    public String getMd1NumDoc() {
        return md1NumDoc;
    }

    public void setMd1NumDoc(String md1NumDoc) {
        this.md1NumDoc = md1NumDoc;
    }

    public String getMd1NomApe() {
        return md1NomApe;
    }

    public void setMd1NomApe(String md1NomApe) {
        this.md1NomApe = md1NomApe;
    }

    public String getMd1NumTlf() {
        return md1NumTlf;
    }

    public void setMd1NumTlf(String md1NumTlf) {
        this.md1NumTlf = md1NumTlf;
    }

    public String getMd2TipDoc() {
        return md2TipDoc;
    }

    public void setMd2TipDoc(String md2TipDoc) {
        this.md2TipDoc = md2TipDoc;
    }

    public String getMd2NumDoc() {
        return md2NumDoc;
    }

    public void setMd2NumDoc(String md2NumDoc) {
        this.md2NumDoc = md2NumDoc;
    }

    public String getMd2NomApe() {
        return md2NomApe;
    }

    public void setMd2NomApe(String md2NomApe) {
        this.md2NomApe = md2NomApe;
    }

    public String getMd2NumTlf() {
        return md2NumTlf;
    }

    public void setMd2NumTlf(String md2NumTlf) {
        this.md2NumTlf = md2NumTlf;
    }

    public String getIndSit() {
        return indSit;
    }

    public void setIndSit(String indSit) {
        this.indSit = indSit;
    }

    public String getDesSit() {
        return desSit;
    }

    public void setDesSit(String desSit) {
        this.desSit = desSit;
    }

    public String getIndEst() {
        return indEst;
    }

    public void setIndEst(String indEst) {
        this.indEst = indEst;
    }

    public String getDesEst() {
        return desEst;
    }

    public void setDesEst(String desEst) {
        this.desEst = desEst;
    }

    public String getCodMot() {
        return codMot;
    }

    public void setCodMot(String codMot) {
        this.codMot = codMot;
    }

    public String getDesMot() {
        return desMot;
    }

    public void setDesMot(String desMot) {
        this.desMot = desMot;
    }

    public Date getFecMot() {
        return fecMot;
    }

    public void setFecMot(Date fecMot) {
        this.fecMot = fecMot;
    }

    public Integer getNroHoj() {
        return nroHoj;
    }

    public void setNroHoj(Integer nroHoj) {
        this.nroHoj = nroHoj;
    }

    public Date getFecHoj() {
        return fecHoj;
    }

    public void setFecHoj(Date fecHoj) {
        this.fecHoj = fecHoj;
    }

    public Integer getNroRen() {
        return nroRen;
    }

    public void setNroRen(Integer nroRen) {
        this.nroRen = nroRen;
    }

    public Date getFecRen() {
        return fecRen;
    }

    public void setFecRen(Date fecRen) {
        this.fecRen = fecRen;
    }

    public Date getFecEnt() {
        return fecEnt;
    }

    public void setFecEnt(Date fecEnt) {
        this.fecEnt = fecEnt;
    }

    public String getPerRec() {
        return perRec;
    }

    public void setPerRec(String perRec) {
        this.perRec = perRec;
    }

    public String getIndVin() {
        return indVin;
    }

    public void setIndVin(String indVin) {
        this.indVin = indVin;
    }

    public String getDesVin() {
        return desVin;
    }

    public void setDesVin(String desVin) {
        this.desVin = desVin;
    }

    public Integer getResNroVis() {
        return resNroVis;
    }

    public void setResNroVis(Integer resNroVis) {
        this.resNroVis = resNroVis;
    }

    public Date getResPriVis() {
        return resPriVis;
    }

    public void setResPriVis(Date resPriVis) {
        this.resPriVis = resPriVis;
    }

    public Date getResSegVis() {
        return resSegVis;
    }

    public void setResSegVis(Date resSegVis) {
        this.resSegVis = resSegVis;
    }

    public Date getResTerVis() {
        return resTerVis;
    }

    public void setResTerVis(Date resTerVis) {
        this.resTerVis = resTerVis;
    }

    public Integer getResNroCoo() {
        return resNroCoo;
    }

    public void setResNroCoo(Integer resNroCoo) {
        this.resNroCoo = resNroCoo;
    }

    public Date getResPriCoo() {
        return resPriCoo;
    }

    public void setResPriCoo(Date resPriCoo) {
        this.resPriCoo = resPriCoo;
    }

    public Date getResSegCoo() {
        return resSegCoo;
    }

    public void setResSegCoo(Date resSegCoo) {
        this.resSegCoo = resSegCoo;
    }

    public Date getResTerCoo() {
        return resTerCoo;
    }

    public void setResTerCoo(Date resTerCoo) {
        this.resTerCoo = resTerCoo;
    }

    public Integer getResNroLla() {
        return resNroLla;
    }

    public void setResNroLla(Integer resNroLla) {
        this.resNroLla = resNroLla;
    }

    public Integer getImgCarEst() {
        return imgCarEst;
    }

    public void setImgCarEst(Integer imgCarEst) {
        this.imgCarEst = imgCarEst;
    }

    public String getImgCarNom() {
        return imgCarNom;
    }

    public void setImgCarNom(String imgCarNom) {
        this.imgCarNom = imgCarNom;
    }

    public Date getImgCarFec() {
        return imgCarFec;
    }

    public void setImgCarFec(Date imgCarFec) {
        this.imgCarFec = imgCarFec;
    }

    public Integer getImgDocEst() {
        return imgDocEst;
    }

    public void setImgDocEst(Integer imgDocEst) {
        this.imgDocEst = imgDocEst;
    }

    public String getImgDocNom() {
        return imgDocNom;
    }

    public void setImgDocNom(String imgDocNom) {
        this.imgDocNom = imgDocNom;
    }

    public Date getImgDocFec() {
        return imgDocFec;
    }

    public void setImgDocFec(Date imgDocFec) {
        this.imgDocFec = imgDocFec;
    }

    public String getTraCpr() {
        return traCpr;
    }

    public void setTraCpr(String traCpr) {
        this.traCpr = traCpr;
    }

    public String getTraCsp() {
        return traCsp;
    }

    public void setTraCsp(String traCsp) {
        this.traCsp = traCsp;
    }

    public String getTraCcl() {
        return traCcl;
    }

    public void setTraCcl(String traCcl) {
        this.traCcl = traCcl;
    }

    public String getTraRet() {
        return traRet;
    }

    public void setTraRet(String traRet) {
        this.traRet = traRet;
    }

    public String getTraEmp() {
        return traEmp;
    }

    public void setTraEmp(String traEmp) {
        this.traEmp = traEmp;
    }

    public String getTraNod() {
        return traNod;
    }

    public void setTraNod(String traNod) {
        this.traNod = traNod;
    }

    public String getTraNhj() {
        return traNhj;
    }

    public void setTraNhj(String traNhj) {
        this.traNhj = traNhj;
    }

    public String getTraCxy() {
        return traCxy;
    }

    public void setTraCxy(String traCxy) {
        this.traCxy = traCxy;
    }

    public String getTraCua() {
        return traCua;
    }

    public void setTraCua(String traCua) {
        this.traCua = traCua;
    }

    public String getTraUbi() {
        return traUbi;
    }

    public void setTraUbi(String traUbi) {
        this.traUbi = traUbi;
    }

    public String getTraDie() {
        return traDie;
    }

    public void setTraDie(String traDie) {
        this.traDie = traDie;
    }

    public String getTraRe1() {
        return traRe1;
    }

    public void setTraRe1(String traRe1) {
        this.traRe1 = traRe1;
    }

    public String getTraRe2() {
        return traRe2;
    }

    public void setTraRe2(String traRe2) {
        this.traRe2 = traRe2;
    }

    public String getTraRe3() {
        return traRe3;
    }

    public void setTraRe3(String traRe3) {
        this.traRe3 = traRe3;
    }

    public String getTraRe4() {
        return traRe4;
    }

    public void setTraRe4(String traRe4) {
        this.traRe4 = traRe4;
    }

    public int getExtUsuCre() {
        return extUsuCre;
    }

    public void setExtUsuCre(int extUsuCre) {
        this.extUsuCre = extUsuCre;
    }

    public Date getExtFecCre() {
        return extFecCre;
    }

    public void setExtFecCre(Date extFecCre) {
        this.extFecCre = extFecCre;
    }

    public Date getExtFecMod() {
        return extFecMod;
    }

    public void setExtFecMod(Date extFecMod) {
        this.extFecMod = extFecMod;
    }

    public String getExtFlgStt() {
        return extFlgStt;
    }

    public void setExtFlgStt(String extFlgStt) {
        this.extFlgStt = extFlgStt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Distribucion)) {
            return false;
        }
        Distribucion other = (Distribucion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.nasqa.values.model.entity.Distribucion[ id=" + id + " ]";
    }

	public Date getFecUltima() {
		return fecUltima;
	}

	public void setFecUltima(Date fecUltima) {
		this.fecUltima = fecUltima;
	}

	public Date getFecUltimaVisita() {
		return fecUltimaVisita;
	}

	public void setFecUltimaVisita(Date fecUltimaVisita) {
		this.fecUltimaVisita = fecUltimaVisita;
	}

	public String getResUltimaVisita() {
		return resUltimaVisita;
	}

	public void setResUltimaVisita(String resUltimaVisita) {
		this.resUltimaVisita = resUltimaVisita;
	}

	public String getLugarUltimo() {
		return lugarUltimo;
	}

	public void setLugarUltimo(String lugarUltimo) {
		this.lugarUltimo = lugarUltimo;
	}

	public String getTipoEntrega() {
		return tipoEntrega;
	}

	public void setTipoEntrega(String tipoEntrega) {
		this.tipoEntrega = tipoEntrega;
	}
    
}
