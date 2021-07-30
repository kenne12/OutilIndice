--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.7
-- Dumped by pg_dump version 9.5.7

-- Started on 2021-06-25 12:35:57

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2815 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_with_oids = false;

--
-- TOC entry 181 (class 1259 OID 285894)
-- Name: categorie; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE categorie (
    idcategorie integer NOT NULL,
    nom character varying(254),
    code character varying(254),
    pointmax integer,
    indice integer DEFAULT 0
);


--
-- TOC entry 182 (class 1259 OID 285903)
-- Name: categoriestructure; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE categoriestructure (
    idstructure bigint NOT NULL,
    idcategorie integer NOT NULL,
    pointmax double precision,
    denominateur integer,
    indice integer
);


--
-- TOC entry 215 (class 1259 OID 374898)
-- Name: cible; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE cible (
    idcible bigint NOT NULL,
    valeurcible double precision,
    valeurrealisee double precision,
    ratio double precision,
    primeresultatquant boolean,
    bonusrevenudept boolean,
    idindicateur integer,
    idperiode integer,
    idservice bigint,
    idstructure bigint,
    idsousperiode integer,
    idcritere integer
);


--
-- TOC entry 183 (class 1259 OID 285911)
-- Name: critere; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE critere (
    idcritere integer NOT NULL,
    nom character varying(254),
    code character varying(254),
    poids double precision,
    pointmax integer DEFAULT 0,
    scoremoyen double precision DEFAULT 0,
    resultat double precision DEFAULT 0,
    etat boolean DEFAULT false,
    workflow boolean DEFAULT false,
    valeurinferieur integer DEFAULT 0,
    valeursuperieur integer DEFAULT 0
);


--
-- TOC entry 216 (class 1259 OID 374923)
-- Name: critereresponsabilite; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE critereresponsabilite (
    idcritereresponsabilite bigint NOT NULL,
    point double precision,
    responsabilite boolean,
    idresponsabilite integer,
    idstructure bigint,
    idcritere integer,
    nombre integer DEFAULT 0,
    total double precision DEFAULT 0
);


--
-- TOC entry 242 (class 1259 OID 475666)
-- Name: critereservice; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE critereservice (
    idservice bigint NOT NULL,
    idcritere integer NOT NULL,
    poids double precision,
    pointmax double precision
);


--
-- TOC entry 184 (class 1259 OID 285928)
-- Name: criterestructure; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE criterestructure (
    idstructure bigint NOT NULL,
    idcritere integer NOT NULL,
    poids double precision,
    pointmax integer,
    scoremoyen integer DEFAULT 0,
    resultat double precision DEFAULT 0,
    poidsfinal double precision DEFAULT 0,
    resultatfinal double precision DEFAULT 0,
    ecart double precision DEFAULT 0,
    etat boolean DEFAULT false,
    valeurinferieur integer DEFAULT 0,
    valeursuperieur integer DEFAULT 0
);


--
-- TOC entry 185 (class 1259 OID 285936)
-- Name: depense; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE depense (
    iddepense bigint NOT NULL,
    idstructure bigint,
    idsousperiode integer,
    idsousrubriquedepense integer,
    idperiode integer,
    montant double precision,
    pourcentage double precision
);


--
-- TOC entry 206 (class 1259 OID 286139)
-- Name: detailsc; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE detailsc (
    iddetailsc bigint NOT NULL,
    idcategorie integer,
    idstructure bigint,
    idsouscritere integer,
    point_max double precision,
    detail text
);


--
-- TOC entry 231 (class 1259 OID 442789)
-- Name: effectifcategorie; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE effectifcategorie (
    ideffectifcategorie bigint NOT NULL,
    nombre integer,
    idcategorie integer,
    idstructure bigint
);


--
-- TOC entry 232 (class 1259 OID 442804)
-- Name: effectifresponsabilite; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE effectifresponsabilite (
    ideffectifresponsabilite bigint NOT NULL,
    nombre integer,
    idstructure bigint,
    idresponsabilite integer
);


--
-- TOC entry 212 (class 1259 OID 346600)
-- Name: elementreponse; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE elementreponse (
    idelementreponse integer NOT NULL,
    nom character varying,
    valeur double precision,
    idsouscritere integer
);


--
-- TOC entry 220 (class 1259 OID 399463)
-- Name: evaluationbonuspp; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE evaluationbonuspp (
    idevaluationbonuspp bigint NOT NULL,
    point double precision,
    idcritere integer,
    pointmax double precision,
    ratio double precision,
    idnote bigint
);


--
-- TOC entry 224 (class 1259 OID 399483)
-- Name: evaluationbonusrdeptpersonnel; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE evaluationbonusrdeptpersonnel (
    idevaluationbonusrdeptpersonnel bigint NOT NULL,
    point double precision,
    idpersonnel bigint,
    idcible bigint,
    idnote bigint,
    point_max double precision DEFAULT 0
);


--
-- TOC entry 218 (class 1259 OID 391266)
-- Name: evaluationheuresupp; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE evaluationheuresupp (
    idevaluationheuresupp bigint NOT NULL,
    coefnuit double precision,
    coefjour double precision,
    nbheurejour double precision,
    nbheurenuit double precision,
    pointjour integer,
    pointnuit integer,
    idcritere integer,
    idnote bigint
);


--
-- TOC entry 225 (class 1259 OID 400198)
-- Name: evaluationpenalitedept; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE evaluationpenalitedept (
    idevaluationpenalitedept bigint NOT NULL,
    valeur double precision,
    idservice bigint,
    idperiode integer,
    idsousperiode integer,
    idstructure bigint
);


--
-- TOC entry 229 (class 1259 OID 416587)
-- Name: evaluationpenalitepersonnel; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE evaluationpenalitepersonnel (
    idevaluationpenalitepersonnel bigint NOT NULL,
    score integer,
    cible integer,
    idnote bigint
);


--
-- TOC entry 186 (class 1259 OID 285954)
-- Name: evaluationpersonnel; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE evaluationpersonnel (
    idevaluationpersonnel bigint NOT NULL,
    iddetailsc bigint,
    note double precision,
    observation character varying(254),
    idelementreponse integer,
    pointmaxindice double precision DEFAULT 0,
    idnote bigint
);


--
-- TOC entry 219 (class 1259 OID 399458)
-- Name: evaluationresponsabilite; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE evaluationresponsabilite (
    idevaluationresponsabilite bigint NOT NULL,
    point double precision,
    idcritere integer,
    pointmax double precision,
    ratio double precision,
    idnote bigint
);


--
-- TOC entry 222 (class 1259 OID 399473)
-- Name: evaluationrprimeqltifdept; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE evaluationrprimeqltifdept (
    idevaluationrprimeqltifdept bigint NOT NULL,
    score integer,
    idservice bigint,
    idperiode integer,
    idsousperiode bigint,
    idcritere bigint,
    pourcentage double precision,
    cible integer,
    idstructure bigint
);


--
-- TOC entry 223 (class 1259 OID 399478)
-- Name: evaluationrprimeqltifpersonnel; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE evaluationrprimeqltifpersonnel (
    idevaluationrprimeqltifpersonnel bigint NOT NULL,
    point double precision,
    idevaluationrprimeqltifdept bigint,
    idpersonnel bigint,
    idnote bigint,
    point_max double precision DEFAULT 0
);


--
-- TOC entry 221 (class 1259 OID 399468)
-- Name: evaluationrqntifdept; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE evaluationrqntifdept (
    idevaluationrqntifdept bigint NOT NULL,
    cible double precision,
    realisation double precision,
    ratio double precision,
    idcible bigint,
    idpersonnel bigint,
    idnote bigint
);


--
-- TOC entry 214 (class 1259 OID 374893)
-- Name: indicateur; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE indicateur (
    idindicateur integer NOT NULL,
    nom character varying(200),
    code character varying(20)
);


--
-- TOC entry 234 (class 1259 OID 459190)
-- Name: indicateurqteservice; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE indicateurqteservice (
    idindicateurqteservice bigint NOT NULL,
    idstructure bigint,
    idservice bigint,
    idindicateur integer
);


--
-- TOC entry 187 (class 1259 OID 285964)
-- Name: institution; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE institution (
    idinstitution integer NOT NULL,
    nom character varying(254),
    code character varying(254),
    choixstrategique character varying(254),
    etat character varying(254),
    sigle character varying(254),
    chapitre character varying(254)
);


--
-- TOC entry 227 (class 1259 OID 408400)
-- Name: lignepenalitedept; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE lignepenalitedept (
    idlignepenalitedept bigint NOT NULL,
    valeur integer,
    pourcentage double precision,
    etat boolean,
    idpenalite integer,
    idevaluationpenalitedept bigint
);


--
-- TOC entry 230 (class 1259 OID 416592)
-- Name: lignepenalitepersonnel; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE lignepenalitepersonnel (
    idlignepenalitepersonnel bigint NOT NULL,
    valeur integer,
    cible integer,
    idevaluationpenalitepersonnel bigint,
    idpenalite integer,
    etat boolean
);


--
-- TOC entry 228 (class 1259 OID 408405)
-- Name: ligneprimequalitedept; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE ligneprimequalitedept (
    idligneprimequalitedept bigint NOT NULL,
    valeurcible integer,
    valeurrealisee integer,
    ratio double precision,
    idsouscritereservice bigint,
    idevaluationrprimeqltifdept bigint
);


--
-- TOC entry 188 (class 1259 OID 285973)
-- Name: menu; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE menu (
    idmenu integer NOT NULL,
    nom character varying(254),
    ressource character varying(254)
);


--
-- TOC entry 189 (class 1259 OID 285982)
-- Name: mouchard; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE mouchard (
    idmouchard bigint NOT NULL,
    idutilisateur integer,
    action character varying(254),
    date date,
    heure date
);


--
-- TOC entry 190 (class 1259 OID 285989)
-- Name: note; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE note (
    idnote bigint NOT NULL,
    idsousperiode integer,
    idperiode integer,
    pointpindiv double precision,
    idpersonnel bigint,
    penalitedepartement double precision,
    pointpenalitedepartement double precision,
    penalitepersonnel double precision,
    pointpenalitepersonnel double precision,
    pointmaxrqntif double precision DEFAULT 0,
    poucentagerqntif double precision DEFAULT 0,
    pointrqntif double precision,
    incitationpositif double precision,
    incitationnegatif double precision,
    totalpoint double precision,
    pointheuresupp double precision,
    incitationnhp double precision,
    pointresponsabilite double precision,
    pointpratiquep double precision,
    pointrqltifdept double precision,
    pointbonusrdept double precision,
    scorepindiv double precision,
    pointmaxprqltif double precision DEFAULT 0,
    scoreprqltif double precision DEFAULT 0,
    pointmax_brd double precision DEFAULT 0,
    scorebrd double precision DEFAULT 0,
    pointmaxpi double precision DEFAULT 0,
    notepi double precision DEFAULT 0,
    etat boolean DEFAULT false
);


--
-- TOC entry 209 (class 1259 OID 294564)
-- Name: noteservice; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE noteservice (
    idnoteservice bigint NOT NULL,
    idperiode integer,
    idsousperiode integer,
    idservice bigint,
    note double precision,
    poids double precision
);


--
-- TOC entry 217 (class 1259 OID 374943)
-- Name: parametragecritere; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE parametragecritere (
    idparametragecritere bigint NOT NULL,
    indice integer,
    denominateur integer,
    valeur double precision,
    point double precision,
    valeurjournee double precision,
    valeurnuit double precision,
    denominateurjournee integer,
    denominateurnuit double precision,
    heuresupp boolean,
    heuresupn boolean,
    pratiqueprivee boolean,
    resultatqualitatifdept boolean,
    performanceindividuelle boolean,
    idcategorie integer,
    idcritere integer,
    idstructure bigint,
    idservice bigint,
    bonusrevenudept boolean DEFAULT false,
    resultatquantitatifdept boolean DEFAULT false,
    nombre integer DEFAULT 0,
    total1 double precision DEFAULT 0
);


--
-- TOC entry 233 (class 1259 OID 459154)
-- Name: parametragepenalite; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE parametragepenalite (
    idparametragepenalite integer NOT NULL,
    pourcentage integer,
    idpenalite integer,
    idstructure bigint,
    idservice bigint,
    idcritere integer,
    detail character varying(300)
);


--
-- TOC entry 226 (class 1259 OID 408395)
-- Name: penalite; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE penalite (
    idpenalite integer NOT NULL,
    nom character varying(200),
    pourcentage integer,
    personnel boolean,
    service boolean,
    detail character varying(300) DEFAULT '-'::character varying,
    code character varying(20)
);


--
-- TOC entry 191 (class 1259 OID 285997)
-- Name: periode; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE periode (
    idperiode integer NOT NULL,
    nom character varying(254),
    code character varying(254),
    idparent integer,
    etat boolean
);


--
-- TOC entry 192 (class 1259 OID 286006)
-- Name: personnel; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE personnel (
    idpersonnel bigint NOT NULL,
    idcategorie integer,
    nom character varying(254),
    prenom character varying(254),
    dateembauche date,
    matricule character varying(254),
    etat boolean,
    idresponsabilite integer,
    idstructure bigint,
    idservice bigint,
    plafond double precision DEFAULT 0
);


--
-- TOC entry 210 (class 1259 OID 294615)
-- Name: prime; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE prime (
    idprime bigint NOT NULL,
    montant double precision,
    indice double precision,
    point double precision,
    montantglobal double precision,
    etat boolean,
    idpersonnel bigint,
    idperiode integer,
    idsousperiode integer,
    idnote bigint,
    notepersonnelle double precision,
    observation character varying,
    relicat double precision DEFAULT 0,
    plafond double precision DEFAULT 0
);


--
-- TOC entry 193 (class 1259 OID 286017)
-- Name: privilege; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE privilege (
    idprivilege bigint NOT NULL,
    idutilisateur integer,
    idmenu integer
);


--
-- TOC entry 194 (class 1259 OID 286025)
-- Name: recette; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE recette (
    idrecette bigint NOT NULL,
    idsousrubriquerecette integer,
    idsousperiode integer,
    idperiode integer,
    idstructure bigint,
    montant double precision,
    pourcentage double precision
);


--
-- TOC entry 213 (class 1259 OID 374882)
-- Name: responsabilite; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE responsabilite (
    idresponsabilite integer NOT NULL,
    nom character varying(100),
    code character varying(20)
);


--
-- TOC entry 195 (class 1259 OID 286035)
-- Name: rubriquedepense; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE rubriquedepense (
    idrubriquedepense integer NOT NULL,
    nom character varying(254),
    code character varying(254)
);


--
-- TOC entry 196 (class 1259 OID 286044)
-- Name: rubriquerecette; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE rubriquerecette (
    idrubriquerecette integer NOT NULL,
    nom character varying(254),
    code character varying(254)
);


--
-- TOC entry 211 (class 1259 OID 328643)
-- Name: rubriquesc; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE rubriquesc (
    idrubriquesc integer NOT NULL,
    nom character varying
);


--
-- TOC entry 197 (class 1259 OID 286053)
-- Name: service; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE service (
    idservice bigint NOT NULL,
    nom character varying(254),
    code character varying(254)
);


--
-- TOC entry 200 (class 1259 OID 286083)
-- Name: souscritere; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE souscritere (
    idsouscritere integer NOT NULL,
    idcritere integer,
    nom text,
    code character varying(254),
    detail text,
    service boolean,
    personnel boolean,
    incitatif boolean,
    multiplicateur integer,
    idrubriquesc integer,
    positif boolean DEFAULT true,
    numerateur integer,
    denominateur integer,
    signe character(1),
    pointmax integer DEFAULT 0
);


--
-- TOC entry 201 (class 1259 OID 286093)
-- Name: souscritereservice; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE souscritereservice (
    idsouscritereservice bigint NOT NULL,
    idservice bigint,
    idsouscritere integer,
    detail character varying(254),
    pointmax double precision,
    idstructure bigint
);


--
-- TOC entry 202 (class 1259 OID 286101)
-- Name: sousperiode; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE sousperiode (
    idsousperiode integer NOT NULL,
    nom character varying(254),
    code character varying(254),
    numero integer,
    idtypesousperiode integer
);


--
-- TOC entry 198 (class 1259 OID 286063)
-- Name: sousrubriquedepense; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE sousrubriquedepense (
    idsousrubriquedepense integer NOT NULL,
    idrubriquedepense integer,
    nom character varying(254),
    code character varying(254),
    special boolean
);


--
-- TOC entry 199 (class 1259 OID 286073)
-- Name: sousrubriquerecette; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE sousrubriquerecette (
    idsousrubriquerecette integer NOT NULL,
    idrubriquerecette integer,
    nom character varying(254),
    code character varying(254)
);


--
-- TOC entry 207 (class 1259 OID 286148)
-- Name: statutstructure; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE statutstructure (
    idstatutstructure bigint NOT NULL,
    nom character varying(254),
    etat character varying(254)
);


--
-- TOC entry 203 (class 1259 OID 286110)
-- Name: structure; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE structure (
    idstructure bigint NOT NULL,
    idinstitution integer,
    idtypestructure bigint,
    idstatutstructure bigint,
    nom character varying(254),
    code character varying(254),
    article character varying(254),
    nomrespo character varying(254),
    arretecreation character varying(254),
    designation character varying(254),
    presentation character varying(254),
    etat character varying(254),
    dateouverture character varying(254),
    vision character varying(254),
    axeintervention character varying(254),
    objectifgeneral character varying(254),
    objectifspecifique character varying(254)
);


--
-- TOC entry 236 (class 1259 OID 467397)
-- Name: typesousperiode; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE typesousperiode (
    idtypesousperiode integer NOT NULL,
    nom character varying(50),
    code character varying(20)
);


--
-- TOC entry 208 (class 1259 OID 286157)
-- Name: typestructure; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE typestructure (
    idtypestructure bigint NOT NULL,
    nom character varying(254),
    etat character varying(254)
);


--
-- TOC entry 238 (class 1259 OID 467423)
-- Name: typestructurecategorie; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE typestructurecategorie (
    idtypestructurecategorie integer NOT NULL,
    idtypestructure bigint,
    idcategorie integer
);


--
-- TOC entry 239 (class 1259 OID 475574)
-- Name: typestructureresponsabilite; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE typestructureresponsabilite (
    idtypestructureresponsabilite integer NOT NULL,
    idtypestructure bigint,
    idresponsabilite integer
);


--
-- TOC entry 235 (class 1259 OID 467382)
-- Name: typestructureservice; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE typestructureservice (
    idtypestructureservice integer NOT NULL,
    idtypestructure bigint,
    idservice bigint
);


--
-- TOC entry 240 (class 1259 OID 475589)
-- Name: typestructuresousrubriquedepense; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE typestructuresousrubriquedepense (
    id integer NOT NULL,
    idtypestructure bigint,
    idsousrubriquedepense integer
);


--
-- TOC entry 241 (class 1259 OID 475604)
-- Name: typestructuresousrubriquerecette; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE typestructuresousrubriquerecette (
    id integer NOT NULL,
    idtypestructure bigint,
    idsousrubriquerecette integer
);


--
-- TOC entry 237 (class 1259 OID 467408)
-- Name: typestructuretypesousperiode; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE typestructuretypesousperiode (
    idtypestructuretypesousperiode integer NOT NULL,
    idtypestructure bigint,
    idtypesousperiode integer
);


--
-- TOC entry 204 (class 1259 OID 286122)
-- Name: utilisateur; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE utilisateur (
    idutilisateur integer NOT NULL,
    nom character varying(254),
    prenom character varying(254),
    login character varying(254),
    password character varying(254),
    photo character varying(254),
    actif boolean,
    template character varying(254),
    theme character varying(254),
    datecreation date,
    datederniereconnexion date,
    heurederniereconnexion date
);


--
-- TOC entry 205 (class 1259 OID 286131)
-- Name: utilisateurstructure; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE utilisateurstructure (
    idutilisateur integer NOT NULL,
    idstructure bigint NOT NULL
);


--
-- TOC entry 2747 (class 0 OID 285894)
-- Dependencies: 181
-- Data for Name: categorie; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO categorie (idcategorie, nom, code, pointmax, indice) VALUES (2, 'Ass Méd / Adjoint directeur', '2', 400, 1791);
INSERT INTO categorie (idcategorie, nom, code, pointmax, indice) VALUES (3, 'Comptable', '3', 0, 947);
INSERT INTO categorie (idcategorie, nom, code, pointmax, indice) VALUES (4, 'Infirmière - sage femme', '4', 0, 761);
INSERT INTO categorie (idcategorie, nom, code, pointmax, indice) VALUES (5, 'Caissier', '5', 0, 497);
INSERT INTO categorie (idcategorie, nom, code, pointmax, indice) VALUES (7, 'Aide infirmier ', '7', 0, 457);
INSERT INTO categorie (idcategorie, nom, code, pointmax, indice) VALUES (8, 'Assistant médical', '8', 0, 1164);
INSERT INTO categorie (idcategorie, nom, code, pointmax, indice) VALUES (9, 'Personnel non qualifié / Maternité', '9', 0, 373);
INSERT INTO categorie (idcategorie, nom, code, pointmax, indice) VALUES (1, 'Médecin Spécialiste', '1', 300, 1140);
INSERT INTO categorie (idcategorie, nom, code, pointmax, indice) VALUES (10, 'Medecin généraliste', '10', 0, 1005);
INSERT INTO categorie (idcategorie, nom, code, pointmax, indice) VALUES (6, 'Infirmer d''état', '6', 0, 650);
INSERT INTO categorie (idcategorie, nom, code, pointmax, indice) VALUES (11, 'DRSP1', 'DRSP1', 1300, 1300);
INSERT INTO categorie (idcategorie, nom, code, pointmax, indice) VALUES (12, 'DRSP2', 'DRSP2', 0, 1000);
INSERT INTO categorie (idcategorie, nom, code, pointmax, indice) VALUES (13, 'DRSP3', 'DRSP3', 0, 800);
INSERT INTO categorie (idcategorie, nom, code, pointmax, indice) VALUES (14, 'Technicien de labo', '12', 0, 700);


--
-- TOC entry 2748 (class 0 OID 285903)
-- Dependencies: 182
-- Data for Name: categoriestructure; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO categoriestructure (idstructure, idcategorie, pointmax, denominateur, indice) VALUES (1, 1, 300, 5, 1504);
INSERT INTO categoriestructure (idstructure, idcategorie, pointmax, denominateur, indice) VALUES (1, 2, 358, 5, 1791);
INSERT INTO categoriestructure (idstructure, idcategorie, pointmax, denominateur, indice) VALUES (1, 3, 189, 5, 947);
INSERT INTO categoriestructure (idstructure, idcategorie, pointmax, denominateur, indice) VALUES (1, 4, 152, 5, 761);
INSERT INTO categoriestructure (idstructure, idcategorie, pointmax, denominateur, indice) VALUES (1, 5, 99, 5, 497);
INSERT INTO categoriestructure (idstructure, idcategorie, pointmax, denominateur, indice) VALUES (1, 6, 238, 5, 1194);
INSERT INTO categoriestructure (idstructure, idcategorie, pointmax, denominateur, indice) VALUES (1, 7, 91, 5, 457);
INSERT INTO categoriestructure (idstructure, idcategorie, pointmax, denominateur, indice) VALUES (1, 8, 232, 5, 1164);
INSERT INTO categoriestructure (idstructure, idcategorie, pointmax, denominateur, indice) VALUES (1, 9, 74, 5, 373);


--
-- TOC entry 2781 (class 0 OID 374898)
-- Dependencies: 215
-- Data for Name: cible; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (1, 1500000, 0, 0, true, false, 1, 1, 3, 1, 1, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (2, 1000000, 0, 0, true, false, 2, 1, 3, 1, 1, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (3, 800000, 0, 0, true, false, 3, 1, 3, 1, 1, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (4, 700000, 0, 0, true, false, 4, 1, 3, 1, 1, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (5, 1000000, 0, 0, true, false, 1, 1, 4, 1, 1, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (6, 1500000, 0, 0, true, false, 2, 1, 4, 1, 1, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (7, 1500000, 0, 0, true, false, 3, 1, 4, 1, 1, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (8, 1500000, 0, 0, true, false, 4, 1, 4, 1, 1, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (10, 5000000, 2000000, 40, false, true, 1, 1, 4, 1, 1, 6);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (9, 10000000, 5000000, 50, false, true, 1, 1, 3, 1, 1, 6);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (11, 3000000, 1000000, 33.333333333333329, false, true, 1, 1, 5, 1, 1, 6);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (12, 10, 0, 0, true, false, 1, 1, 1, 2, 1, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (13, 10, 0, 0, true, false, 2, 1, 1, 2, 1, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (14, 10, 0, 0, true, false, 3, 1, 1, 2, 1, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (15, 10, 0, 0, true, false, 4, 1, 1, 2, 1, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (16, 1000000, 0, 0, false, true, 1, 1, 1, 2, 1, 6);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (17, 1000000, 0, 0, false, true, 1, 1, 2, 2, 1, 6);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (18, 10, 0, 0, true, false, 1, 1, 2, 2, 1, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (19, 20, 0, 0, true, false, 2, 1, 2, 2, 1, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (20, 5, 0, 0, true, false, 3, 1, 2, 2, 1, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (21, 5, 0, 0, true, false, 4, 1, 2, 2, 1, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (22, 10, 0, 0, true, false, 1, 1, 3, 3, 1, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (23, 20, 0, 0, true, false, 2, 1, 3, 3, 1, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (24, 20, 0, 0, true, false, 3, 1, 3, 3, 1, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (25, 10, 0, 0, true, false, 4, 1, 3, 3, 1, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (27, 400000, 300000, 75, false, true, 1, 1, 4, 3, 1, 6);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (26, 400000, 250000, 62.5, false, true, 1, 1, 3, 3, 1, 6);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (28, 400000, 300000, 75, false, true, 1, 1, 5, 3, 1, 6);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (29, 400000, 350000, 87.5, false, true, 1, 1, 2, 3, 1, 6);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (30, 10, 0, 0, true, false, 3, 1, 3, 3, 13, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (31, 10, 0, 0, true, false, 4, 1, 3, 3, 13, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (32, 5, 0, 0, true, false, 5, 1, 3, 3, 13, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (34, 500000, 300000, 60, false, true, 1, 1, 1, 5, 15, 6);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (33, 2000000, 1500000, 75, false, true, 1, 1, 2, 5, 15, 6);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (35, 300000, 300000, 100, false, true, 1, 1, 6, 5, 15, 6);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (36, 20, 0, 0, true, false, 1, 1, 2, 5, 15, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (37, 10, 0, 0, true, false, 3, 1, 2, 5, 15, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (38, 10, 0, 0, true, false, 5, 1, 2, 5, 15, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (39, 20, 0, 0, true, false, 1, 1, 1, 5, 15, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (40, 15, 0, 0, true, false, 3, 1, 1, 5, 15, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (41, 20, 0, 0, true, false, 5, 1, 1, 5, 15, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (42, 30, 0, 0, true, false, 2, 1, 6, 5, 15, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (43, 30, 0, 0, true, false, 4, 1, 6, 5, 15, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (44, 20, 0, 0, true, false, 1, 1, 1, 6, 15, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (45, 15, 0, 0, true, false, 3, 1, 1, 6, 15, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (46, 30, 0, 0, true, false, 5, 1, 1, 6, 15, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (50, 30, 0, 0, true, false, 1, 1, 2, 6, 15, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (51, 35, 0, 0, true, false, 3, 1, 2, 6, 15, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (52, 20, 0, 0, true, false, 4, 1, 2, 6, 15, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (53, 15, 0, 0, true, false, 1, 1, 6, 6, 15, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (54, 20, 0, 0, true, false, 2, 1, 6, 6, 15, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (55, 30, 0, 0, true, false, 3, 1, 6, 6, 15, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (48, 500000, 300000, 60, false, true, 1, 1, 1, 6, 15, 6);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (47, 1500000, 1000000, 66.666666666666657, false, true, 1, 1, 2, 6, 15, 6);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (49, 1000000, 500000, 50, false, true, 1, 1, 6, 6, 15, 6);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (56, 50, 0, 0, true, false, 1, 1, 2, 6, 16, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (57, 50, 0, 0, true, false, 4, 1, 2, 6, 16, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (59, 1000000, 700000, 70, false, true, 1, 1, 1, 6, 16, 6);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (58, 1000000, 500000, 50, false, true, 1, 1, 2, 6, 16, 6);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (60, 1000000, 600000, 60, false, true, 1, 1, 6, 6, 16, 6);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (61, 50, 0, 0, true, false, 1, 1, 6, 6, 16, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (62, 30, 0, 0, true, false, 2, 1, 6, 6, 16, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (63, 50, 0, 0, true, false, 3, 1, 6, 6, 16, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (64, 10, 0, 0, true, false, 1, 1, 1, 6, 16, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (65, 5, 0, 0, true, false, 3, 1, 1, 6, 16, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (66, 5, 0, 0, true, false, 5, 1, 1, 6, 16, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (67, 50, 0, 0, true, false, 1, 2, 3, 4, 4, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (68, 50, 0, 0, true, false, 2, 2, 3, 4, 4, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (69, 30, 0, 0, true, false, 1, 2, 4, 4, 4, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (70, 25, 0, 0, true, false, 3, 2, 4, 4, 4, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (71, 50, 0, 0, true, false, 5, 2, 4, 4, 4, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (72, 50, 0, 0, true, false, 2, 2, 5, 4, 4, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (73, 10, 0, 0, true, false, 4, 2, 5, 4, 4, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (74, 30, 0, 0, true, false, 2, 2, 7, 4, 4, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (75, 30, 0, 0, true, false, 4, 2, 7, 4, 4, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (76, 10, 0, 0, true, false, 1, 2, 8, 4, 4, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (77, 30, 0, 0, true, false, 2, 2, 8, 4, 4, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (78, 20, 0, 0, true, false, 4, 2, 8, 4, 4, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (79, 40, 0, 0, true, false, 5, 2, 8, 4, 4, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (80, 15, 0, 0, true, false, 1, 2, 9, 4, 4, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (81, 15, 0, 0, true, false, 3, 2, 9, 4, 4, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (82, 15, 0, 0, true, false, 5, 2, 9, 4, 4, 4);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (84, 2000000, 1000000, 50, false, true, 1, 2, 4, 4, 4, 6);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (86, 1000000, 700000, 70, false, true, 1, 2, 7, 4, 4, 6);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (88, 500000, 500000, 100, false, true, 1, 2, 9, 4, 4, 6);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (83, 700000, 500000, 71.428571428571431, false, true, 1, 2, 3, 4, 4, 6);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (85, 400000, 400000, 100, false, true, 1, 2, 5, 4, 4, 6);
INSERT INTO cible (idcible, valeurcible, valeurrealisee, ratio, primeresultatquant, bonusrevenudept, idindicateur, idperiode, idservice, idstructure, idsousperiode, idcritere) VALUES (87, 0, 0, 0, false, true, 1, 2, 8, 4, 4, 6);


--
-- TOC entry 2749 (class 0 OID 285911)
-- Dependencies: 183
-- Data for Name: critere; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO critere (idcritere, nom, code, poids, pointmax, scoremoyen, resultat, etat, workflow, valeurinferieur, valeursuperieur) VALUES (8, 'Heures perdues', '8', 0, 0, 0, 0, false, false, 0, 0);
INSERT INTO critere (idcritere, nom, code, poids, pointmax, scoremoyen, resultat, etat, workflow, valeurinferieur, valeursuperieur) VALUES (10, 'Pénalités individuelles  : réduction de X %', '10', 0, 0, 10, 0, false, false, 0, 0);
INSERT INTO critere (idcritere, nom, code, poids, pointmax, scoremoyen, resultat, etat, workflow, valeurinferieur, valeursuperieur) VALUES (2, 'Bonus heures supplémentaires', '2', 0, 0, 0, 0, false, false, 0, 0);
INSERT INTO critere (idcritere, nom, code, poids, pointmax, scoremoyen, resultat, etat, workflow, valeurinferieur, valeursuperieur) VALUES (9, 'Pénalité du département : redéduction sur le total des incitations positive de X %', '9', 0, 0, 5, 0, false, false, 0, 0);
INSERT INTO critere (idcritere, nom, code, poids, pointmax, scoremoyen, resultat, etat, workflow, valeurinferieur, valeursuperieur) VALUES (3, 'Prime accord de non détournement des patients pour le privé', '3', 20, 25012, 90, 22511, false, true, 5, 5);
INSERT INTO critere (idcritere, nom, code, poids, pointmax, scoremoyen, resultat, etat, workflow, valeurinferieur, valeursuperieur) VALUES (1, 'Prime de responsabilité', '1', 4, 5302, 100, 5302, false, true, 5, 5);
INSERT INTO critere (idcritere, nom, code, poids, pointmax, scoremoyen, resultat, etat, workflow, valeurinferieur, valeursuperieur) VALUES (4, 'Primes de resultat Quantitatif du département', '4', 17, 20895, 50, 10448, false, true, 5, 5);
INSERT INTO critere (idcritere, nom, code, poids, pointmax, scoremoyen, resultat, etat, workflow, valeurinferieur, valeursuperieur) VALUES (5, 'Prime de résultat Qualitatif du département', '5', 25, 31343, 50, 15672, false, true, 5, 5);
INSERT INTO critere (idcritere, nom, code, poids, pointmax, scoremoyen, resultat, etat, workflow, valeurinferieur, valeursuperieur) VALUES (6, 'Bonus basé sur les recettes du département', '6', 17, 20895, 50, 10448, false, true, 5, 5);
INSERT INTO critere (idcritere, nom, code, poids, pointmax, scoremoyen, resultat, etat, workflow, valeurinferieur, valeursuperieur) VALUES (7, 'Prime de performance individuelle', '7', 17, 20895, 80, 16716, false, true, 5, 5);


--
-- TOC entry 2782 (class 0 OID 374923)
-- Dependencies: 216
-- Data for Name: critereresponsabilite; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (2, 50, true, 2, 1, 1, 0, 0);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (4, 50, true, 4, 1, 1, 0, 0);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (5, 100, true, 5, 1, 1, 0, 0);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (3, 80, true, 3, 1, 1, 0, 0);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (1, 90, true, 1, 1, 1, 0, 0);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (6, 100, true, 6, 1, 1, 0, 0);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (7, 1000, true, 3, 2, 1, 1, 1000);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (8, 500, true, 5, 2, 1, 1, 500);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (9, 500, true, 1, 2, 1, 2, 1000);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (10, 300, true, 2, 2, 1, 3, 900);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (11, 100, true, 4, 2, 1, 4, 400);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (12, 400, true, 6, 2, 1, 3, 1200);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (13, 400, true, 1, 3, 1, 1, 400);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (14, 250, true, 2, 3, 1, 2, 500);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (15, 200, true, 3, 3, 1, 1, 200);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (16, 150, true, 4, 3, 1, 1, 150);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (17, 0, true, 5, 3, 1, 0, 0);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (18, 100, true, 6, 3, 1, 1, 100);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (19, 1200, true, 7, 5, 1, 2, 2400);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (20, 1000, true, 8, 5, 1, 1, 1000);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (21, 500, true, 9, 5, 1, 1, 500);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (22, 300, true, 10, 5, 1, 2, 600);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (23, 250, true, 7, 6, 1, 2, 500);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (24, 50, true, 8, 6, 1, 2, 100);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (25, 40, true, 9, 6, 1, 2, 80);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (26, 40, true, 10, 6, 1, 2, 80);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (27, 0, true, 1, 4, 1, 2, 0);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (32, 75, true, 13, 4, 1, 1, 75);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (30, 125, true, 4, 4, 1, 1, 125);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (31, 100, true, 12, 4, 1, 1, 100);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (28, 200, true, 2, 4, 1, 1, 200);
INSERT INTO critereresponsabilite (idcritereresponsabilite, point, responsabilite, idresponsabilite, idstructure, idcritere, nombre, total) VALUES (29, 120, true, 3, 4, 1, 1, 120);


--
-- TOC entry 2808 (class 0 OID 475666)
-- Dependencies: 242
-- Data for Name: critereservice; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2750 (class 0 OID 285928)
-- Dependencies: 184
-- Data for Name: criterestructure; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (1, 1, 4, 5302, 100, 5302, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (1, 10, 0, 0, 10, 8123, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (1, 2, 0, 0, 0, 3128, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (1, 3, 20, 25012, 90, 22511, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (1, 4, 17, 20895, 50, 10448, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (1, 5, 25, 31343, 50, 15672, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (1, 6, 17, 20895, 50, 10448, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (1, 7, 17, 20895, 80, 16716, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (1, 8, 0, 0, 0, 3128, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (1, 9, 0, 0, 5, 4107, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (2, 1, 4, 5302, 100, 5302, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (2, 10, 0, 0, 10, 8123, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (2, 2, 0, 0, 0, 3128, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (2, 3, 20, 25012, 90, 22511, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (2, 4, 17, 20895, 50, 10448, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (2, 5, 25, 31343, 50, 15672, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (2, 6, 17, 20895, 50, 10448, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (2, 7, 17, 20895, 80, 16716, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (2, 8, 0, 0, 0, 3128, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (2, 9, 0, 0, 5, 4107, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (3, 1, 4, 5302, 100, 5302, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (3, 2, 0, 0, 0, 3128, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (3, 3, 20, 25012, 90, 22511, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (3, 4, 17, 20895, 50, 10448, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (3, 5, 25, 31343, 50, 15672, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (3, 6, 17, 20895, 50, 10448, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (3, 7, 17, 20895, 80, 16716, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (3, 8, 0, 0, 0, 3128, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (3, 9, 0, 0, 5, 4107, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (3, 10, 0, 0, 10, 8123, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (5, 2, 0, 0, 0, 3128, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (5, 8, 0, 0, 0, 3128, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (5, 9, 0, 0, 5, 4107, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (5, 10, 0, 0, 10, 8123, 0, 0, 0, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (5, 1, 4, 5302, 100, 5302, 8.8932806324110665, 4500, 4.8932806324110665, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (5, 3, 20, 25012, 90, 22511, 44.466403162055336, 22500, 24.466403162055336, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (5, 4, 17, 20895, 50, 10448, 11.6600790513834, 5900, -5.3399209486165997, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (5, 5, 25, 31343, 50, 15672, 11.6600790513834, 5900, -13.3399209486166, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (5, 6, 17, 20895, 50, 10448, 11.6600790513834, 5900, -5.3399209486165997, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (5, 7, 17, 20895, 80, 16716, 11.6600790513834, 5900, -5.3399209486165997, false, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (6, 9, 0, 0, 5, 0, 0, 0, 0, true, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (6, 10, 0, 0, 10, 0, 0, 0, 0, true, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (6, 1, 4, 5302, 100, 5302, 4.7892116705526497, 760, 0.78921167055264974, true, 5, 5);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (6, 3, 20, 25012, 90, 22511, 19.534942340412123, 3100, -0.46505765958787748, true, 5, 5);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (4, 3, 20, 25012, 90, 22511, 21.501797363866331, 1615, 1.5017973638663307, true, 5, 5);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (6, 4, 17, 20895, 50, 10448, 16.831558384271219, 2671, -0.16844161572878136, true, 5, 5);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (6, 2, 0, 0, 0, 0, 0, 0, 0, true, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (6, 5, 25, 31343, 50, 15672, 25.206377213435001, 4000, 0.20637721343500104, true, 5, 5);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (6, 6, 17, 20895, 50, 10448, 16.806352007057786, 2667, -0.19364799294221413, true, 5, 5);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (6, 7, 17, 20895, 80, 16716, 16.831558384271219, 2671, -0.16844161572878136, true, 5, 5);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (6, 8, 0, 0, 0, 0, 0, 0, 0, true, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (4, 4, 17, 20895, 50, 10448, 15.057915057915059, 1131, -1.9420849420849411, true, 5, 5);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (4, 5, 25, 31343, 50, 15672, 25.069897483690589, 1883, 0.069897483690589013, true, 5, 5);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (4, 6, 17, 20895, 50, 10448, 15.057915057915059, 1131, -1.9420849420849411, true, 5, 5);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (4, 7, 17, 20895, 80, 16716, 15.057915057915059, 1131, -1.9420849420849411, true, 5, 5);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (4, 8, 0, 0, 0, 0, 0, 0, 0, true, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (4, 9, 0, 0, 5, 0, 0, 0, 0, true, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (4, 1, 4, 5302, 100, 5302, 8.2545599786979107, 620, 4.2545599786979107, true, 5, 5);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (4, 2, 0, 0, 0, 0, 0, 0, 0, true, 0, 0);
INSERT INTO criterestructure (idstructure, idcritere, poids, pointmax, scoremoyen, resultat, poidsfinal, resultatfinal, ecart, etat, valeurinferieur, valeursuperieur) VALUES (4, 10, 0, 0, 10, 0, 0, 0, 0, true, 0, 0);


--
-- TOC entry 2751 (class 0 OID 285936)
-- Dependencies: 185
-- Data for Name: depense; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (1, 1, 1, 1, 1, 7000000, 53.846153846153847);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (2, 1, 1, 2, 1, 3000000, 23.076923076923077);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (3, 1, 1, 3, 1, 3000000, 23.076923076923077);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (4, 1, 2, 1, 1, 4000000, 66.666666666666657);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (5, 1, 2, 2, 1, 300000, 5);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (6, 1, 2, 4, 1, 200000, 3.3333333333333335);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (7, 1, 2, 5, 1, 500000, 8.3333333333333321);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (8, 1, 2, 3, 1, 1000000, 16.666666666666664);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (9, 1, 3, 1, 1, 8200000, 25);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (10, 1, 3, 2, 1, 1640000, 5);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (11, 1, 3, 4, 1, 6560000, 20);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (12, 1, 3, 5, 1, 9840000, 30);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (13, 1, 3, 3, 1, 6560000, 20);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (14, 1, 4, 1, 1, 1500000, 30);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (15, 1, 4, 2, 1, 500000, 10);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (16, 1, 4, 4, 1, 500000, 10);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (17, 1, 4, 5, 1, 2000000, 40);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (18, 1, 4, 3, 1, 500000, 10);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (19, 2, 1, 1, 1, 1000000, 20);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (20, 2, 1, 2, 1, 1000000, 20);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (21, 2, 1, 4, 1, 1000000, 20);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (22, 2, 1, 5, 1, 1000000, 20);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (23, 2, 1, 3, 1, 1000000, 20);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (24, 2, 2, 1, 1, 1000000, 22.222222222222221);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (25, 2, 2, 2, 1, 1000000, 22.222222222222221);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (26, 2, 2, 4, 1, 1000000, 22.222222222222221);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (27, 2, 2, 5, 1, 750000, 16.666666666666664);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (28, 2, 2, 3, 1, 750000, 16.666666666666664);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (29, 3, 1, 1, 1, 2000000, 28.571428571428569);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (30, 3, 1, 2, 1, 1000000, 14.285714285714285);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (31, 3, 1, 4, 1, 1000000, 14.285714285714285);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (32, 3, 1, 5, 1, 1000000, 14.285714285714285);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (33, 3, 1, 3, 1, 2000000, 28.571428571428569);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (34, 2, 13, 1, 1, 2000000, 21.052631578947366);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (35, 2, 13, 2, 1, 1000000, 10.526315789473683);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (36, 2, 13, 4, 1, 2000000, 21.052631578947366);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (37, 2, 13, 5, 1, 2000000, 21.052631578947366);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (38, 2, 13, 3, 1, 2500000, 26.315789473684209);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (39, 3, 13, 1, 1, 3000000, 37.5);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (40, 3, 13, 2, 1, 2000000, 25);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (41, 3, 13, 4, 1, 0, 0);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (42, 3, 13, 5, 1, 1500000, 18.75);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (43, 3, 13, 3, 1, 1500000, 18.75);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (44, 5, 15, 1, 1, 7000000, 46.666666666666664);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (45, 5, 15, 2, 1, 5000000, 33.333333333333329);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (46, 5, 15, 3, 1, 3000000, 20);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (47, 6, 15, 1, 1, 3000000, 37.5);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (48, 6, 15, 2, 1, 2000000, 25);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (49, 6, 15, 3, 1, 3000000, 37.5);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (50, 6, 16, 1, 1, 2500000, 50);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (51, 6, 16, 2, 1, 1000000, 20);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (52, 6, 16, 3, 1, 1500000, 30);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (53, 4, 4, 1, 2, 5100000, 34);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (54, 4, 4, 2, 2, 2400000, 16);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (55, 4, 4, 4, 2, 0, 0);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (56, 4, 4, 5, 2, 5250000, 35);
INSERT INTO depense (iddepense, idstructure, idsousperiode, idsousrubriquedepense, idperiode, montant, pourcentage) VALUES (57, 4, 4, 3, 2, 2250000, 15);


--
-- TOC entry 2772 (class 0 OID 286139)
-- Dependencies: 206
-- Data for Name: detailsc; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (1, NULL, 1, 1, 9, '- Est arrivé en retard plus de 7 fois / mois = 0 points
- Est arrivé en retard 4 à 7 fois / mois = 3 points
- Est arrivé en retard 1 à 3 fois / mois = 6 points
- N''est jamais arrivé en retard = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (2, NULL, 1, 2, 9, '- A été absent de son poste sans motif connu / sans permission plus de 7 fois / mois = 0 points
- A été absent de son poste sans motif connu / sans permission(4 à 7 fois / mois) = 3 points
- A été absent de son poste sans motif connu / sans permission(1 à 3 fois / mois) = 6 points
- N''a  jamais été absent de son poste sans motif connu / sans permission = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (3, NULL, 1, 3, 9, '- Portée au poste de travail (moins de 4 fois / mois) = 0 points
- Portée quelques fois au poste de travail (plus de 4 fois / mois) = 3 points
- Tenue toujours portée mais négligée (salle ou déchirée ou non repassée) = 6 points
- Tenue toujours portée mais négligée (lavée, repassée et non déchirée) = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (4, NULL, 1, 4, 9, '- A été souvent en conflit avec les collègues et rapporté plus de 4 fois / trimestre à la hiérarchie = o points
- A été souvent en conflit avec les collègues et rapporté 2 - 4 fois / trimestre à la hiérarchie = 3 points
- A été en conflit avec les collègues 1 fois / trimestre = 6 points
- N''a jamais été en conflit avec les collègues = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (5, NULL, 1, 5, 9, '- A plus de 4 fois par trimestre refusé de donner assistance et / ou expertise aux collègues = 0 points
- A 2 - 3 fois par trimestre refusé de donner assistance et /ou expertise aux collègues = 3 points
- A 1 fois par trimestre refusé de donner assistance et /ou expertise aux collègues = 6 points
- N''a jamais refusé de donner assistance et /ou expertise aux collègues = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (6, NULL, 1, 6, 9, '- N''a jamais accompli accompli un travail supplémentaire sous - prétexte qu''il n''a pas le temps matériel = 0 points 
- A attendu les ordres de la hiérarchie  pour accomplir au moins un travail supplémentaire = 3 points
- A commencé un travail supplémentaire, mais sans terminer = 6 points
- A accompli au moins travail supplémentaire sans attendre les ordres de la hiérarchie = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (7, NULL, 1, 7, 12, '- Ne dispose jamais d''un plan journalier d''activités (Constat lors des supervision interne) = 0 points
- Ne dispose pas toujours d''un plan journalier d''activités lors des supervision internes = 4 points
- Dispose d''un plan  d''activités, mais non  respecté lors des supervisions internes = 8 points
- Dispose toujours d''un plan d''activités respecté lors des supervisions internes = 12 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (8, NULL, 1, 8, 12, '- Ne respecte pas les normes de ses taches (selon le rapport des supervisions internes) = 0 points
- Ne respecte pas les normes de ses taches (constat fait 2 fois / trimestre lors des supervisions) = 4 points
- Ne respectes pas les normes de ses taches (constat fait 1 fois / trimestre lors des supervisions) = 8 points
- Respecte toujours les normes de ses taches = 12 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (9, NULL, 1, 9, 12, '- Ne termine pas 5 fois ou plus par trimestre les taches du plan  journalier (constat supervision) = 0 points
- Ne termine pas 3 à 4 fois par trimestre les taches du plan journalier (constat supervision) = 4 points
- Ne termine pas 1 à 2 fois par trimestre les taches du plan journalier (constat supervision) = 8 points
- Termine toujours les taches définies dans son plan journalier (constat supervision) = 12 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (10, NULL, 1, 10, 10, '- Ne tiens pas compte des recommandations des supervisions internes et externes précédents plus de 4 fois par trimestre (constat lors des supervisions) = 0 points
- Ne tiens pas compte des recommandations reçues lors de supervisions internes et externes précédentes 2 à 3 fois par trimestre (constat lors des supervisions )  = 3 points
- Ne tient pas compte des recommandations reçues lors des supervisions internes et externes précédentes 1 fois par trimestre (constat lors des supervisions) = 6 points 
- Tiens toujours compte des recommandations des supervisions internes et externes = 10 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (11, NULL, 2, 1, 9, '- Est arrivé en retard plus de 7 fois / mois = 0 points
- Est arrivé en retard 4 à 7 fois / mois = 3 points
- Est arrivé en retard 1 à 3 fois / mois = 6 points
- N''est jamais arrivé en retard = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (12, NULL, 2, 2, 9, '- A été absent de son poste sans motif connu / sans permission plus de 7 fois / mois = 0 points
- A été absent de son poste sans motif connu / sans permission(4 à 7 fois / mois) = 3 points
- A été absent de son poste sans motif connu / sans permission(1 à 3 fois / mois) = 6 points
- N''a  jamais été absent de son poste sans motif connu / sans permission = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (13, NULL, 2, 3, 9, '- Portée au poste de travail (moins de 4 fois / mois) = 0 points
- Portée quelques fois au poste de travail (plus de 4 fois / mois) = 3 points
- Tenue toujours portée mais négligée (salle ou déchirée ou non repassée) = 6 points
- Tenue toujours portée mais négligée (lavée, repassée et non déchirée) = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (14, NULL, 2, 4, 9, '- A été souvent en conflit avec les collègues et rapporté plus de 4 fois / trimestre à la hiérarchie = o points
- A été souvent en conflit avec les collègues et rapporté 2 - 4 fois / trimestre à la hiérarchie = 3 points
- A été en conflit avec les collègues 1 fois / trimestre = 6 points
- N''a jamais été en conflit avec les collègues = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (15, NULL, 2, 5, 9, '- A plus de 4 fois par trimestre refusé de donner assistance et / ou expertise aux collègues = 0 points
- A 2 - 3 fois par trimestre refusé de donner assistance et /ou expertise aux collègues = 3 points
- A 1 fois par trimestre refusé de donner assistance et /ou expertise aux collègues = 6 points
- N''a jamais refusé de donner assistance et /ou expertise aux collègues = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (16, NULL, 2, 6, 9, '- N''a jamais accompli accompli un travail supplémentaire sous - prétexte qu''il n''a pas le temps matériel = 0 points 
- A attendu les ordres de la hiérarchie  pour accomplir au moins un travail supplémentaire = 3 points
- A commencé un travail supplémentaire, mais sans terminer = 6 points
- A accompli au moins travail supplémentaire sans attendre les ordres de la hiérarchie = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (17, NULL, 2, 7, 12, '- Ne dispose jamais d''un plan journalier d''activités (Constat lors des supervision interne) = 0 points
- Ne dispose pas toujours d''un plan journalier d''activités lors des supervision internes = 4 points
- Dispose d''un plan  d''activités, mais non  respecté lors des supervisions internes = 8 points
- Dispose toujours d''un plan d''activités respecté lors des supervisions internes = 12 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (18, NULL, 2, 8, 12, '- Ne respecte pas les normes de ses taches (selon le rapport des supervisions internes) = 0 points
- Ne respecte pas les normes de ses taches (constat fait 2 fois / trimestre lors des supervisions) = 4 points
- Ne respectes pas les normes de ses taches (constat fait 1 fois / trimestre lors des supervisions) = 8 points
- Respecte toujours les normes de ses taches = 12 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (19, NULL, 2, 9, 12, '- Ne termine pas 5 fois ou plus par trimestre les taches du plan  journalier (constat supervision) = 0 points
- Ne termine pas 3 à 4 fois par trimestre les taches du plan journalier (constat supervision) = 4 points
- Ne termine pas 1 à 2 fois par trimestre les taches du plan journalier (constat supervision) = 8 points
- Termine toujours les taches définies dans son plan journalier (constat supervision) = 12 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (20, NULL, 2, 10, 10, '- Ne tiens pas compte des recommandations des supervisions internes et externes précédents plus de 4 fois par trimestre (constat lors des supervisions) = 0 points
- Ne tiens pas compte des recommandations reçues lors de supervisions internes et externes précédentes 2 à 3 fois par trimestre (constat lors des supervisions )  = 3 points
- Ne tient pas compte des recommandations reçues lors des supervisions internes et externes précédentes 1 fois par trimestre (constat lors des supervisions) = 6 points 
- Tiens toujours compte des recommandations des supervisions internes et externes = 10 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (21, NULL, 3, 1, 9, '- Est arrivé en retard plus de 7 fois / mois = 0 points
- Est arrivé en retard 4 à 7 fois / mois = 3 points
- Est arrivé en retard 1 à 3 fois / mois = 6 points
- N''est jamais arrivé en retard = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (22, NULL, 3, 2, 9, '- A été absent de son poste sans motif connu / sans permission plus de 7 fois / mois = 0 points
- A été absent de son poste sans motif connu / sans permission(4 à 7 fois / mois) = 3 points
- A été absent de son poste sans motif connu / sans permission(1 à 3 fois / mois) = 6 points
- N''a  jamais été absent de son poste sans motif connu / sans permission = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (23, NULL, 3, 3, 9, '- Portée au poste de travail (moins de 4 fois / mois) = 0 points
- Portée quelques fois au poste de travail (plus de 4 fois / mois) = 3 points
- Tenue toujours portée mais négligée (salle ou déchirée ou non repassée) = 6 points
- Tenue toujours portée mais négligée (lavée, repassée et non déchirée) = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (24, NULL, 3, 4, 9, '- A été souvent en conflit avec les collègues et rapporté plus de 4 fois / trimestre à la hiérarchie = o points
- A été souvent en conflit avec les collègues et rapporté 2 - 4 fois / trimestre à la hiérarchie = 3 points
- A été en conflit avec les collègues 1 fois / trimestre = 6 points
- N''a jamais été en conflit avec les collègues = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (25, NULL, 3, 5, 9, '- A plus de 4 fois par trimestre refusé de donner assistance et / ou expertise aux collègues = 0 points
- A 2 - 3 fois par trimestre refusé de donner assistance et /ou expertise aux collègues = 3 points
- A 1 fois par trimestre refusé de donner assistance et /ou expertise aux collègues = 6 points
- N''a jamais refusé de donner assistance et /ou expertise aux collègues = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (26, NULL, 3, 6, 9, '- N''a jamais accompli accompli un travail supplémentaire sous - prétexte qu''il n''a pas le temps matériel = 0 points 
- A attendu les ordres de la hiérarchie  pour accomplir au moins un travail supplémentaire = 3 points
- A commencé un travail supplémentaire, mais sans terminer = 6 points
- A accompli au moins travail supplémentaire sans attendre les ordres de la hiérarchie = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (27, NULL, 3, 7, 12, '- Ne dispose jamais d''un plan journalier d''activités (Constat lors des supervision interne) = 0 points
- Ne dispose pas toujours d''un plan journalier d''activités lors des supervision internes = 4 points
- Dispose d''un plan  d''activités, mais non  respecté lors des supervisions internes = 8 points
- Dispose toujours d''un plan d''activités respecté lors des supervisions internes = 12 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (28, NULL, 3, 8, 12, '- Ne respecte pas les normes de ses taches (selon le rapport des supervisions internes) = 0 points
- Ne respecte pas les normes de ses taches (constat fait 2 fois / trimestre lors des supervisions) = 4 points
- Ne respectes pas les normes de ses taches (constat fait 1 fois / trimestre lors des supervisions) = 8 points
- Respecte toujours les normes de ses taches = 12 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (29, NULL, 3, 9, 12, '- Ne termine pas 5 fois ou plus par trimestre les taches du plan  journalier (constat supervision) = 0 points
- Ne termine pas 3 à 4 fois par trimestre les taches du plan journalier (constat supervision) = 4 points
- Ne termine pas 1 à 2 fois par trimestre les taches du plan journalier (constat supervision) = 8 points
- Termine toujours les taches définies dans son plan journalier (constat supervision) = 12 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (30, NULL, 3, 10, 10, '- Ne tiens pas compte des recommandations des supervisions internes et externes précédents plus de 4 fois par trimestre (constat lors des supervisions) = 0 points
- Ne tiens pas compte des recommandations reçues lors de supervisions internes et externes précédentes 2 à 3 fois par trimestre (constat lors des supervisions )  = 3 points
- Ne tient pas compte des recommandations reçues lors des supervisions internes et externes précédentes 1 fois par trimestre (constat lors des supervisions) = 6 points 
- Tiens toujours compte des recommandations des supervisions internes et externes = 10 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (31, NULL, 5, 1, 9, '- Est arrivé en retard plus de 7 fois / mois = 0 points
- Est arrivé en retard 4 à 7 fois / mois = 3 points
- Est arrivé en retard 1 à 3 fois / mois = 6 points
- N''est jamais arrivé en retard = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (32, NULL, 5, 2, 9, '- A été absent de son poste sans motif connu / sans permission plus de 7 fois / mois = 0 points
- A été absent de son poste sans motif connu / sans permission(4 à 7 fois / mois) = 3 points
- A été absent de son poste sans motif connu / sans permission(1 à 3 fois / mois) = 6 points
- N''a  jamais été absent de son poste sans motif connu / sans permission = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (33, NULL, 5, 3, 9, '- Portée au poste de travail (moins de 4 fois / mois) = 0 points
- Portée quelques fois au poste de travail (plus de 4 fois / mois) = 3 points
- Tenue toujours portée mais négligée (salle ou déchirée ou non repassée) = 6 points
- Tenue toujours portée mais négligée (lavée, repassée et non déchirée) = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (34, NULL, 5, 4, 9, '- A été souvent en conflit avec les collègues et rapporté plus de 4 fois / trimestre à la hiérarchie = o points
- A été souvent en conflit avec les collègues et rapporté 2 - 4 fois / trimestre à la hiérarchie = 3 points
- A été en conflit avec les collègues 1 fois / trimestre = 6 points
- N''a jamais été en conflit avec les collègues = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (35, NULL, 5, 5, 9, '- A plus de 4 fois par trimestre refusé de donner assistance et / ou expertise aux collègues = 0 points
- A 2 - 3 fois par trimestre refusé de donner assistance et /ou expertise aux collègues = 3 points
- A 1 fois par trimestre refusé de donner assistance et /ou expertise aux collègues = 6 points
- N''a jamais refusé de donner assistance et /ou expertise aux collègues = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (36, NULL, 5, 6, 9, '- N''a jamais accompli accompli un travail supplémentaire sous - prétexte qu''il n''a pas le temps matériel = 0 points 
- A attendu les ordres de la hiérarchie  pour accomplir au moins un travail supplémentaire = 3 points
- A commencé un travail supplémentaire, mais sans terminer = 6 points
- A accompli au moins travail supplémentaire sans attendre les ordres de la hiérarchie = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (37, NULL, 5, 7, 12, '- Ne dispose jamais d''un plan journalier d''activités (Constat lors des supervision interne) = 0 points
- Ne dispose pas toujours d''un plan journalier d''activités lors des supervision internes = 4 points
- Dispose d''un plan  d''activités, mais non  respecté lors des supervisions internes = 8 points
- Dispose toujours d''un plan d''activités respecté lors des supervisions internes = 12 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (38, NULL, 5, 8, 12, '- Ne respecte pas les normes de ses taches (selon le rapport des supervisions internes) = 0 points
- Ne respecte pas les normes de ses taches (constat fait 2 fois / trimestre lors des supervisions) = 4 points
- Ne respectes pas les normes de ses taches (constat fait 1 fois / trimestre lors des supervisions) = 8 points
- Respecte toujours les normes de ses taches = 12 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (39, NULL, 5, 9, 12, '- Ne termine pas 5 fois ou plus par trimestre les taches du plan  journalier (constat supervision) = 0 points
- Ne termine pas 3 à 4 fois par trimestre les taches du plan journalier (constat supervision) = 4 points
- Ne termine pas 1 à 2 fois par trimestre les taches du plan journalier (constat supervision) = 8 points
- Termine toujours les taches définies dans son plan journalier (constat supervision) = 12 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (40, NULL, 5, 10, 10, '- Ne tiens pas compte des recommandations des supervisions internes et externes précédents plus de 4 fois par trimestre (constat lors des supervisions) = 0 points
- Ne tiens pas compte des recommandations reçues lors de supervisions internes et externes précédentes 2 à 3 fois par trimestre (constat lors des supervisions )  = 3 points
- Ne tient pas compte des recommandations reçues lors des supervisions internes et externes précédentes 1 fois par trimestre (constat lors des supervisions) = 6 points 
- Tiens toujours compte des recommandations des supervisions internes et externes = 10 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (41, NULL, 6, 1, 9, '- Est arrivé en retard plus de 7 fois / mois = 0 points
- Est arrivé en retard 4 à 7 fois / mois = 3 points
- Est arrivé en retard 1 à 3 fois / mois = 6 points
- N''est jamais arrivé en retard = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (42, NULL, 6, 2, 9, '- A été absent de son poste sans motif connu / sans permission plus de 7 fois / mois = 0 points
- A été absent de son poste sans motif connu / sans permission(4 à 7 fois / mois) = 3 points
- A été absent de son poste sans motif connu / sans permission(1 à 3 fois / mois) = 6 points
- N''a  jamais été absent de son poste sans motif connu / sans permission = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (43, NULL, 6, 3, 9, '- Portée au poste de travail (moins de 4 fois / mois) = 0 points
- Portée quelques fois au poste de travail (plus de 4 fois / mois) = 3 points
- Tenue toujours portée mais négligée (salle ou déchirée ou non repassée) = 6 points
- Tenue toujours portée mais négligée (lavée, repassée et non déchirée) = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (44, NULL, 6, 4, 9, '- A été souvent en conflit avec les collègues et rapporté plus de 4 fois / trimestre à la hiérarchie = o points
- A été souvent en conflit avec les collègues et rapporté 2 - 4 fois / trimestre à la hiérarchie = 3 points
- A été en conflit avec les collègues 1 fois / trimestre = 6 points
- N''a jamais été en conflit avec les collègues = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (45, NULL, 6, 5, 9, '- A plus de 4 fois par trimestre refusé de donner assistance et / ou expertise aux collègues = 0 points
- A 2 - 3 fois par trimestre refusé de donner assistance et /ou expertise aux collègues = 3 points
- A 1 fois par trimestre refusé de donner assistance et /ou expertise aux collègues = 6 points
- N''a jamais refusé de donner assistance et /ou expertise aux collègues = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (46, NULL, 6, 6, 9, '- N''a jamais accompli accompli un travail supplémentaire sous - prétexte qu''il n''a pas le temps matériel = 0 points 
- A attendu les ordres de la hiérarchie  pour accomplir au moins un travail supplémentaire = 3 points
- A commencé un travail supplémentaire, mais sans terminer = 6 points
- A accompli au moins travail supplémentaire sans attendre les ordres de la hiérarchie = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (47, NULL, 6, 7, 12, '- Ne dispose jamais d''un plan journalier d''activités (Constat lors des supervision interne) = 0 points
- Ne dispose pas toujours d''un plan journalier d''activités lors des supervision internes = 4 points
- Dispose d''un plan  d''activités, mais non  respecté lors des supervisions internes = 8 points
- Dispose toujours d''un plan d''activités respecté lors des supervisions internes = 12 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (48, NULL, 6, 8, 12, '- Ne respecte pas les normes de ses taches (selon le rapport des supervisions internes) = 0 points
- Ne respecte pas les normes de ses taches (constat fait 2 fois / trimestre lors des supervisions) = 4 points
- Ne respectes pas les normes de ses taches (constat fait 1 fois / trimestre lors des supervisions) = 8 points
- Respecte toujours les normes de ses taches = 12 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (49, NULL, 6, 9, 12, '- Ne termine pas 5 fois ou plus par trimestre les taches du plan  journalier (constat supervision) = 0 points
- Ne termine pas 3 à 4 fois par trimestre les taches du plan journalier (constat supervision) = 4 points
- Ne termine pas 1 à 2 fois par trimestre les taches du plan journalier (constat supervision) = 8 points
- Termine toujours les taches définies dans son plan journalier (constat supervision) = 12 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (50, NULL, 6, 10, 10, '- Ne tiens pas compte des recommandations des supervisions internes et externes précédents plus de 4 fois par trimestre (constat lors des supervisions) = 0 points
- Ne tiens pas compte des recommandations reçues lors de supervisions internes et externes précédentes 2 à 3 fois par trimestre (constat lors des supervisions )  = 3 points
- Ne tient pas compte des recommandations reçues lors des supervisions internes et externes précédentes 1 fois par trimestre (constat lors des supervisions) = 6 points 
- Tiens toujours compte des recommandations des supervisions internes et externes = 10 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (51, NULL, 4, 1, 9, '- Est arrivé en retard plus de 7 fois / mois = 0 points
- Est arrivé en retard 4 à 7 fois / mois = 3 points
- Est arrivé en retard 1 à 3 fois / mois = 6 points
- N''est jamais arrivé en retard = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (52, NULL, 4, 2, 9, '- A été absent de son poste sans motif connu / sans permission plus de 7 fois / mois = 0 points
- A été absent de son poste sans motif connu / sans permission(4 à 7 fois / mois) = 3 points
- A été absent de son poste sans motif connu / sans permission(1 à 3 fois / mois) = 6 points
- N''a  jamais été absent de son poste sans motif connu / sans permission = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (53, NULL, 4, 3, 9, '- Portée au poste de travail (moins de 4 fois / mois) = 0 points
- Portée quelques fois au poste de travail (plus de 4 fois / mois) = 3 points
- Tenue toujours portée mais négligée (salle ou déchirée ou non repassée) = 6 points
- Tenue toujours portée mais négligée (lavée, repassée et non déchirée) = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (54, NULL, 4, 4, 9, '- A été souvent en conflit avec les collègues et rapporté plus de 4 fois / trimestre à la hiérarchie = o points
- A été souvent en conflit avec les collègues et rapporté 2 - 4 fois / trimestre à la hiérarchie = 3 points
- A été en conflit avec les collègues 1 fois / trimestre = 6 points
- N''a jamais été en conflit avec les collègues = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (55, NULL, 4, 5, 9, '- A plus de 4 fois par trimestre refusé de donner assistance et / ou expertise aux collègues = 0 points
- A 2 - 3 fois par trimestre refusé de donner assistance et /ou expertise aux collègues = 3 points
- A 1 fois par trimestre refusé de donner assistance et /ou expertise aux collègues = 6 points
- N''a jamais refusé de donner assistance et /ou expertise aux collègues = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (56, NULL, 4, 6, 9, '- N''a jamais accompli accompli un travail supplémentaire sous - prétexte qu''il n''a pas le temps matériel = 0 points 
- A attendu les ordres de la hiérarchie  pour accomplir au moins un travail supplémentaire = 3 points
- A commencé un travail supplémentaire, mais sans terminer = 6 points
- A accompli au moins travail supplémentaire sans attendre les ordres de la hiérarchie = 9 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (57, NULL, 4, 7, 12, '- Ne dispose jamais d''un plan journalier d''activités (Constat lors des supervision interne) = 0 points
- Ne dispose pas toujours d''un plan journalier d''activités lors des supervision internes = 4 points
- Dispose d''un plan  d''activités, mais non  respecté lors des supervisions internes = 8 points
- Dispose toujours d''un plan d''activités respecté lors des supervisions internes = 12 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (58, NULL, 4, 8, 12, '- Ne respecte pas les normes de ses taches (selon le rapport des supervisions internes) = 0 points
- Ne respecte pas les normes de ses taches (constat fait 2 fois / trimestre lors des supervisions) = 4 points
- Ne respectes pas les normes de ses taches (constat fait 1 fois / trimestre lors des supervisions) = 8 points
- Respecte toujours les normes de ses taches = 12 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (59, NULL, 4, 9, 12, '- Ne termine pas 5 fois ou plus par trimestre les taches du plan  journalier (constat supervision) = 0 points
- Ne termine pas 3 à 4 fois par trimestre les taches du plan journalier (constat supervision) = 4 points
- Ne termine pas 1 à 2 fois par trimestre les taches du plan journalier (constat supervision) = 8 points
- Termine toujours les taches définies dans son plan journalier (constat supervision) = 12 points');
INSERT INTO detailsc (iddetailsc, idcategorie, idstructure, idsouscritere, point_max, detail) VALUES (60, NULL, 4, 10, 10, '- Ne tiens pas compte des recommandations des supervisions internes et externes précédents plus de 4 fois par trimestre (constat lors des supervisions) = 0 points
- Ne tiens pas compte des recommandations reçues lors de supervisions internes et externes précédentes 2 à 3 fois par trimestre (constat lors des supervisions )  = 3 points
- Ne tient pas compte des recommandations reçues lors des supervisions internes et externes précédentes 1 fois par trimestre (constat lors des supervisions) = 6 points 
- Tiens toujours compte des recommandations des supervisions internes et externes = 10 points');


--
-- TOC entry 2797 (class 0 OID 442789)
-- Dependencies: 231
-- Data for Name: effectifcategorie; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (1, 1, 1, 1);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (2, 1, 10, 1);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (3, 1, 2, 1);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (4, 1, 3, 1);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (5, 1, 4, 1);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (6, 1, 5, 1);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (7, 1, 6, 1);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (9, 1, 8, 1);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (10, 1, 9, 1);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (8, 2, 7, 1);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (13, 1, 2, 2);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (15, 1, 4, 2);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (17, 1, 6, 2);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (20, 1, 9, 2);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (11, 3, 1, 2);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (12, 5, 10, 2);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (14, 2, 3, 2);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (16, 4, 5, 2);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (18, 4, 7, 2);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (19, 2, 8, 2);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (21, 3, 1, 3);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (22, 1, 10, 3);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (23, 0, 2, 3);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (24, 0, 3, 3);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (25, 1, 4, 3);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (26, 0, 5, 3);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (27, 0, 6, 3);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (28, 1, 7, 3);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (29, 0, 8, 3);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (30, 0, 9, 3);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (31, 1, 11, 5);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (32, 3, 12, 5);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (33, 2, 13, 5);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (34, 2, 11, 6);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (35, 3, 12, 6);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (36, 3, 13, 6);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (37, 1, 7, 4);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (38, 0, 2, 4);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (39, 0, 8, 4);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (40, 1, 5, 4);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (41, 1, 3, 4);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (42, 0, 6, 4);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (43, 1, 4, 4);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (44, 0, 10, 4);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (45, 2, 1, 4);
INSERT INTO effectifcategorie (ideffectifcategorie, nombre, idcategorie, idstructure) VALUES (46, 1, 14, 4);


--
-- TOC entry 2798 (class 0 OID 442804)
-- Dependencies: 232
-- Data for Name: effectifresponsabilite; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (1, 1, 1, 1);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (2, 1, 1, 2);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (3, 1, 1, 3);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (4, 1, 1, 4);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (5, 1, 1, 5);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (6, 1, 1, 6);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (9, 1, 2, 3);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (11, 1, 2, 5);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (7, 2, 2, 1);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (8, 3, 2, 2);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (10, 4, 2, 4);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (12, 3, 2, 6);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (13, 1, 3, 1);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (14, 2, 3, 2);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (15, 1, 3, 3);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (16, 1, 3, 4);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (17, 0, 3, 5);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (18, 1, 3, 6);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (19, 2, 5, 7);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (20, 1, 5, 8);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (21, 1, 5, 9);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (22, 2, 5, 10);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (23, 2, 6, 7);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (24, 2, 6, 8);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (25, 2, 6, 9);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (26, 2, 6, 10);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (27, 2, 4, 1);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (28, 1, 4, 2);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (29, 1, 4, 3);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (30, 1, 4, 4);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (31, 1, 4, 12);
INSERT INTO effectifresponsabilite (ideffectifresponsabilite, nombre, idstructure, idresponsabilite) VALUES (32, 1, 4, 13);


--
-- TOC entry 2778 (class 0 OID 346600)
-- Dependencies: 212
-- Data for Name: elementreponse; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (1, '-', 0, 1);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (6, '-', 0, 2);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (11, '-', 0, 3);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (12, '- Portée au poste de travail (moins de 4 fois / mois) = 0 points', 0, 3);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (16, '-', 0, 4);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (21, '-', 0, 5);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (25, '- N''a jamais refusé de donner assistance et /ou expertise aux collègues = 9 points', 9, 5);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (26, '-', 0, 6);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (28, '- A attendu les ordres de la hiérarchie  pour accomplir au moins un travail supplémentaire = 3 Points', 3, 6);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (29, '- A commencé un travail supplémentaire, mais sans terminer = 6 points', 6, 6);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (31, '-', 0, 7);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (32, '- Ne dispose jamais d''un plan journalier d''activités (Constat lors des supervision interne) = 0 points', 0, 7);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (33, '- Ne dispose pas toujours d''un plan journalier d''activités lors des supervision internes = 4 points', 4, 7);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (34, '- Dispose d''un plan  d''activités, mais non  respecté lors des supervisions internes = 8 points', 8, 7);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (35, '- Dispose toujours d''un plan d''activités respecté lors des supervisions internes = 12 points', 12, 7);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (36, '-', 0, 8);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (37, '- Ne respecte pas les normes de ses taches (selon le rapport des supervisions internes) = 0 points', 0, 8);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (38, '- Ne respecte pas les normes de ses taches (constat fait 2 fois / trimestre lors des supervisions) = 4 points', 4, 8);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (39, '- Ne respectes pas les normes de ses taches (constat fait 1 fois / trimestre lors des supervisions) = 8 points', 8, 8);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (40, '- Respecte toujours les normes de ses taches = 12 points', 12, 8);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (41, '-', 0, 9);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (42, '- Ne termine pas 5 fois ou plus par trimestre les taches du plan  journalier (constat supervision) = 0 points', 0, 9);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (43, '- Ne termine pas 3 à 4 fois par trimestre les taches du plan journalier (constat supervision) = 4 points', 4, 9);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (44, '- Ne termine pas 1 à 2 fois par trimestre les taches du plan journalier (constat supervision) = 8 points', 8, 9);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (45, '- Termine toujours les taches définies dans son plan journalier (constat supervision) = 12 points', 12, 9);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (46, '-', 0, 10);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (47, '- Ne tiens pas compte des recommandations des supervisions internes et externes précédents plus de 4 fois par trimestre (constat lors des supervisions) = 0 points', 0, 10);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (48, '- Ne tiens pas compte des recommandations reçues lors de supervisions internes et externes précédentes 2 à 3 fois par trimestre (constat lors des supervisions )  = 3 points', 3, 10);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (49, '- Ne tient pas compte des recommandations reçues lors des supervisions internes et externes précédentes 1 fois par trimestre (constat lors des supervisions) = 6 points ', 6, 10);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (50, '- Tiens toujours compte des recommandations des supervisions internes et externes = 10 points', 10, 10);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (2, 'Est arrivé en retard plus de 7 fois / mois = 0 points', 0, 1);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (3, 'Est arrivé en retard 4 à 7 fois / mois = 3 points', 3, 1);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (4, 'Est arrivé en retard 1 à 3 fois / mois = 6 points', 6, 1);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (5, 'N''est jamais arrivé en retard = 9 points', 9, 1);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (7, 'A été absent de son poste sans motif connu / sans permission plus de 7 fois / mois = 0 points', 0, 2);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (8, 'A été absent de son poste sans motif connu / sans permission(4 à 7 fois / mois) = 3 points', 3, 2);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (9, 'A été absent de son poste sans motif connu / sans permission(1 à 3 fois / mois) = 6 points', 6, 2);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (10, 'N''a  jamais été absent de son poste sans motif connu / sans permission = 9 points', 9, 2);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (13, 'Portée quelques fois au poste de travail (plus de 4 fois / mois) = 3 points', 3, 3);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (14, 'Tenue toujours portée mais négligée (salle ou déchirée ou non repassée) = 6 points', 6, 3);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (15, 'Tenue toujours portée mais négligée (lavée, repassée et non déchirée) = 9 points', 9, 3);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (17, 'A été souvent en conflit avec les collègues et rapporté plus de 4 fois / trimestre à la hiérarchie = 0 points', 0, 4);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (19, 'A été en conflit avec les collègues 1 fois / trimestre = 6 points', 6, 4);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (20, 'N''a jamais été en conflit avec les collègues = 9 points', 9, 4);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (18, 'A été souvent en conflit avec les collègues et rapporté 2 - 4 fois / trimestre à la hiérarchie = 3 points', 3, 4);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (22, 'A plus de 4 fois par trimestre refusé de donner assistance et / ou expertise aux collègues = 0 ', 0, 5);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (23, 'A 2 - 3 fois par trimestre refusé de donner assistance et /ou expertise aux collègues = 3 points', 3, 5);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (24, 'A 1 fois par trimestre refusé de donner assistance et /ou expertise aux collègues = 6 points', 6, 5);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (27, 'N''a jamais accompli accompli un travail supplémentaire sous - prétexte qu''il n''a pas le temps matériel = 0 points ', 0, 6);
INSERT INTO elementreponse (idelementreponse, nom, valeur, idsouscritere) VALUES (30, 'A accompli au moins travail supplémentaire sans attendre les ordres de la hiérarchie = 9 points', 9, 6);


--
-- TOC entry 2786 (class 0 OID 399463)
-- Dependencies: 220
-- Data for Name: evaluationbonuspp; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO evaluationbonuspp (idevaluationbonuspp, point, idcritere, pointmax, ratio, idnote) VALUES (1, 400, 3, 500, 80, 1);
INSERT INTO evaluationbonuspp (idevaluationbonuspp, point, idcritere, pointmax, ratio, idnote) VALUES (2, 5200, 3, 6500, 80, 2);
INSERT INTO evaluationbonuspp (idevaluationbonuspp, point, idcritere, pointmax, ratio, idnote) VALUES (3, 3200, 3, 4000, 80, 3);
INSERT INTO evaluationbonuspp (idevaluationbonuspp, point, idcritere, pointmax, ratio, idnote) VALUES (4, 2000, 3, 2000, 100, 4);
INSERT INTO evaluationbonuspp (idevaluationbonuspp, point, idcritere, pointmax, ratio, idnote) VALUES (5, 3200, 3, 4000, 80, 5);
INSERT INTO evaluationbonuspp (idevaluationbonuspp, point, idcritere, pointmax, ratio, idnote) VALUES (6, 400, 3, 500, 80, 6);
INSERT INTO evaluationbonuspp (idevaluationbonuspp, point, idcritere, pointmax, ratio, idnote) VALUES (7, 320, 3, 400, 80, 7);
INSERT INTO evaluationbonuspp (idevaluationbonuspp, point, idcritere, pointmax, ratio, idnote) VALUES (8, 320, 3, 400, 80, 8);
INSERT INTO evaluationbonuspp (idevaluationbonuspp, point, idcritere, pointmax, ratio, idnote) VALUES (9, 500, 3, 500, 100, 9);
INSERT INTO evaluationbonuspp (idevaluationbonuspp, point, idcritere, pointmax, ratio, idnote) VALUES (10, 300, 3, 300, 100, 10);
INSERT INTO evaluationbonuspp (idevaluationbonuspp, point, idcritere, pointmax, ratio, idnote) VALUES (11, 400, 3, 400, 100, 11);
INSERT INTO evaluationbonuspp (idevaluationbonuspp, point, idcritere, pointmax, ratio, idnote) VALUES (12, 300, 3, 300, 100, 12);
INSERT INTO evaluationbonuspp (idevaluationbonuspp, point, idcritere, pointmax, ratio, idnote) VALUES (13, 300, 3, 300, 100, 13);
INSERT INTO evaluationbonuspp (idevaluationbonuspp, point, idcritere, pointmax, ratio, idnote) VALUES (14, 175, 3, 175, 100, 14);
INSERT INTO evaluationbonuspp (idevaluationbonuspp, point, idcritere, pointmax, ratio, idnote) VALUES (15, 250, 3, 250, 100, 15);
INSERT INTO evaluationbonuspp (idevaluationbonuspp, point, idcritere, pointmax, ratio, idnote) VALUES (16, 140, 3, 140, 100, 16);
INSERT INTO evaluationbonuspp (idevaluationbonuspp, point, idcritere, pointmax, ratio, idnote) VALUES (17, 400, 3, 400, 100, 17);
INSERT INTO evaluationbonuspp (idevaluationbonuspp, point, idcritere, pointmax, ratio, idnote) VALUES (18, 50, 3, 50, 100, 18);
INSERT INTO evaluationbonuspp (idevaluationbonuspp, point, idcritere, pointmax, ratio, idnote) VALUES (19, 160, 3, 200, 80, 19);
INSERT INTO evaluationbonuspp (idevaluationbonuspp, point, idcritere, pointmax, ratio, idnote) VALUES (20, 400, 3, 400, 100, 20);


--
-- TOC entry 2790 (class 0 OID 399483)
-- Dependencies: 224
-- Data for Name: evaluationbonusrdeptpersonnel; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO evaluationbonusrdeptpersonnel (idevaluationbonusrdeptpersonnel, point, idpersonnel, idcible, idnote, point_max) VALUES (1, 570, 9, 9, 1, 0);
INSERT INTO evaluationbonusrdeptpersonnel (idevaluationbonusrdeptpersonnel, point, idpersonnel, idcible, idnote, point_max) VALUES (2, 780, 15, 34, 2, 0);
INSERT INTO evaluationbonusrdeptpersonnel (idevaluationbonusrdeptpersonnel, point, idpersonnel, idcible, idnote, point_max) VALUES (3, 600, 16, 34, 3, 0);
INSERT INTO evaluationbonusrdeptpersonnel (idevaluationbonusrdeptpersonnel, point, idpersonnel, idcible, idnote, point_max) VALUES (4, 800, 17, 35, 4, 0);
INSERT INTO evaluationbonusrdeptpersonnel (idevaluationbonusrdeptpersonnel, point, idpersonnel, idcible, idnote, point_max) VALUES (5, 1000, 18, 35, 5, 0);
INSERT INTO evaluationbonusrdeptpersonnel (idevaluationbonusrdeptpersonnel, point, idpersonnel, idcible, idnote, point_max) VALUES (6, 261, 21, 48, 6, 434);
INSERT INTO evaluationbonusrdeptpersonnel (idevaluationbonusrdeptpersonnel, point, idpersonnel, idcible, idnote, point_max) VALUES (7, 167, 22, 49, 7, 334);
INSERT INTO evaluationbonusrdeptpersonnel (idevaluationbonusrdeptpersonnel, point, idpersonnel, idcible, idnote, point_max) VALUES (8, 167, 23, 49, 8, 334);
INSERT INTO evaluationbonusrdeptpersonnel (idevaluationbonusrdeptpersonnel, point, idpersonnel, idcible, idnote, point_max) VALUES (9, 290, 24, 47, 9, 434);
INSERT INTO evaluationbonusrdeptpersonnel (idevaluationbonusrdeptpersonnel, point, idpersonnel, idcible, idnote, point_max) VALUES (10, 161, 25, 48, 10, 267);
INSERT INTO evaluationbonusrdeptpersonnel (idevaluationbonusrdeptpersonnel, point, idpersonnel, idcible, idnote, point_max) VALUES (11, 201, 26, 48, 11, 334);
INSERT INTO evaluationbonusrdeptpersonnel (idevaluationbonusrdeptpersonnel, point, idpersonnel, idcible, idnote, point_max) VALUES (12, 134, 27, 49, 12, 267);
INSERT INTO evaluationbonusrdeptpersonnel (idevaluationbonusrdeptpersonnel, point, idpersonnel, idcible, idnote, point_max) VALUES (13, 178, 28, 47, 13, 267);
INSERT INTO evaluationbonusrdeptpersonnel (idevaluationbonusrdeptpersonnel, point, idpersonnel, idcible, idnote, point_max) VALUES (14, 70, 35, 86, 14, 100);
INSERT INTO evaluationbonusrdeptpersonnel (idevaluationbonusrdeptpersonnel, point, idpersonnel, idcible, idnote, point_max) VALUES (15, 140, 34, 88, 15, 140);
INSERT INTO evaluationbonusrdeptpersonnel (idevaluationbonusrdeptpersonnel, point, idpersonnel, idcible, idnote, point_max) VALUES (16, 92, 33, 85, 16, 92);
INSERT INTO evaluationbonusrdeptpersonnel (idevaluationbonusrdeptpersonnel, point, idpersonnel, idcible, idnote, point_max) VALUES (17, 114, 32, 84, 17, 228);
INSERT INTO evaluationbonusrdeptpersonnel (idevaluationbonusrdeptpersonnel, point, idpersonnel, idcible, idnote, point_max) VALUES (18, 133, 31, 86, 18, 190);
INSERT INTO evaluationbonusrdeptpersonnel (idevaluationbonusrdeptpersonnel, point, idpersonnel, idcible, idnote, point_max) VALUES (19, 153, 29, 85, 19, 153);
INSERT INTO evaluationbonusrdeptpersonnel (idevaluationbonusrdeptpersonnel, point, idpersonnel, idcible, idnote, point_max) VALUES (20, 0, 30, 87, 20, 228);


--
-- TOC entry 2784 (class 0 OID 391266)
-- Dependencies: 218
-- Data for Name: evaluationheuresupp; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (1, 2.2799999999999998, 1.1399999999999999, 10, 20, 11, 46, 2, 1);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (2, 2.2799999999999998, 1.1399999999999999, 5, 5, 6, 11, 8, 1);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (3, 16.25, 13, 10, 10, 130, 162, 2, 2);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (4, 16.25, 13, 3, 3, 39, 49, 8, 2);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (5, 12.5, 10, 10, 5, 100, 62, 2, 3);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (6, 12.5, 10, 3, 3, 30, 38, 8, 3);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (7, 10, 8, 10, 15, 80, 150, 2, 4);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (8, 10, 8, 3, 5, 24, 50, 8, 4);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (9, 12.5, 10, 20, 15, 200, 188, 2, 5);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (10, 12.5, 10, 3, 3, 30, 38, 8, 5);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (11, 2.6000000000000001, 1.3, 10, 10, 13, 26, 2, 6);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (12, 2.6000000000000001, 1.3, 0, 5, 0, 13, 8, 6);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (13, 2, 1, 10, 15, 10, 30, 2, 7);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (14, 2, 1, 0, 0, 0, 0, 8, 7);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (15, 2, 1, 15, 20, 15, 40, 2, 8);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (16, 2, 1, 5, 0, 5, 0, 8, 8);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (17, 2.6000000000000001, 1.3, 10, 5, 13, 13, 2, 9);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (18, 2.6000000000000001, 1.3, 0, 0, 0, 0, 8, 9);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (19, 1.6000000000000001, 0.80000000000000004, 15, 10, 12, 16, 2, 10);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (20, 1.6000000000000001, 0.80000000000000004, 5, 0, 4, 0, 8, 10);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (21, 2, 1, 5, 0, 5, 0, 2, 11);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (22, 2, 1, 0, 0, 0, 0, 8, 11);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (23, 1.6000000000000001, 0.80000000000000004, 0, 0, 0, 0, 2, 12);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (24, 1.6000000000000001, 0.80000000000000004, 0, 10, 0, 16, 8, 12);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (25, 1.6000000000000001, 0.80000000000000004, 10, 10, 8, 16, 2, 13);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (26, 1.6000000000000001, 0.80000000000000004, 0, 0, 0, 0, 8, 13);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (27, 0.99399999999999999, 0.497, 10, 20, 5, 20, 2, 14);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (28, 0.99399999999999999, 0.497, 5, 0, 3, 0, 8, 14);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (29, 1.3999999999999999, 0.69999999999999996, 20, 10, 14, 14, 2, 15);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (30, 1.3999999999999999, 0.69999999999999996, 0, 10, 0, 14, 8, 15);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (31, 0.91400000000000003, 0.45700000000000002, 10, 10, 5, 10, 2, 16);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (32, 0.91400000000000003, 0.45700000000000002, 10, 0, 5, 0, 8, 16);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (33, 2.2799999999999998, 1.1399999999999999, 10, 10, 12, 23, 2, 17);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (34, 2.2799999999999998, 1.1399999999999999, 10, 0, 12, 0, 8, 17);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (35, 1.8939999999999999, 0.94699999999999995, 100, 100, 95, 190, 2, 18);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (36, 1.8939999999999999, 0.94699999999999995, 0, 10, 0, 19, 8, 18);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (37, 1.522, 0.76100000000000001, 10, 15, 8, 23, 2, 19);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (38, 1.522, 0.76100000000000001, 0, 10, 0, 16, 8, 19);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (39, 2.2799999999999998, 1.1399999999999999, 15, 20, 18, 46, 2, 20);
INSERT INTO evaluationheuresupp (idevaluationheuresupp, coefnuit, coefjour, nbheurejour, nbheurenuit, pointjour, pointnuit, idcritere, idnote) VALUES (40, 2.2799999999999998, 1.1399999999999999, 10, 10, 12, 23, 8, 20);


--
-- TOC entry 2791 (class 0 OID 400198)
-- Dependencies: 225
-- Data for Name: evaluationpenalitedept; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO evaluationpenalitedept (idevaluationpenalitedept, valeur, idservice, idperiode, idsousperiode, idstructure) VALUES (1, 15, 3, 1, 1, NULL);
INSERT INTO evaluationpenalitedept (idevaluationpenalitedept, valeur, idservice, idperiode, idsousperiode, idstructure) VALUES (2, 5, 4, 1, 1, NULL);
INSERT INTO evaluationpenalitedept (idevaluationpenalitedept, valeur, idservice, idperiode, idsousperiode, idstructure) VALUES (3, 5, 5, 1, 1, NULL);
INSERT INTO evaluationpenalitedept (idevaluationpenalitedept, valeur, idservice, idperiode, idsousperiode, idstructure) VALUES (4, 5, 3, 1, 1, 3);
INSERT INTO evaluationpenalitedept (idevaluationpenalitedept, valeur, idservice, idperiode, idsousperiode, idstructure) VALUES (5, 10, 4, 1, 1, 3);
INSERT INTO evaluationpenalitedept (idevaluationpenalitedept, valeur, idservice, idperiode, idsousperiode, idstructure) VALUES (6, 5, 5, 1, 1, 3);
INSERT INTO evaluationpenalitedept (idevaluationpenalitedept, valeur, idservice, idperiode, idsousperiode, idstructure) VALUES (7, 10, 2, 1, 1, 3);
INSERT INTO evaluationpenalitedept (idevaluationpenalitedept, valeur, idservice, idperiode, idsousperiode, idstructure) VALUES (8, 15, 3, 1, 1, 2);
INSERT INTO evaluationpenalitedept (idevaluationpenalitedept, valeur, idservice, idperiode, idsousperiode, idstructure) VALUES (9, 5, 1, 1, 15, 5);
INSERT INTO evaluationpenalitedept (idevaluationpenalitedept, valeur, idservice, idperiode, idsousperiode, idstructure) VALUES (10, 5, 2, 1, 15, 5);
INSERT INTO evaluationpenalitedept (idevaluationpenalitedept, valeur, idservice, idperiode, idsousperiode, idstructure) VALUES (11, 5, 6, 1, 15, 5);
INSERT INTO evaluationpenalitedept (idevaluationpenalitedept, valeur, idservice, idperiode, idsousperiode, idstructure) VALUES (12, 5, 1, 1, 15, 6);
INSERT INTO evaluationpenalitedept (idevaluationpenalitedept, valeur, idservice, idperiode, idsousperiode, idstructure) VALUES (13, 5, 6, 1, 15, 6);
INSERT INTO evaluationpenalitedept (idevaluationpenalitedept, valeur, idservice, idperiode, idsousperiode, idstructure) VALUES (14, 5, 2, 1, 15, 6);
INSERT INTO evaluationpenalitedept (idevaluationpenalitedept, valeur, idservice, idperiode, idsousperiode, idstructure) VALUES (15, 5, 1, 1, 16, 6);
INSERT INTO evaluationpenalitedept (idevaluationpenalitedept, valeur, idservice, idperiode, idsousperiode, idstructure) VALUES (16, 5, 3, 2, 4, 4);
INSERT INTO evaluationpenalitedept (idevaluationpenalitedept, valeur, idservice, idperiode, idsousperiode, idstructure) VALUES (17, 10, 4, 2, 4, 4);
INSERT INTO evaluationpenalitedept (idevaluationpenalitedept, valeur, idservice, idperiode, idsousperiode, idstructure) VALUES (18, 5, 5, 2, 4, 4);
INSERT INTO evaluationpenalitedept (idevaluationpenalitedept, valeur, idservice, idperiode, idsousperiode, idstructure) VALUES (19, 0, 7, 2, 4, 4);
INSERT INTO evaluationpenalitedept (idevaluationpenalitedept, valeur, idservice, idperiode, idsousperiode, idstructure) VALUES (20, 5, 8, 2, 4, 4);
INSERT INTO evaluationpenalitedept (idevaluationpenalitedept, valeur, idservice, idperiode, idsousperiode, idstructure) VALUES (21, 0, 9, 2, 4, 4);


--
-- TOC entry 2795 (class 0 OID 416587)
-- Dependencies: 229
-- Data for Name: evaluationpenalitepersonnel; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO evaluationpenalitepersonnel (idevaluationpenalitepersonnel, score, cible, idnote) VALUES (1, 5, 20, 1);
INSERT INTO evaluationpenalitepersonnel (idevaluationpenalitepersonnel, score, cible, idnote) VALUES (2, 5, 20, 2);
INSERT INTO evaluationpenalitepersonnel (idevaluationpenalitepersonnel, score, cible, idnote) VALUES (3, 5, 20, 3);
INSERT INTO evaluationpenalitepersonnel (idevaluationpenalitepersonnel, score, cible, idnote) VALUES (4, 0, 20, 4);
INSERT INTO evaluationpenalitepersonnel (idevaluationpenalitepersonnel, score, cible, idnote) VALUES (5, 5, 20, 5);
INSERT INTO evaluationpenalitepersonnel (idevaluationpenalitepersonnel, score, cible, idnote) VALUES (6, 15, 20, 6);
INSERT INTO evaluationpenalitepersonnel (idevaluationpenalitepersonnel, score, cible, idnote) VALUES (7, 0, 20, 7);
INSERT INTO evaluationpenalitepersonnel (idevaluationpenalitepersonnel, score, cible, idnote) VALUES (8, 5, 20, 8);
INSERT INTO evaluationpenalitepersonnel (idevaluationpenalitepersonnel, score, cible, idnote) VALUES (9, 5, 20, 9);
INSERT INTO evaluationpenalitepersonnel (idevaluationpenalitepersonnel, score, cible, idnote) VALUES (10, 5, 20, 10);
INSERT INTO evaluationpenalitepersonnel (idevaluationpenalitepersonnel, score, cible, idnote) VALUES (11, 10, 20, 11);
INSERT INTO evaluationpenalitepersonnel (idevaluationpenalitepersonnel, score, cible, idnote) VALUES (12, 5, 20, 12);
INSERT INTO evaluationpenalitepersonnel (idevaluationpenalitepersonnel, score, cible, idnote) VALUES (13, 0, 20, 13);
INSERT INTO evaluationpenalitepersonnel (idevaluationpenalitepersonnel, score, cible, idnote) VALUES (14, 5, 20, 14);
INSERT INTO evaluationpenalitepersonnel (idevaluationpenalitepersonnel, score, cible, idnote) VALUES (15, 5, 20, 15);
INSERT INTO evaluationpenalitepersonnel (idevaluationpenalitepersonnel, score, cible, idnote) VALUES (16, 5, 20, 16);
INSERT INTO evaluationpenalitepersonnel (idevaluationpenalitepersonnel, score, cible, idnote) VALUES (17, 5, 20, 17);
INSERT INTO evaluationpenalitepersonnel (idevaluationpenalitepersonnel, score, cible, idnote) VALUES (18, 5, 20, 18);
INSERT INTO evaluationpenalitepersonnel (idevaluationpenalitepersonnel, score, cible, idnote) VALUES (19, 0, 20, 19);
INSERT INTO evaluationpenalitepersonnel (idevaluationpenalitepersonnel, score, cible, idnote) VALUES (20, 5, 20, 20);


--
-- TOC entry 2752 (class 0 OID 285954)
-- Dependencies: 186
-- Data for Name: evaluationpersonnel; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (51, 41, 9, '---', 5, 0, 6);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (52, 42, 9, '---', 10, 0, 6);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (53, 43, 9, '---', 15, 0, 6);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (54, 44, 9, '---', 20, 0, 6);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (55, 45, 9, '---', 25, 0, 6);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (56, 46, 9, '---', 30, 0, 6);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (57, 47, 12, '---', 35, 0, 6);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (58, 48, 12, '---', 40, 0, 6);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (59, 49, 12, '---', 45, 0, 6);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (60, 50, 10, '---', 50, 0, 6);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (1, 21, 0, '---', 2, 0, 1);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (2, 22, 6, '---', 9, 0, 1);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (3, 23, 9, '---', 15, 0, 1);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (4, 24, 0, '---', 17, 0, 1);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (5, 25, 6, '---', 24, 0, 1);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (6, 26, 6, '---', 29, 0, 1);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (7, 27, 12, '---', 35, 0, 1);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (8, 28, 8, '---', 39, 0, 1);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (9, 29, 12, '---', 45, 0, 1);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (10, 30, 10, '---', 50, 0, 1);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (11, 31, 9, '---', 5, 0, 2);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (12, 32, 9, '---', 10, 0, 2);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (13, 33, 6, '---', 14, 0, 2);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (14, 34, 3, '---', 18, 0, 2);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (15, 35, 0, '---', 22, 0, 2);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (16, 36, 9, '---', 30, 0, 2);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (17, 37, 4, '---', 33, 0, 2);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (18, 38, 12, '---', 40, 0, 2);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (19, 39, 12, '---', 45, 0, 2);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (20, 40, 6, '---', 49, 0, 2);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (21, 31, 0, '---', 2, 0, 3);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (22, 32, 3, '---', 8, 0, 3);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (23, 33, 6, '---', 14, 0, 3);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (24, 34, 9, '---', 20, 0, 3);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (25, 35, 9, '---', 25, 0, 3);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (26, 36, 3, '---', 28, 0, 3);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (27, 37, 8, '---', 34, 0, 3);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (28, 38, 12, '---', 40, 0, 3);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (29, 39, 12, '---', 45, 0, 3);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (30, 40, 10, '---', 50, 0, 3);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (31, 31, 9, '---', 5, 0, 4);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (32, 32, 9, '---', 10, 0, 4);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (33, 33, 9, '---', 15, 0, 4);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (34, 34, 9, '---', 20, 0, 4);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (35, 35, 9, '---', 25, 0, 4);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (36, 36, 9, '---', 30, 0, 4);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (37, 37, 12, '---', 35, 0, 4);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (38, 38, 12, '---', 40, 0, 4);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (39, 39, 12, '---', 45, 0, 4);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (40, 40, 10, '---', 50, 0, 4);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (41, 31, 0, '---', 2, 0, 5);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (42, 32, 3, '---', 8, 0, 5);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (43, 33, 6, '---', 14, 0, 5);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (44, 34, 9, '---', 20, 0, 5);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (45, 35, 3, '---', 23, 0, 5);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (46, 36, 6, '---', 29, 0, 5);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (47, 37, 12, '---', 35, 0, 5);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (48, 38, 0, '---', 37, 0, 5);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (49, 39, 12, '---', 45, 0, 5);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (50, 40, 6, '---', 49, 0, 5);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (61, 41, 9, '---', 5, 0, 7);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (62, 42, 9, '---', 10, 0, 7);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (63, 43, 3, '---', 13, 0, 7);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (64, 44, 3, '---', 18, 0, 7);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (65, 45, 0, '---', 22, 0, 7);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (66, 46, 9, '---', 30, 0, 7);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (67, 47, 8, '---', 34, 0, 7);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (68, 48, 4, '---', 38, 0, 7);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (69, 49, 12, '---', 45, 0, 7);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (70, 50, 10, '---', 50, 0, 7);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (71, 41, 3, '---', 3, 0, 8);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (72, 42, 3, '---', 8, 0, 8);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (73, 43, 6, '---', 14, 0, 8);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (74, 44, 6, '---', 19, 0, 8);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (75, 45, 9, '---', 25, 0, 8);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (76, 46, 0, '---', 27, 0, 8);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (77, 47, 12, '---', 35, 0, 8);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (78, 48, 12, '---', 40, 0, 8);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (79, 49, 8, '---', 44, 0, 8);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (80, 50, 10, '---', 50, 0, 8);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (81, 41, 9, '---', 5, 0, 9);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (82, 42, 6, '---', 9, 0, 9);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (83, 43, 3, '---', 13, 0, 9);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (84, 44, 0, '---', 17, 0, 9);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (85, 45, 9, '---', 25, 0, 9);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (86, 46, 9, '---', 30, 0, 9);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (87, 47, 12, '---', 35, 0, 9);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (88, 48, 8, '---', 39, 0, 9);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (89, 49, 12, '---', 45, 0, 9);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (90, 50, 10, '---', 50, 0, 9);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (91, 41, 9, '---', 5, 0, 10);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (92, 42, 9, '---', 10, 0, 10);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (93, 43, 9, '---', 15, 0, 10);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (94, 44, 3, '---', 18, 0, 10);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (95, 45, 3, '---', 23, 0, 10);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (96, 46, 0, '---', 27, 0, 10);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (97, 47, 12, '---', 35, 0, 10);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (98, 48, 12, '---', 40, 0, 10);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (99, 49, 8, '---', 44, 0, 10);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (100, 50, 6, '---', 49, 0, 10);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (101, 41, 6, '---', 4, 0, 11);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (102, 42, 9, '---', 10, 0, 11);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (103, 43, 3, '---', 13, 0, 11);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (104, 44, 9, '---', 20, 0, 11);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (105, 45, 6, '---', 24, 0, 11);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (106, 46, 9, '---', 30, 0, 11);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (107, 47, 12, '---', 35, 0, 11);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (108, 48, 12, '---', 40, 0, 11);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (109, 49, 0, '---', 42, 0, 11);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (110, 50, 0, '---', 47, 0, 11);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (111, 41, 9, '---', 5, 0, 12);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (112, 42, 6, '---', 9, 0, 12);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (113, 43, 3, '---', 13, 0, 12);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (114, 44, 9, '---', 20, 0, 12);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (115, 45, 9, '---', 25, 0, 12);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (116, 46, 9, '---', 30, 0, 12);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (117, 47, 8, '---', 34, 0, 12);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (118, 48, 8, '---', 39, 0, 12);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (119, 49, 4, '---', 43, 0, 12);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (120, 50, 10, '---', 50, 0, 12);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (121, 41, 9, '---', 5, 0, 13);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (122, 42, 9, '---', 10, 0, 13);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (123, 43, 9, '---', 15, 0, 13);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (124, 44, 9, '---', 20, 0, 13);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (125, 45, 9, '---', 25, 0, 13);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (126, 46, 9, '---', 30, 0, 13);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (127, 47, 12, '---', 35, 0, 13);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (128, 48, 12, '---', 40, 0, 13);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (129, 49, 12, '---', 45, 0, 13);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (130, 50, 10, '---', 50, 0, 13);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (131, 51, 9, '---', 5, 0, 14);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (132, 52, 9, '---', 10, 0, 14);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (133, 53, 6, '---', 14, 0, 14);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (134, 54, 6, '---', 19, 0, 14);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (135, 55, 3, '---', 23, 0, 14);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (136, 56, 9, '---', 30, 0, 14);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (137, 57, 8, '---', 34, 0, 14);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (138, 58, 8, '---', 39, 0, 14);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (139, 59, 12, '---', 45, 0, 14);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (140, 60, 10, '---', 50, 0, 14);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (141, 51, 0, '---', 2, 0, 15);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (142, 52, 3, '---', 8, 0, 15);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (143, 53, 3, '---', 13, 0, 15);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (144, 54, 6, '---', 19, 0, 15);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (145, 55, 9, '---', 25, 0, 15);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (146, 56, 9, '---', 30, 0, 15);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (147, 57, 12, '---', 35, 0, 15);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (148, 58, 8, '---', 39, 0, 15);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (149, 59, 4, '---', 43, 0, 15);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (150, 60, 6, '---', 49, 0, 15);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (151, 51, 6, '---', 4, 0, 16);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (152, 52, 6, '---', 9, 0, 16);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (153, 53, 9, '---', 15, 0, 16);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (154, 54, 9, '---', 20, 0, 16);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (155, 55, 9, '---', 25, 0, 16);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (156, 56, 9, '---', 30, 0, 16);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (157, 57, 4, '---', 33, 0, 16);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (158, 58, 4, '---', 38, 0, 16);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (159, 59, 4, '---', 43, 0, 16);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (160, 60, 10, '---', 50, 0, 16);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (161, 51, 6, '---', 4, 0, 17);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (162, 52, 6, '---', 9, 0, 17);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (163, 53, 9, '---', 15, 0, 17);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (164, 54, 9, '---', 20, 0, 17);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (165, 55, 6, '---', 24, 0, 17);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (166, 56, 0, '---', 27, 0, 17);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (167, 57, 4, '---', 33, 0, 17);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (168, 58, 12, '---', 40, 0, 17);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (169, 59, 4, '---', 43, 0, 17);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (170, 60, 10, '---', 50, 0, 17);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (171, 51, 9, '---', 5, 0, 18);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (172, 52, 3, '---', 8, 0, 18);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (173, 53, 9, '---', 15, 0, 18);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (174, 54, 9, '---', 20, 0, 18);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (175, 55, 6, '---', 24, 0, 18);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (176, 56, 3, '---', 28, 0, 18);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (177, 57, 4, '---', 33, 0, 18);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (178, 58, 12, '---', 40, 0, 18);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (179, 59, 12, '---', 45, 0, 18);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (180, 60, 10, '---', 50, 0, 18);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (181, 51, 9, '---', 5, 0, 19);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (182, 52, 9, '---', 10, 0, 19);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (183, 53, 6, '---', 14, 0, 19);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (184, 54, 3, '---', 18, 0, 19);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (185, 55, 9, '---', 25, 0, 19);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (186, 56, 6, '---', 29, 0, 19);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (187, 57, 8, '---', 34, 0, 19);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (188, 58, 8, '---', 39, 0, 19);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (189, 59, 12, '---', 45, 0, 19);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (190, 60, 10, '---', 50, 0, 19);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (191, 51, 9, '---', 5, 0, 20);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (192, 52, 9, '---', 10, 0, 20);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (193, 53, 9, '---', 15, 0, 20);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (194, 54, 6, '---', 19, 0, 20);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (195, 55, 6, '---', 24, 0, 20);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (196, 56, 6, '---', 29, 0, 20);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (197, 57, 4, '---', 33, 0, 20);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (198, 58, 4, '---', 38, 0, 20);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (199, 59, 8, '---', 44, 0, 20);
INSERT INTO evaluationpersonnel (idevaluationpersonnel, iddetailsc, note, observation, idelementreponse, pointmaxindice, idnote) VALUES (200, 60, 10, '---', 50, 0, 20);


--
-- TOC entry 2785 (class 0 OID 399458)
-- Dependencies: 219
-- Data for Name: evaluationresponsabilite; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO evaluationresponsabilite (idevaluationresponsabilite, point, idcritere, pointmax, ratio, idnote) VALUES (1, 360, 1, 400, 90, 1);
INSERT INTO evaluationresponsabilite (idevaluationresponsabilite, point, idcritere, pointmax, ratio, idnote) VALUES (2, 1200, 1, 1200, 100, 2);
INSERT INTO evaluationresponsabilite (idevaluationresponsabilite, point, idcritere, pointmax, ratio, idnote) VALUES (3, 700, 1, 1000, 70, 3);
INSERT INTO evaluationresponsabilite (idevaluationresponsabilite, point, idcritere, pointmax, ratio, idnote) VALUES (4, 500, 1, 500, 100, 4);
INSERT INTO evaluationresponsabilite (idevaluationresponsabilite, point, idcritere, pointmax, ratio, idnote) VALUES (5, 300, 1, 300, 100, 5);
INSERT INTO evaluationresponsabilite (idevaluationresponsabilite, point, idcritere, pointmax, ratio, idnote) VALUES (6, 200, 1, 250, 80, 6);
INSERT INTO evaluationresponsabilite (idevaluationresponsabilite, point, idcritere, pointmax, ratio, idnote) VALUES (7, 40, 1, 40, 100, 7);
INSERT INTO evaluationresponsabilite (idevaluationresponsabilite, point, idcritere, pointmax, ratio, idnote) VALUES (8, 40, 1, 40, 100, 8);
INSERT INTO evaluationresponsabilite (idevaluationresponsabilite, point, idcritere, pointmax, ratio, idnote) VALUES (9, 50, 1, 50, 100, 9);
INSERT INTO evaluationresponsabilite (idevaluationresponsabilite, point, idcritere, pointmax, ratio, idnote) VALUES (10, 45, 1, 50, 90, 10);
INSERT INTO evaluationresponsabilite (idevaluationresponsabilite, point, idcritere, pointmax, ratio, idnote) VALUES (11, 20, 1, 40, 50, 11);
INSERT INTO evaluationresponsabilite (idevaluationresponsabilite, point, idcritere, pointmax, ratio, idnote) VALUES (12, 250, 1, 250, 100, 12);
INSERT INTO evaluationresponsabilite (idevaluationresponsabilite, point, idcritere, pointmax, ratio, idnote) VALUES (13, 40, 1, 40, 100, 13);
INSERT INTO evaluationresponsabilite (idevaluationresponsabilite, point, idcritere, pointmax, ratio, idnote) VALUES (14, 75, 1, 75, 100, 14);
INSERT INTO evaluationresponsabilite (idevaluationresponsabilite, point, idcritere, pointmax, ratio, idnote) VALUES (15, 80, 1, 100, 80, 15);
INSERT INTO evaluationresponsabilite (idevaluationresponsabilite, point, idcritere, pointmax, ratio, idnote) VALUES (16, 0, 1, 0, 0, 16);
INSERT INTO evaluationresponsabilite (idevaluationresponsabilite, point, idcritere, pointmax, ratio, idnote) VALUES (17, 120, 1, 120, 100, 17);
INSERT INTO evaluationresponsabilite (idevaluationresponsabilite, point, idcritere, pointmax, ratio, idnote) VALUES (18, 125, 1, 125, 100, 18);
INSERT INTO evaluationresponsabilite (idevaluationresponsabilite, point, idcritere, pointmax, ratio, idnote) VALUES (19, 0, 1, 0, 0, 19);
INSERT INTO evaluationresponsabilite (idevaluationresponsabilite, point, idcritere, pointmax, ratio, idnote) VALUES (20, 200, 1, 200, 100, 20);


--
-- TOC entry 2788 (class 0 OID 399473)
-- Dependencies: 222
-- Data for Name: evaluationrprimeqltifdept; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO evaluationrprimeqltifdept (idevaluationrprimeqltifdept, score, idservice, idperiode, idsousperiode, idcritere, pourcentage, cible, idstructure) VALUES (1, 60, 3, 1, 1, 5, 50, 120, 3);
INSERT INTO evaluationrprimeqltifdept (idevaluationrprimeqltifdept, score, idservice, idperiode, idsousperiode, idcritere, pourcentage, cible, idstructure) VALUES (2, 60, 4, 1, 1, 5, 50, 120, 3);
INSERT INTO evaluationrprimeqltifdept (idevaluationrprimeqltifdept, score, idservice, idperiode, idsousperiode, idcritere, pourcentage, cible, idstructure) VALUES (3, 70, 5, 1, 1, 5, 70, 100, 3);
INSERT INTO evaluationrprimeqltifdept (idevaluationrprimeqltifdept, score, idservice, idperiode, idsousperiode, idcritere, pourcentage, cible, idstructure) VALUES (4, 90, 2, 1, 1, 5, 80, 120, 3);
INSERT INTO evaluationrprimeqltifdept (idevaluationrprimeqltifdept, score, idservice, idperiode, idsousperiode, idcritere, pourcentage, cible, idstructure) VALUES (5, 57, 1, 1, 15, 5, 55, 120, 5);
INSERT INTO evaluationrprimeqltifdept (idevaluationrprimeqltifdept, score, idservice, idperiode, idsousperiode, idcritere, pourcentage, cible, idstructure) VALUES (6, 70, 2, 1, 15, 5, 61.666666666666664, 120, 5);
INSERT INTO evaluationrprimeqltifdept (idevaluationrprimeqltifdept, score, idservice, idperiode, idsousperiode, idcritere, pourcentage, cible, idstructure) VALUES (7, 75, 6, 1, 15, 5, 60, 120, 5);
INSERT INTO evaluationrprimeqltifdept (idevaluationrprimeqltifdept, score, idservice, idperiode, idsousperiode, idcritere, pourcentage, cible, idstructure) VALUES (8, 51, 1, 1, 15, 5, 49, 120, 6);
INSERT INTO evaluationrprimeqltifdept (idevaluationrprimeqltifdept, score, idservice, idperiode, idsousperiode, idcritere, pourcentage, cible, idstructure) VALUES (9, 45, 2, 1, 15, 5, 45, 120, 6);
INSERT INTO evaluationrprimeqltifdept (idevaluationrprimeqltifdept, score, idservice, idperiode, idsousperiode, idcritere, pourcentage, cible, idstructure) VALUES (10, 51, 6, 1, 15, 5, 49, 120, 6);
INSERT INTO evaluationrprimeqltifdept (idevaluationrprimeqltifdept, score, idservice, idperiode, idsousperiode, idcritere, pourcentage, cible, idstructure) VALUES (11, 60, 2, 1, 16, 5, 50, 120, 6);
INSERT INTO evaluationrprimeqltifdept (idevaluationrprimeqltifdept, score, idservice, idperiode, idsousperiode, idcritere, pourcentage, cible, idstructure) VALUES (12, 33, 1, 1, 16, 5, 25, 120, 6);
INSERT INTO evaluationrprimeqltifdept (idevaluationrprimeqltifdept, score, idservice, idperiode, idsousperiode, idcritere, pourcentage, cible, idstructure) VALUES (13, 45, 3, 2, 4, 5, 35, 120, 4);
INSERT INTO evaluationrprimeqltifdept (idevaluationrprimeqltifdept, score, idservice, idperiode, idsousperiode, idcritere, pourcentage, cible, idstructure) VALUES (14, 40, 4, 2, 4, 5, 62.5, 70, 4);
INSERT INTO evaluationrprimeqltifdept (idevaluationrprimeqltifdept, score, idservice, idperiode, idsousperiode, idcritere, pourcentage, cible, idstructure) VALUES (15, 45, 5, 2, 4, 5, 45, 120, 4);
INSERT INTO evaluationrprimeqltifdept (idevaluationrprimeqltifdept, score, idservice, idperiode, idsousperiode, idcritere, pourcentage, cible, idstructure) VALUES (16, 30, 7, 2, 4, 5, 30, 120, 4);
INSERT INTO evaluationrprimeqltifdept (idevaluationrprimeqltifdept, score, idservice, idperiode, idsousperiode, idcritere, pourcentage, cible, idstructure) VALUES (17, 45, 8, 2, 4, 5, 40, 120, 4);
INSERT INTO evaluationrprimeqltifdept (idevaluationrprimeqltifdept, score, idservice, idperiode, idsousperiode, idcritere, pourcentage, cible, idstructure) VALUES (18, 35, 9, 2, 4, 5, 50, 70, 4);


--
-- TOC entry 2789 (class 0 OID 399478)
-- Dependencies: 223
-- Data for Name: evaluationrprimeqltifpersonnel; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO evaluationrprimeqltifpersonnel (idevaluationrprimeqltifpersonnel, point, idevaluationrprimeqltifdept, idpersonnel, idnote, point_max) VALUES (1, 570, 1, 9, 1, 0);
INSERT INTO evaluationrprimeqltifpersonnel (idevaluationrprimeqltifpersonnel, point, idevaluationrprimeqltifdept, idpersonnel, idnote, point_max) VALUES (2, 715, 5, 15, 2, 0);
INSERT INTO evaluationrprimeqltifpersonnel (idevaluationrprimeqltifpersonnel, point, idevaluationrprimeqltifdept, idpersonnel, idnote, point_max) VALUES (3, 550, 5, 16, 3, 0);
INSERT INTO evaluationrprimeqltifpersonnel (idevaluationrprimeqltifpersonnel, point, idevaluationrprimeqltifdept, idpersonnel, idnote, point_max) VALUES (4, 480, 7, 17, 4, 0);
INSERT INTO evaluationrprimeqltifpersonnel (idevaluationrprimeqltifpersonnel, point, idevaluationrprimeqltifdept, idpersonnel, idnote, point_max) VALUES (5, 600, 7, 18, 5, 0);
INSERT INTO evaluationrprimeqltifpersonnel (idevaluationrprimeqltifpersonnel, point, idevaluationrprimeqltifdept, idpersonnel, idnote, point_max) VALUES (6, 319, 8, 21, 6, 650);
INSERT INTO evaluationrprimeqltifpersonnel (idevaluationrprimeqltifpersonnel, point, idevaluationrprimeqltifdept, idpersonnel, idnote, point_max) VALUES (7, 245, 10, 22, 7, 500);
INSERT INTO evaluationrprimeqltifpersonnel (idevaluationrprimeqltifpersonnel, point, idevaluationrprimeqltifdept, idpersonnel, idnote, point_max) VALUES (8, 245, 10, 23, 8, 500);
INSERT INTO evaluationrprimeqltifpersonnel (idevaluationrprimeqltifpersonnel, point, idevaluationrprimeqltifdept, idpersonnel, idnote, point_max) VALUES (9, 293, 9, 24, 9, 650);
INSERT INTO evaluationrprimeqltifpersonnel (idevaluationrprimeqltifpersonnel, point, idevaluationrprimeqltifdept, idpersonnel, idnote, point_max) VALUES (10, 196, 8, 25, 10, 400);
INSERT INTO evaluationrprimeqltifpersonnel (idevaluationrprimeqltifpersonnel, point, idevaluationrprimeqltifdept, idpersonnel, idnote, point_max) VALUES (11, 245, 8, 26, 11, 500);
INSERT INTO evaluationrprimeqltifpersonnel (idevaluationrprimeqltifpersonnel, point, idevaluationrprimeqltifdept, idpersonnel, idnote, point_max) VALUES (12, 196, 10, 27, 12, 400);
INSERT INTO evaluationrprimeqltifpersonnel (idevaluationrprimeqltifpersonnel, point, idevaluationrprimeqltifdept, idpersonnel, idnote, point_max) VALUES (13, 180, 9, 28, 13, 400);
INSERT INTO evaluationrprimeqltifpersonnel (idevaluationrprimeqltifpersonnel, point, idevaluationrprimeqltifdept, idpersonnel, idnote, point_max) VALUES (14, 50, 16, 35, 14, 166);
INSERT INTO evaluationrprimeqltifpersonnel (idevaluationrprimeqltifpersonnel, point, idevaluationrprimeqltifdept, idpersonnel, idnote, point_max) VALUES (15, 117, 18, 34, 15, 234);
INSERT INTO evaluationrprimeqltifpersonnel (idevaluationrprimeqltifpersonnel, point, idevaluationrprimeqltifdept, idpersonnel, idnote, point_max) VALUES (16, 69, 15, 33, 16, 153);
INSERT INTO evaluationrprimeqltifpersonnel (idevaluationrprimeqltifpersonnel, point, idevaluationrprimeqltifdept, idpersonnel, idnote, point_max) VALUES (17, 238, 14, 32, 17, 380);
INSERT INTO evaluationrprimeqltifpersonnel (idevaluationrprimeqltifpersonnel, point, idevaluationrprimeqltifdept, idpersonnel, idnote, point_max) VALUES (18, 95, 16, 31, 18, 316);
INSERT INTO evaluationrprimeqltifpersonnel (idevaluationrprimeqltifpersonnel, point, idevaluationrprimeqltifdept, idpersonnel, idnote, point_max) VALUES (19, 115, 15, 29, 19, 254);
INSERT INTO evaluationrprimeqltifpersonnel (idevaluationrprimeqltifpersonnel, point, idevaluationrprimeqltifdept, idpersonnel, idnote, point_max) VALUES (20, 152, 17, 30, 20, 380);


--
-- TOC entry 2787 (class 0 OID 399468)
-- Dependencies: 221
-- Data for Name: evaluationrqntifdept; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (1, 10, 2, 20, 22, 9, 1);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (2, 20, 3, 15, 23, 9, 1);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (3, 20, 4, 20, 24, 9, 1);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (4, 10, 10, 100, 25, 9, 1);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (5, 20, 5, 25, 39, 15, 2);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (6, 15, 3, 20, 40, 15, 2);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (7, 20, 5, 25, 41, 15, 2);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (8, 20, 3, 15, 39, 16, 3);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (9, 15, 6, 40, 40, 16, 3);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (10, 20, 5, 25, 41, 16, 3);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (11, 30, 10, 33.333333333333329, 42, 17, 4);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (12, 30, 10, 33.333333333333329, 43, 17, 4);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (13, 30, 0, 0, 42, 18, 5);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (14, 30, 0, 0, 43, 18, 5);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (15, 20, 5, 25, 44, 21, 6);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (16, 15, 5, 33.333333333333329, 45, 21, 6);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (17, 30, 8, 26.666666666666668, 46, 21, 6);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (18, 15, 5, 33.333333333333329, 53, 22, 7);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (19, 20, 5, 25, 54, 22, 7);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (20, 30, 3, 10, 55, 22, 7);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (21, 15, 3, 20, 53, 23, 8);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (22, 20, 5, 25, 54, 23, 8);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (23, 30, 6, 20, 55, 23, 8);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (24, 30, 5, 16.666666666666664, 50, 24, 9);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (25, 35, 7, 20, 51, 24, 9);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (26, 20, 5, 25, 52, 24, 9);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (27, 20, 5, 25, 44, 25, 10);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (28, 15, 5, 33.333333333333329, 45, 25, 10);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (29, 30, 10, 33.333333333333329, 46, 25, 10);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (30, 20, 3, 15, 44, 26, 11);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (31, 15, 3, 20, 45, 26, 11);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (32, 30, 3, 10, 46, 26, 11);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (33, 15, 5, 33.333333333333329, 53, 27, 12);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (34, 20, 10, 50, 54, 27, 12);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (35, 30, 10, 33.333333333333329, 55, 27, 12);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (36, 30, 5, 16.666666666666664, 50, 28, 13);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (37, 35, 20, 57.142857142857139, 51, 28, 13);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (38, 20, 10, 50, 52, 28, 13);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (39, 30, 6, 20, 74, 35, 14);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (40, 30, 6, 20, 75, 35, 14);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (41, 15, 3, 20, 80, 34, 15);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (42, 15, 5, 33.333333333333329, 81, 34, 15);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (43, 15, 4, 26.666666666666668, 82, 34, 15);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (44, 50, 5, 10, 72, 33, 16);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (45, 10, 5, 50, 73, 33, 16);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (46, 30, 10, 33.333333333333329, 69, 32, 17);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (47, 25, 10, 40, 70, 32, 17);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (48, 50, 15, 30, 71, 32, 17);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (49, 30, 5, 16.666666666666664, 74, 31, 18);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (50, 30, 5, 16.666666666666664, 75, 31, 18);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (51, 50, 5, 10, 72, 29, 19);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (52, 10, 5, 50, 73, 29, 19);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (53, 10, 3, 30, 76, 30, 20);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (54, 30, 5, 16.666666666666664, 77, 30, 20);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (55, 20, 5, 25, 78, 30, 20);
INSERT INTO evaluationrqntifdept (idevaluationrqntifdept, cible, realisation, ratio, idcible, idpersonnel, idnote) VALUES (56, 40, 5, 12.5, 79, 30, 20);


--
-- TOC entry 2780 (class 0 OID 374893)
-- Dependencies: 214
-- Data for Name: indicateur; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO indicateur (idindicateur, nom, code) VALUES (1, 'Indicateur 1', '001');
INSERT INTO indicateur (idindicateur, nom, code) VALUES (2, 'Indicateur 2', '002');
INSERT INTO indicateur (idindicateur, nom, code) VALUES (3, 'Indicateur 3', '003');
INSERT INTO indicateur (idindicateur, nom, code) VALUES (4, 'Indicateur 4', '004');
INSERT INTO indicateur (idindicateur, nom, code) VALUES (5, 'Indicateur 5', '005');


--
-- TOC entry 2800 (class 0 OID 459190)
-- Dependencies: 234
-- Data for Name: indicateurqteservice; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (1, 2, 4, 1);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (2, 2, 4, 2);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (3, 2, 4, 3);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (4, 2, 4, 4);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (5, 2, 3, 1);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (6, 2, 3, 2);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (7, 2, 5, 3);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (8, 2, 5, 4);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (9, 3, 4, 1);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (10, 3, 4, 2);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (11, 3, 4, 3);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (12, 3, 3, 3);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (13, 3, 3, 4);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (14, 3, 3, 5);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (15, 3, 5, 1);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (16, 3, 5, 3);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (17, 3, 5, 5);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (18, 5, 1, 1);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (19, 5, 1, 3);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (20, 5, 1, 5);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (21, 5, 2, 1);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (22, 5, 2, 2);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (23, 5, 2, 3);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (24, 5, 2, 4);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (25, 5, 2, 5);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (26, 5, 6, 2);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (27, 5, 6, 4);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (28, 6, 1, 1);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (29, 6, 1, 3);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (30, 6, 1, 5);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (31, 6, 2, 1);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (32, 6, 2, 3);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (33, 6, 2, 4);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (34, 6, 6, 1);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (35, 6, 6, 2);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (36, 6, 6, 3);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (37, 4, 4, 1);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (38, 4, 4, 3);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (39, 4, 4, 5);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (40, 4, 7, 2);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (41, 4, 7, 4);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (42, 4, 8, 1);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (43, 4, 8, 2);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (44, 4, 8, 4);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (45, 4, 8, 5);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (46, 4, 9, 1);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (47, 4, 9, 3);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (48, 4, 9, 5);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (49, 4, 3, 1);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (50, 4, 3, 2);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (51, 4, 5, 2);
INSERT INTO indicateurqteservice (idindicateurqteservice, idstructure, idservice, idindicateur) VALUES (52, 4, 5, 4);


--
-- TOC entry 2753 (class 0 OID 285964)
-- Dependencies: 187
-- Data for Name: institution; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO institution (idinstitution, nom, code, choixstrategique, etat, sigle, chapitre) VALUES (1, 'MINISTERE DE LA SANTE', 'MINSANTE', '--', 'Actif', 'MINSANTE', '40');


--
-- TOC entry 2793 (class 0 OID 408400)
-- Dependencies: 227
-- Data for Name: lignepenalitedept; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (1, 5, 0, true, 1, 1);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (2, 10, 0, true, 2, 1);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (3, 0, 0, false, 3, 1);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (4, 5, 0, true, 1, 2);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (5, 0, 0, false, 2, 2);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (6, 0, 0, false, 3, 2);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (7, 5, 0, true, 1, 3);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (8, 0, 0, false, 2, 3);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (9, 0, 0, false, 3, 3);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (10, 5, 0, true, 1, 4);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (11, 0, 0, false, 2, 4);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (12, 0, 0, false, 3, 4);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (13, 0, 0, false, 1, 5);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (14, 10, 0, true, 2, 5);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (15, 0, 0, false, 3, 5);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (16, 5, 0, true, 1, 6);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (17, 0, 0, false, 2, 6);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (18, 0, 0, false, 3, 6);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (19, 0, 0, false, 1, 7);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (20, 10, 0, true, 2, 7);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (21, 0, 0, false, 3, 7);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (22, 5, 0, true, 1, 8);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (23, 10, 0, true, 2, 8);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (24, 0, 0, false, 3, 8);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (25, 5, 0, true, 1, 9);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (26, 0, 0, false, 2, 9);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (27, 0, 0, false, 7, 9);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (28, 0, 0, false, 3, 9);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (29, 0, 0, false, 1, 10);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (30, 5, 0, true, 7, 10);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (31, 0, 0, false, 2, 11);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (32, 5, 0, true, 7, 11);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (33, 5, 0, true, 1, 12);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (34, 0, 0, false, 2, 12);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (35, 0, 0, false, 3, 12);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (36, 0, 0, false, 7, 12);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (37, 5, 0, true, 1, 13);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (38, 0, 0, false, 2, 13);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (39, 0, 0, false, 3, 13);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (40, 0, 0, false, 7, 13);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (41, 5, 0, true, 1, 14);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (42, 0, 0, false, 2, 14);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (43, 0, 0, false, 3, 14);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (44, 0, 0, false, 7, 14);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (45, 5, 0, true, 1, 15);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (46, 0, 0, false, 2, 15);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (47, 0, 0, false, 3, 15);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (48, 0, 0, false, 7, 15);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (49, 5, 0, true, 1, 16);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (50, 0, 0, false, 2, 16);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (51, 0, 0, false, 3, 16);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (52, 0, 0, false, 7, 16);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (53, 0, 0, false, 1, 17);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (54, 10, 0, true, 2, 17);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (55, 0, 0, false, 3, 17);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (56, 0, 0, false, 7, 17);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (57, 5, 0, true, 1, 18);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (58, 0, 0, false, 2, 18);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (59, 0, 0, false, 3, 18);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (60, 0, 0, false, 7, 18);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (61, 0, 0, false, 1, 19);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (62, 0, 0, false, 2, 19);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (63, 0, 0, false, 3, 19);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (64, 0, 0, false, 7, 19);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (65, 5, 0, true, 1, 20);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (66, 0, 0, false, 2, 20);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (67, 0, 0, false, 3, 20);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (68, 0, 0, false, 7, 20);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (69, 0, 0, false, 1, 21);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (70, 0, 0, false, 2, 21);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (71, 0, 0, false, 3, 21);
INSERT INTO lignepenalitedept (idlignepenalitedept, valeur, pourcentage, etat, idpenalite, idevaluationpenalitedept) VALUES (72, 0, 0, false, 7, 21);


--
-- TOC entry 2796 (class 0 OID 416592)
-- Dependencies: 230
-- Data for Name: lignepenalitepersonnel; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (1, 5, 5, 1, 6, true);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (2, 0, 5, 1, 4, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (3, 0, 10, 1, 5, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (4, 5, 5, 2, 4, true);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (5, 0, 10, 2, 5, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (6, 0, 5, 2, 6, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (7, 5, 5, 3, 4, true);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (8, 0, 10, 3, 5, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (9, 0, 5, 3, 6, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (10, 0, 5, 4, 4, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (11, 0, 10, 4, 5, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (12, 0, 5, 4, 6, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (13, 5, 5, 5, 4, true);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (14, 0, 10, 5, 5, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (15, 0, 5, 5, 6, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (16, 5, 5, 6, 4, true);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (18, 0, 5, 6, 6, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (17, 10, 10, 6, 5, true);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (19, 0, 5, 7, 4, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (20, 0, 10, 7, 5, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (21, 0, 5, 7, 6, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (22, 5, 5, 8, 4, true);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (23, 0, 10, 8, 5, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (24, 0, 5, 8, 6, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (25, 5, 5, 9, 4, true);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (26, 0, 10, 9, 5, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (27, 0, 5, 9, 6, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (28, 5, 5, 10, 4, true);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (29, 0, 10, 10, 5, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (30, 0, 5, 10, 6, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (31, 0, 5, 11, 4, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (32, 10, 10, 11, 5, true);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (33, 0, 5, 11, 6, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (34, 5, 5, 12, 4, true);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (35, 0, 10, 12, 5, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (36, 0, 5, 12, 6, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (37, 0, 5, 13, 4, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (38, 0, 10, 13, 5, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (39, 0, 5, 13, 6, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (40, 5, 5, 14, 4, true);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (41, 0, 10, 14, 5, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (42, 0, 5, 14, 6, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (43, 5, 5, 15, 4, true);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (44, 0, 10, 15, 5, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (45, 0, 5, 15, 6, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (46, 5, 5, 16, 4, true);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (47, 0, 10, 16, 5, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (48, 0, 5, 16, 6, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (49, 5, 5, 17, 4, true);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (50, 0, 10, 17, 5, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (51, 0, 5, 17, 6, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (52, 5, 5, 18, 4, true);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (53, 0, 10, 18, 5, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (54, 0, 5, 18, 6, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (55, 0, 5, 19, 4, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (56, 0, 10, 19, 5, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (57, 0, 5, 19, 6, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (58, 5, 5, 20, 4, true);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (59, 0, 10, 20, 5, false);
INSERT INTO lignepenalitepersonnel (idlignepenalitepersonnel, valeur, cible, idevaluationpenalitepersonnel, idpenalite, etat) VALUES (60, 0, 5, 20, 6, false);


--
-- TOC entry 2794 (class 0 OID 408405)
-- Dependencies: 228
-- Data for Name: ligneprimequalitedept; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (1, 50, 25, 50, 4, 1);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (2, 50, 25, 50, 5, 1);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (3, 20, 10, 50, 6, 1);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (4, 50, 25, 50, 1, 2);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (5, 50, 25, 50, 2, 2);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (6, 20, 10, 50, 3, 2);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (7, 50, 35, 70, 9, 3);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (8, 20, 10, 50, 10, 3);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (9, 50, 50, 100, 11, 3);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (10, 50, 20, 40, 12, 3);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (11, 50, 30, 60, 15, 4);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (12, 50, 40, 80, 16, 4);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (13, 20, 20, 100, 17, 4);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (14, 50, 15, 30, 21, 5);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (15, 50, 25, 50, 22, 5);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (16, 20, 17, 85, 23, 5);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (17, 50, 30, 60, 24, 6);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (18, 50, 25, 50, 25, 6);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (19, 20, 15, 75, 26, 6);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (20, 50, 50, 100, 27, 7);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (21, 50, 15, 30, 28, 7);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (22, 20, 10, 50, 29, 7);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (23, 50, 15, 30, 30, 8);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (24, 50, 21, 42, 31, 8);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (25, 20, 15, 75, 32, 8);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (26, 50, 15, 30, 33, 9);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (27, 50, 15, 30, 34, 9);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (28, 20, 15, 75, 35, 9);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (29, 50, 21, 42, 36, 10);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (30, 50, 15, 30, 37, 10);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (31, 20, 15, 75, 38, 10);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (32, 50, 30, 60, 33, 11);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (33, 50, 20, 40, 34, 11);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (34, 20, 10, 50, 35, 11);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (35, 50, 10, 20, 30, 12);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (36, 50, 20, 40, 31, 12);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (37, 20, 3, 15, 32, 12);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (38, 50, 20, 40, 49, 13);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (39, 50, 20, 40, 50, 13);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (40, 20, 5, 25, 51, 13);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (41, 50, 25, 50, 39, 14);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (42, 20, 15, 75, 40, 14);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (43, 50, 15, 30, 52, 15);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (44, 50, 15, 30, 53, 15);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (45, 20, 15, 75, 54, 15);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (46, 50, 10, 20, 41, 16);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (47, 50, 10, 20, 42, 16);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (48, 20, 10, 50, 43, 16);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (49, 50, 15, 30, 44, 17);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (50, 50, 20, 40, 45, 17);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (51, 20, 10, 50, 46, 17);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (52, 50, 25, 50, 47, 18);
INSERT INTO ligneprimequalitedept (idligneprimequalitedept, valeurcible, valeurrealisee, ratio, idsouscritereservice, idevaluationrprimeqltifdept) VALUES (53, 20, 10, 50, 48, 18);


--
-- TOC entry 2754 (class 0 OID 285973)
-- Dependencies: 188
-- Data for Name: menu; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO menu (idmenu, nom, ressource) VALUES (1, 'Super Admin', '--');
INSERT INTO menu (idmenu, nom, ressource) VALUES (3, 'Gestion des droits d''àccès', '/OutilIndice-war/securite/privilege/privilege.html');
INSERT INTO menu (idmenu, nom, ressource) VALUES (2, 'Gestion des utilisateurs', '/OutilIndice-war/securite/utilisateur/utilisateur.html;/OutilIndice-war/securite/activercompte/activercompte.html;/OutilIndice-war/securite/desactivercompte/desactivercompte.html;/OutilIndice-war/securite/reset-compte/reset-compte.html');
INSERT INTO menu (idmenu, nom, ressource) VALUES (4, 'Visualisation du mouchard', '/OutilIndice-war/securite/mouchard/mouchard.html');
INSERT INTO menu (idmenu, nom, ressource) VALUES (5, 'Gestion des type de structure', '/OutilIndice-war/parametrage/typestructure/typestructure.html');
INSERT INTO menu (idmenu, nom, ressource) VALUES (6, 'Gestion des status de structure', '/OutilIndice-war/parametrage/statutstructure/statutstructure.html');
INSERT INTO menu (idmenu, nom, ressource) VALUES (7, 'Gestion des institutions', '/OutilIndice-war/parametrage/institution/institution.html');
INSERT INTO menu (idmenu, nom, ressource) VALUES (9, 'Gestion des structures', '/OutilIndice-war/parametrage/structure/structure.html');
INSERT INTO menu (idmenu, nom, ressource) VALUES (10, 'Gestion des services', '/OutilIndice-war/parametrage/service/service.html');


--
-- TOC entry 2755 (class 0 OID 285982)
-- Dependencies: 189
-- Data for Name: mouchard; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (1, NULL, 'Enregistrement de l''utilisateur : Kenne Gervais', '2020-07-17', '2020-07-17');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (2, NULL, 'Suppresion de l''utilisateur : Pouamoun Abdel', '2020-07-17', '2020-07-17');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (3, NULL, 'Enregistrement de l''utilisateur : Pouamoun Abdel', '2020-07-17', '2020-07-17');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (4, NULL, 'Suppresion de l''utilisateur : Pouamoun Abdel', '2020-07-17', '2020-07-17');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (5, NULL, 'Enregistrement de l''utilisateur : Pouamoun Abdel', '2020-07-17', '2020-07-17');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (6, NULL, 'ATTRIBUTION DU PRIVILEGE : SUPER ADMINISTRATEUR à l''utilisateur -> Kenne Gervais', '2020-07-17', '2020-07-17');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (7, NULL, 'RETRAIT DU PRIVILEGE -> Super Admin à l''utilisateur -> Kenne Gervais', '2020-07-17', '2020-07-17');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (8, NULL, 'ATTRIBUTION DU PRIVILEGE : SUPER ADMINISTRATEUR à l''utilisateur -> Kenne Gervais', '2020-07-17', '2020-07-17');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (9, NULL, 'RETRAIT DU PRIVILEGE -> Super Admin à l''utilisateur -> Kenne Gervais', '2020-07-17', '2020-07-17');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (10, NULL, 'ATTRIBUTION DU PRIVILEGE : SUPER ADMINISTRATEUR à l''utilisateur -> Pouamoun Abdel', '2020-07-17', '2020-07-17');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (11, 1, 'Enregistrement des depense de la structure : Hopital de District de Byem - Assi', '2020-08-17', '2020-08-17');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (12, 1, 'Déconnexion', '2020-08-24', '2020-08-24');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (13, 1, 'ATTRIBUTION DU PRIVILEGE -> Gestion des droits d''àccès à l''utilisateur -> Mandela Arouna Nelson', '2020-08-24', '2020-08-24');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (14, 1, 'ATTRIBUTION DU PRIVILEGE -> Visualisation du mouchard à l''utilisateur -> Mandela Arouna Nelson', '2020-08-24', '2020-08-24');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (15, 1, 'Enregistrement des depense de la structure : Hopital de District de Cité - Verte', '2020-11-23', '2020-11-23');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (16, 1, 'Enregistrement des depense de la structure : Hopital de District de Cité - Verte', '2020-11-23', '2020-11-23');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (17, 1, 'Enregistrement des depense de la structure : Hopital de District de Cité - Verte', '2020-11-26', '2020-11-26');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (18, 1, 'Déconnexion', '2020-12-21', '2020-12-21');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (19, 1, 'Déconnexion', '2021-01-12', '2021-01-12');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (20, 1, 'Déconnexion', '2021-01-25', '2021-01-25');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (21, 1, 'Déconnexion', '2021-02-03', '2021-02-03');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (22, 1, 'Déconnexion', '2021-03-05', '2021-03-05');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (23, 1, 'Déconnexion', '2021-03-05', '2021-03-05');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (24, 1, 'Déconnexion', '2021-03-05', '2021-03-05');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (25, 1, 'Enregistrement des depense de la structure : Hopital général de yaoundé', '2021-03-10', '2021-03-10');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (26, 1, 'Enregistrement des depense de la structure : Hopital général de yaoundé', '2021-03-10', '2021-03-10');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (27, 1, 'Déconnexion', '2021-03-10', '2021-03-10');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (28, 1, 'Déconnexion', '2021-03-11', '2021-03-11');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (29, 1, 'Déconnexion', '2021-03-11', '2021-03-11');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (30, 1, 'Déconnexion', '2021-03-11', '2021-03-11');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (31, 1, 'Enregistrement des depense de la structure : Hopital central de yaoundé', '2021-03-11', '2021-03-11');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (32, 1, 'Enregistrement des depense de la structure : Hopital général de yaoundé', '2021-03-25', '2021-03-25');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (33, 1, 'Enregistrement des depense de la structure : Hopital central de yaoundé', '2021-03-30', '2021-03-30');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (34, 1, 'Enregistrement des depense de la structure : DRSP CENTRE', '2021-03-30', '2021-03-30');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (35, 1, 'Enregistrement des depense de la structure : DRSP LITTORAL', '2021-04-13', '2021-04-13');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (36, 1, 'Enregistrement des depense de la structure : DRSP LITTORAL', '2021-05-04', '2021-05-04');
INSERT INTO mouchard (idmouchard, idutilisateur, action, date, heure) VALUES (37, 1, 'Enregistrement des depense de la structure : Hopital de district de byem - assi', '2021-06-25', '2021-06-25');


--
-- TOC entry 2756 (class 0 OID 285989)
-- Dependencies: 190
-- Data for Name: note; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO note (idnote, idsousperiode, idperiode, pointpindiv, idpersonnel, penalitedepartement, pointpenalitedepartement, penalitepersonnel, pointpenalitepersonnel, pointmaxrqntif, poucentagerqntif, pointrqntif, incitationpositif, incitationnegatif, totalpoint, pointheuresupp, incitationnhp, pointresponsabilite, pointpratiquep, pointrqltifdept, pointbonusrdept, scorepindiv, pointmaxprqltif, scoreprqltif, pointmax_brd, scorebrd, pointmaxpi, notepi, etat) VALUES (1, 1, 1, 786.60000000000002, 9, 5, 155.22999999999999, 0, 155.22999999999999, 0, 31.666666666666664, 361, 3104.5999999999999, 327.56, 2777.04, 56.999999999999993, 17.099999999999998, 360, 400, 570, 570, 69, 0, 0, 0, 0, 0, 0, false);
INSERT INTO note (idnote, idsousperiode, idperiode, pointpindiv, idpersonnel, penalitedepartement, pointpenalitedepartement, penalitepersonnel, pointpenalitepersonnel, pointmaxrqntif, poucentagerqntif, pointrqntif, incitationpositif, incitationnegatif, totalpoint, pointheuresupp, incitationnhp, pointresponsabilite, pointpratiquep, pointrqltifdept, pointbonusrdept, scorepindiv, pointmaxprqltif, scoreprqltif, pointmax_brd, scorebrd, pointmaxpi, notepi, etat) VALUES (2, 15, 1, 910, 15, 5, 470.23863636363637, 0, 470.23863636363637, 0, 23.636363636363637, 307.27272727272725, 9404.7727272727279, 1028.2272727272727, 8376.5454545454559, 292.5, 87.75, 1200, 5200, 715, 780, 70, 0, 0, 0, 0, 0, 0, false);
INSERT INTO note (idnote, idsousperiode, idperiode, pointpindiv, idpersonnel, penalitedepartement, pointpenalitedepartement, penalitepersonnel, pointpenalitepersonnel, pointmaxrqntif, poucentagerqntif, pointrqntif, incitationpositif, incitationnegatif, totalpoint, pointheuresupp, incitationnhp, pointresponsabilite, pointpratiquep, pointrqltifdept, pointbonusrdept, scorepindiv, pointmaxprqltif, scoreprqltif, pointmax_brd, scorebrd, pointmaxpi, notepi, etat) VALUES (3, 15, 1, 720, 16, 5, 309.35227272727275, 0, 309.35227272727275, 0, 25.454545454545453, 254.54545454545453, 6187.045454545455, 686.2045454545455, 5500.8409090909099, 162.5, 67.5, 700, 3200, 550, 600, 72, 0, 0, 0, 0, 0, 0, false);
INSERT INTO note (idnote, idsousperiode, idperiode, pointpindiv, idpersonnel, penalitedepartement, pointpenalitedepartement, penalitepersonnel, pointpenalitepersonnel, pointmaxrqntif, poucentagerqntif, pointrqntif, incitationpositif, incitationnegatif, totalpoint, pointheuresupp, incitationnhp, pointresponsabilite, pointpratiquep, pointrqltifdept, pointbonusrdept, scorepindiv, pointmaxprqltif, scoreprqltif, pointmax_brd, scorebrd, pointmaxpi, notepi, etat) VALUES (4, 15, 1, 800, 17, 5, 253.83333333333329, 0, 0, 0, 33.333333333333329, 266.66666666666663, 5076.6666666666661, 327.83333333333326, 4748.833333333333, 230, 74, 500, 2000, 480, 800, 100, 0, 0, 0, 0, 0, 0, false);
INSERT INTO note (idnote, idsousperiode, idperiode, pointpindiv, idpersonnel, penalitedepartement, pointpenalitedepartement, penalitepersonnel, pointpenalitepersonnel, pointmaxrqntif, poucentagerqntif, pointrqntif, incitationpositif, incitationnegatif, totalpoint, pointheuresupp, incitationnhp, pointresponsabilite, pointpratiquep, pointrqltifdept, pointbonusrdept, scorepindiv, pointmaxprqltif, scoreprqltif, pointmax_brd, scorebrd, pointmaxpi, notepi, etat) VALUES (5, 15, 1, 569.99999999999989, 18, 5, 302.875, 0, 302.875, 0, 0, 0, 6057.5, 673.25, 5384.25, 387.5, 67.5, 300, 3200, 600, 1000, 56.999999999999993, 0, 0, 0, 0, 0, 0, false);
INSERT INTO note (idnote, idsousperiode, idperiode, pointpindiv, idpersonnel, penalitedepartement, pointpenalitedepartement, penalitepersonnel, pointpenalitepersonnel, pointmaxrqntif, poucentagerqntif, pointrqntif, incitationpositif, incitationnegatif, totalpoint, pointheuresupp, incitationnhp, pointresponsabilite, pointpratiquep, pointrqltifdept, pointbonusrdept, scorepindiv, pointmaxprqltif, scoreprqltif, pointmax_brd, scorebrd, pointmaxpi, notepi, etat) VALUES (19, 4, 2, 123, 29, 5, 31, 0, 0, 153, 16.666666666666664, 26, 608, 47, 561, 31, 16, 0, 160, 115, 153, 80, 254, 45, 153, 100, 153, 80, true);
INSERT INTO note (idnote, idsousperiode, idperiode, pointpindiv, idpersonnel, penalitedepartement, pointpenalitedepartement, penalitepersonnel, pointpenalitepersonnel, pointmaxrqntif, poucentagerqntif, pointrqntif, incitationpositif, incitationnegatif, totalpoint, pointheuresupp, incitationnhp, pointresponsabilite, pointpratiquep, pointrqltifdept, pointbonusrdept, scorepindiv, pointmaxprqltif, scoreprqltif, pointmax_brd, scorebrd, pointmaxpi, notepi, etat) VALUES (6, 15, 1, 434, 21, 5, 89, 15, 267, 434, 27.692307692307693, 121, 1774, 369, 1405, 39, 13, 200, 400, 319, 261, 100, 650, 49, 434, 60, 434, 100, true);
INSERT INTO note (idnote, idsousperiode, idperiode, pointpindiv, idpersonnel, penalitedepartement, pointpenalitedepartement, penalitepersonnel, pointpenalitepersonnel, pointmaxrqntif, poucentagerqntif, pointrqntif, incitationpositif, incitationnegatif, totalpoint, pointheuresupp, incitationnhp, pointresponsabilite, pointpratiquep, pointrqltifdept, pointbonusrdept, scorepindiv, pointmaxprqltif, scoreprqltif, pointmax_brd, scorebrd, pointmaxpi, notepi, etat) VALUES (7, 15, 1, 224, 22, 5, 56, 0, 0, 334, 20, 67, 1103, 56, 1047, 40, 0, 40, 320, 245, 167, 67, 500, 49, 334, 50, 334, 67, true);
INSERT INTO note (idnote, idsousperiode, idperiode, pointpindiv, idpersonnel, penalitedepartement, pointpenalitedepartement, penalitepersonnel, pointpenalitepersonnel, pointmaxrqntif, poucentagerqntif, pointrqntif, incitationpositif, incitationnegatif, totalpoint, pointheuresupp, incitationnhp, pointresponsabilite, pointpratiquep, pointrqltifdept, pointbonusrdept, scorepindiv, pointmaxprqltif, scoreprqltif, pointmax_brd, scorebrd, pointmaxpi, notepi, etat) VALUES (8, 15, 1, 231, 23, 5, 57, 5, 57, 334, 21.53846153846154, 72, 1130, 119, 1011, 55, 5, 40, 320, 245, 167, 69, 500, 49, 334, 50, 334, 69, true);
INSERT INTO note (idnote, idsousperiode, idperiode, pointpindiv, idpersonnel, penalitedepartement, pointpenalitedepartement, penalitepersonnel, pointpenalitepersonnel, pointmaxrqntif, poucentagerqntif, pointrqntif, incitationpositif, incitationnegatif, totalpoint, pointheuresupp, incitationnhp, pointresponsabilite, pointpratiquep, pointrqltifdept, pointbonusrdept, scorepindiv, pointmaxprqltif, scoreprqltif, pointmax_brd, scorebrd, pointmaxpi, notepi, etat) VALUES (10, 15, 1, 190, 25, 5, 51, 5, 51, 267, 30.76923076923077, 83, 1003, 106, 897, 28, 4, 45, 300, 196, 161, 71, 400, 49, 267, 60, 267, 71, true);
INSERT INTO note (idnote, idsousperiode, idperiode, pointpindiv, idpersonnel, penalitedepartement, pointpenalitedepartement, penalitepersonnel, pointpenalitepersonnel, pointmaxrqntif, poucentagerqntif, pointrqntif, incitationpositif, incitationnegatif, totalpoint, pointheuresupp, incitationnhp, pointresponsabilite, pointpratiquep, pointrqltifdept, pointbonusrdept, scorepindiv, pointmaxprqltif, scoreprqltif, pointmax_brd, scorebrd, pointmaxpi, notepi, etat) VALUES (11, 15, 1, 221, 26, 5, 57, 10, 114, 334, 13.846153846153847, 47, 1139, 171, 968, 5, 0, 20, 400, 245, 201, 66, 500, 49, 334, 60, 334, 66, true);
INSERT INTO note (idnote, idsousperiode, idperiode, pointpindiv, idpersonnel, penalitedepartement, pointpenalitedepartement, penalitepersonnel, pointpenalitepersonnel, pointmaxrqntif, poucentagerqntif, pointrqntif, incitationpositif, incitationnegatif, totalpoint, pointheuresupp, incitationnhp, pointresponsabilite, pointpratiquep, pointrqltifdept, pointbonusrdept, scorepindiv, pointmaxprqltif, scoreprqltif, pointmax_brd, scorebrd, pointmaxpi, notepi, etat) VALUES (12, 15, 1, 201, 27, 5, 60, 5, 60, 267, 38.461538461538467, 103, 1184, 136, 1048, 0, 16, 250, 300, 196, 134, 75, 400, 49, 267, 50, 267, 75, true);
INSERT INTO note (idnote, idsousperiode, idperiode, pointpindiv, idpersonnel, penalitedepartement, pointpenalitedepartement, penalitepersonnel, pointpenalitepersonnel, pointmaxrqntif, poucentagerqntif, pointrqntif, incitationpositif, incitationnegatif, totalpoint, pointheuresupp, incitationnhp, pointresponsabilite, pointpratiquep, pointrqltifdept, pointbonusrdept, scorepindiv, pointmaxprqltif, scoreprqltif, pointmax_brd, scorebrd, pointmaxpi, notepi, etat) VALUES (13, 15, 1, 267, 28, 5, 55, 0, 0, 267, 41.17647058823529, 110, 1099, 55, 1044, 24, 0, 40, 300, 180, 178, 100, 400, 45, 267, 66.666666666666657, 267, 100, true);
INSERT INTO note (idnote, idsousperiode, idperiode, pointpindiv, idpersonnel, penalitedepartement, pointpenalitedepartement, penalitepersonnel, pointpenalitepersonnel, pointmaxrqntif, poucentagerqntif, pointrqntif, incitationpositif, incitationnegatif, totalpoint, pointheuresupp, incitationnhp, pointresponsabilite, pointpratiquep, pointrqltifdept, pointbonusrdept, scorepindiv, pointmaxprqltif, scoreprqltif, pointmax_brd, scorebrd, pointmaxpi, notepi, etat) VALUES (9, 15, 1, 339, 24, 5, 80, 5, 80, 434, 20, 87, 1585, 160, 1425, 26, 0, 50, 500, 293, 290, 78, 650, 45, 434, 66.666666666666657, 434, 78, true);
INSERT INTO note (idnote, idsousperiode, idperiode, pointpindiv, idpersonnel, penalitedepartement, pointpenalitedepartement, penalitepersonnel, pointpenalitepersonnel, pointmaxrqntif, poucentagerqntif, pointrqntif, incitationpositif, incitationnegatif, totalpoint, pointheuresupp, incitationnhp, pointresponsabilite, pointpratiquep, pointrqltifdept, pointbonusrdept, scorepindiv, pointmaxprqltif, scoreprqltif, pointmax_brd, scorebrd, pointmaxpi, notepi, etat) VALUES (20, 4, 2, 162, 30, 5, 51, 5, 51, 228, 18, 42, 1020, 137, 883, 64, 35, 200, 400, 152, 0, 71, 380, 40, 228, 0, 228, 71, true);
INSERT INTO note (idnote, idsousperiode, idperiode, pointpindiv, idpersonnel, penalitedepartement, pointpenalitedepartement, penalitepersonnel, pointpenalitepersonnel, pointmaxrqntif, poucentagerqntif, pointrqntif, incitationpositif, incitationnegatif, totalpoint, pointheuresupp, incitationnhp, pointresponsabilite, pointpratiquep, pointrqltifdept, pointbonusrdept, scorepindiv, pointmaxprqltif, scoreprqltif, pointmax_brd, scorebrd, pointmaxpi, notepi, etat) VALUES (18, 4, 2, 147, 31, 0, 0, 5, 44, 190, 16.666666666666664, 32, 867, 63, 804, 285, 19, 125, 50, 95, 133, 77, 316, 30, 190, 70, 190, 77, true);
INSERT INTO note (idnote, idsousperiode, idperiode, pointpindiv, idpersonnel, penalitedepartement, pointpenalitedepartement, penalitepersonnel, pointpenalitepersonnel, pointmaxrqntif, poucentagerqntif, pointrqntif, incitationpositif, incitationnegatif, totalpoint, pointheuresupp, incitationnhp, pointresponsabilite, pointpratiquep, pointrqltifdept, pointbonusrdept, scorepindiv, pointmaxprqltif, scoreprqltif, pointmax_brd, scorebrd, pointmaxpi, notepi, etat) VALUES (17, 4, 2, 151, 32, 10, 114, 5, 57, 228, 33.333333333333329, 76, 1134, 183, 951, 35, 12, 120, 400, 238, 114, 66, 380, 62.5, 228, 50, 228, 66, true);
INSERT INTO note (idnote, idsousperiode, idperiode, pointpindiv, idpersonnel, penalitedepartement, pointpenalitedepartement, penalitepersonnel, pointpenalitepersonnel, pointmaxrqntif, poucentagerqntif, pointrqntif, incitationpositif, incitationnegatif, totalpoint, pointheuresupp, incitationnhp, pointresponsabilite, pointpratiquep, pointrqltifdept, pointbonusrdept, scorepindiv, pointmaxprqltif, scoreprqltif, pointmax_brd, scorebrd, pointmaxpi, notepi, etat) VALUES (16, 4, 2, 65, 33, 5, 20, 5, 20, 92, 16.666666666666664, 16, 397, 45, 352, 15, 5, 0, 140, 69, 92, 70, 153, 45, 92, 100, 92, 70, true);
INSERT INTO note (idnote, idsousperiode, idperiode, pointpindiv, idpersonnel, penalitedepartement, pointpenalitedepartement, penalitepersonnel, pointpenalitepersonnel, pointmaxrqntif, poucentagerqntif, pointrqntif, incitationpositif, incitationnegatif, totalpoint, pointheuresupp, incitationnhp, pointresponsabilite, pointpratiquep, pointrqltifdept, pointbonusrdept, scorepindiv, pointmaxprqltif, scoreprqltif, pointmax_brd, scorebrd, pointmaxpi, notepi, etat) VALUES (15, 4, 2, 84, 34, 0, 0, 5, 37, 140, 26.666666666666668, 38, 737, 51, 686, 28, 14, 80, 250, 117, 140, 60, 234, 50, 140, 100, 140, 60, true);
INSERT INTO note (idnote, idsousperiode, idperiode, pointpindiv, idpersonnel, penalitedepartement, pointpenalitedepartement, penalitepersonnel, pointpenalitepersonnel, pointmaxrqntif, poucentagerqntif, pointrqntif, incitationpositif, incitationnegatif, totalpoint, pointheuresupp, incitationnhp, pointresponsabilite, pointpratiquep, pointrqltifdept, pointbonusrdept, scorepindiv, pointmaxprqltif, scoreprqltif, pointmax_brd, scorebrd, pointmaxpi, notepi, etat) VALUES (14, 4, 2, 80, 35, 0, 0, 5, 25, 100, 20, 20, 495, 28, 467, 25, 3, 75, 175, 50, 70, 80, 166, 30, 100, 70, 100, 80, true);


--
-- TOC entry 2775 (class 0 OID 294564)
-- Dependencies: 209
-- Data for Name: noteservice; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2783 (class 0 OID 374943)
-- Dependencies: 217
-- Data for Name: parametragecritere; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (1, 1504, 0, 0, 0, 1.504, 3.008, 1000, 500, true, false, false, false, false, 1, 2, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (2, 1791, 0, 0, 0, 1.7909999999999999, 3.5819999999999999, 1000, 500, true, false, false, false, false, 2, 2, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (3, 947, 0, 0, 0, 0.94699999999999995, 1.8939999999999999, 1000, 500, true, false, false, false, false, 3, 2, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (4, 761, 0, 0, 0, 0.76100000000000001, 1.522, 1000, 500, true, false, false, false, false, 4, 2, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (5, 497, 0, 0, 0, 0.497, 0.99399999999999999, 1000, 500, true, false, false, false, false, 5, 2, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (6, 1194, 0, 0, 0, 1.194, 2.3879999999999999, 1000, 500, true, false, false, false, false, 6, 2, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (7, 457, 0, 0, 0, 0.45700000000000002, 0.91400000000000003, 1000, 500, true, false, false, false, false, 7, 2, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (8, 1164, 0, 0, 0, 1.1639999999999999, 2.3279999999999998, 1000, 500, true, false, false, false, false, 8, 2, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (9, 373, 0, 0, 0, 0.373, 0.746, 1000, 500, true, false, false, false, false, 9, 2, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (28, 1504, 0, 0, 0, 1.504, 3.008, 1000, 500, false, true, false, false, false, 1, 8, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (29, 1791, 0, 0, 0, 1.7909999999999999, 3.5819999999999999, 1000, 500, false, true, false, false, false, 2, 8, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (30, 947, 0, 0, 0, 0.94699999999999995, 1.8939999999999999, 1000, 500, false, true, false, false, false, 3, 8, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (31, 761, 0, 0, 0, 0.76100000000000001, 1.522, 1000, 500, false, true, false, false, false, 4, 8, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (32, 497, 0, 0, 0, 0.497, 0.99399999999999999, 1000, 500, false, true, false, false, false, 5, 8, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (33, 1194, 0, 0, 0, 1.194, 2.3879999999999999, 1000, 500, false, true, false, false, false, 6, 8, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (34, 457, 0, 0, 0, 0.45700000000000002, 0.91400000000000003, 1000, 500, false, true, false, false, false, 7, 8, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (35, 1164, 0, 0, 0, 1.1639999999999999, 2.3279999999999998, 1000, 500, false, true, false, false, false, 8, 8, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (36, 373, 0, 0, 0, 0.373, 0.746, 1000, 500, false, true, false, false, false, 9, 8, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (10, 1504, 0, 0, 200, 0, 0, 0, 0, false, false, true, false, false, 1, 3, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (11, 1791, 0, 0, 300, 0, 0, 0, 0, false, false, true, false, false, 2, 3, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (12, 947, 0, 0, 150, 0, 0, 0, 0, false, false, true, false, false, 3, 3, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (13, 761, 0, 0, 100, 0, 0, 0, 0, false, false, true, false, false, 4, 3, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (14, 497, 0, 0, 100, 0, 0, 0, 0, false, false, true, false, false, 5, 3, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (15, 1194, 0, 0, 75, 0, 0, 0, 0, false, false, true, false, false, 6, 3, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (17, 1164, 0, 0, 50, 0, 0, 0, 0, false, false, true, false, false, 8, 3, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (18, 373, 0, 0, 20, 0, 0, 0, 0, false, false, true, false, false, 9, 3, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (16, 457, 0, 0, 65, 0, 0, 0, 0, false, false, true, false, false, 7, 3, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (19, 1504, 5, 0, 300.80000000000001, 0, 0, 0, 0, false, false, false, true, false, 1, 5, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (20, 1791, 5, 0, 358.19999999999999, 0, 0, 0, 0, false, false, false, true, false, 2, 5, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (21, 947, 5, 0, 189.40000000000001, 0, 0, 0, 0, false, false, false, true, false, 3, 5, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (22, 761, 5, 0, 152.19999999999999, 0, 0, 0, 0, false, false, false, true, false, 4, 5, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (23, 497, 5, 0, 99.400000000000006, 0, 0, 0, 0, false, false, false, true, false, 5, 5, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (24, 1194, 5, 0, 238.80000000000001, 0, 0, 0, 0, false, false, false, true, false, 6, 5, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (25, 457, 5, 0, 91.400000000000006, 0, 0, 0, 0, false, false, false, true, false, 7, 5, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (26, 1164, 5, 0, 232.80000000000001, 0, 0, 0, 0, false, false, false, true, false, 8, 5, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (27, 373, 5, 0, 74.599999999999994, 0, 0, 0, 0, false, false, false, true, false, 9, 5, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (37, 1504, 10, 0, 150.40000000000001, 0, 0, 0, 0, false, false, false, false, true, 1, 7, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (38, 1791, 10, 0, 179.09999999999999, 0, 0, 0, 0, false, false, false, false, true, 2, 7, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (39, 947, 10, 0, 94.700000000000003, 0, 0, 0, 0, false, false, false, false, true, 3, 7, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (40, 761, 10, 0, 76.099999999999994, 0, 0, 0, 0, false, false, false, false, true, 4, 7, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (41, 497, 10, 0, 49.700000000000003, 0, 0, 0, 0, false, false, false, false, true, 5, 7, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (42, 1194, 10, 0, 119.40000000000001, 0, 0, 0, 0, false, false, false, false, true, 6, 7, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (43, 457, 10, 0, 45.700000000000003, 0, 0, 0, 0, false, false, false, false, true, 7, 7, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (44, 1164, 10, 0, 116.40000000000001, 0, 0, 0, 0, false, false, false, false, true, 8, 7, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (45, 373, 10, 0, 37.299999999999997, 0, 0, 0, 0, false, false, false, false, true, 9, 7, 1, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (46, 1504, 5, 0, 300.80000000000001, 0, 0, 0, 0, false, false, false, false, false, 1, 6, 1, NULL, true, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (47, 1791, 5, 0, 358.19999999999999, 0, 0, 0, 0, false, false, false, false, false, 2, 6, 1, NULL, true, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (48, 947, 5, 0, 189.40000000000001, 0, 0, 0, 0, false, false, false, false, false, 3, 6, 1, NULL, true, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (49, 761, 5, 0, 152.19999999999999, 0, 0, 0, 0, false, false, false, false, false, 4, 6, 1, NULL, true, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (50, 497, 5, 0, 99.400000000000006, 0, 0, 0, 0, false, false, false, false, false, 5, 6, 1, NULL, true, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (51, 1194, 5, 0, 238.80000000000001, 0, 0, 0, 0, false, false, false, false, false, 6, 6, 1, NULL, true, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (52, 457, 5, 0, 91.400000000000006, 0, 0, 0, 0, false, false, false, false, false, 7, 6, 1, NULL, true, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (53, 1164, 5, 0, 232.80000000000001, 0, 0, 0, 0, false, false, false, false, false, 8, 6, 1, NULL, true, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (54, 373, 5, 0, 74.599999999999994, 0, 0, 0, 0, false, false, false, false, false, 9, 6, 1, NULL, true, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (55, 1140, 5, 0, 228, 0, 0, 0, 0, false, false, false, false, false, 1, 4, 1, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (56, 1005, 5, 0, 201, 0, 0, 0, 0, false, false, false, false, false, 10, 4, 1, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (57, 1791, 5, 0, 358.19999999999999, 0, 0, 0, 0, false, false, false, false, false, 2, 4, 1, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (58, 947, 5, 0, 189.40000000000001, 0, 0, 0, 0, false, false, false, false, false, 3, 4, 1, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (59, 761, 5, 0, 152.19999999999999, 0, 0, 0, 0, false, false, false, false, false, 4, 4, 1, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (60, 497, 5, 0, 99.400000000000006, 0, 0, 0, 0, false, false, false, false, false, 5, 4, 1, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (61, 650, 5, 0, 130, 0, 0, 0, 0, false, false, false, false, false, 6, 4, 1, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (62, 457, 5, 0, 91.400000000000006, 0, 0, 0, 0, false, false, false, false, false, 7, 4, 1, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (63, 1164, 5, 0, 232.80000000000001, 0, 0, 0, 0, false, false, false, false, false, 8, 4, 1, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (64, 373, 5, 0, 74.599999999999994, 0, 0, 0, 0, false, false, false, false, false, 9, 4, 1, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (65, 1791, 0, 0, 0, 1.7909999999999999, 3.5819999999999999, 1000, 500, true, false, false, false, false, 2, 2, 2, NULL, false, false, 1, 2);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (66, 761, 0, 0, 0, 0.76100000000000001, 1.522, 1000, 500, true, false, false, false, false, 4, 2, 2, NULL, false, false, 1, 1);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (67, 650, 0, 0, 0, 0.65000000000000002, 1.3, 1000, 500, true, false, false, false, false, 6, 2, 2, NULL, false, false, 1, 1);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (68, 373, 0, 0, 0, 0.373, 0.746, 1000, 500, true, false, false, false, false, 9, 2, 2, NULL, false, false, 1, 1);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (69, 1140, 0, 0, 0, 1.1399999999999999, 2.2799999999999998, 1000, 500, true, false, false, false, false, 1, 2, 2, NULL, false, false, 3, 4);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (70, 1005, 0, 0, 0, 1.0049999999999999, 2.0099999999999998, 1000, 500, true, false, false, false, false, 10, 2, 2, NULL, false, false, 5, 6);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (71, 947, 0, 0, 0, 0.94699999999999995, 1.8939999999999999, 1000, 500, true, false, false, false, false, 3, 2, 2, NULL, false, false, 2, 2);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (72, 497, 0, 0, 0, 0.497, 0.99399999999999999, 1000, 500, true, false, false, false, false, 5, 2, 2, NULL, false, false, 4, 2);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (73, 457, 0, 0, 0, 0.45700000000000002, 0.91400000000000003, 1000, 500, true, false, false, false, false, 7, 2, 2, NULL, false, false, 4, 2);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (74, 1164, 0, 0, 0, 1.1639999999999999, 2.3279999999999998, 1000, 500, true, false, false, false, false, 8, 2, 2, NULL, false, false, 2, 3);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (75, 1791, 0, 0, 500, 0, 0, 0, 0, false, false, true, false, false, 2, 3, 2, NULL, false, false, 1, 500);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (76, 761, 0, 0, 100, 0, 0, 0, 0, false, false, true, false, false, 4, 3, 2, NULL, false, false, 1, 100);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (77, 650, 0, 0, 100, 0, 0, 0, 0, false, false, true, false, false, 6, 3, 2, NULL, false, false, 1, 100);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (78, 373, 0, 0, 100, 0, 0, 0, 0, false, false, true, false, false, 9, 3, 2, NULL, false, false, 1, 100);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (79, 1140, 0, 0, 100, 0, 0, 0, 0, false, false, true, false, false, 1, 3, 2, NULL, false, false, 3, 300);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (80, 1005, 0, 0, 100, 0, 0, 0, 0, false, false, true, false, false, 10, 3, 2, NULL, false, false, 5, 500);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (81, 947, 0, 0, 100, 0, 0, 0, 0, false, false, true, false, false, 3, 3, 2, NULL, false, false, 2, 200);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (82, 497, 0, 0, 100, 0, 0, 0, 0, false, false, true, false, false, 5, 3, 2, NULL, false, false, 4, 400);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (83, 457, 0, 0, 100, 0, 0, 0, 0, false, false, true, false, false, 7, 3, 2, NULL, false, false, 4, 400);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (84, 1164, 0, 0, 100, 0, 0, 0, 0, false, false, true, false, false, 8, 3, 2, NULL, false, false, 2, 200);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (85, 1791, 2, 0, 895.5, 0, 0, 0, 0, false, false, false, false, false, 2, 4, 2, NULL, false, true, 1, 896);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (86, 761, 2, 0, 380.5, 0, 0, 0, 0, false, false, false, false, false, 4, 4, 2, NULL, false, true, 1, 381);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (87, 650, 2, 0, 325, 0, 0, 0, 0, false, false, false, false, false, 6, 4, 2, NULL, false, true, 1, 325);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (88, 373, 2, 0, 186.5, 0, 0, 0, 0, false, false, false, false, false, 9, 4, 2, NULL, false, true, 1, 187);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (89, 1140, 2, 0, 570, 0, 0, 0, 0, false, false, false, false, false, 1, 4, 2, NULL, false, true, 3, 1710);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (90, 1005, 2, 0, 502.5, 0, 0, 0, 0, false, false, false, false, false, 10, 4, 2, NULL, false, true, 5, 2513);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (91, 947, 2, 0, 473.5, 0, 0, 0, 0, false, false, false, false, false, 3, 4, 2, NULL, false, true, 2, 947);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (92, 497, 2, 0, 248.5, 0, 0, 0, 0, false, false, false, false, false, 5, 4, 2, NULL, false, true, 4, 994);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (93, 457, 2, 0, 228.5, 0, 0, 0, 0, false, false, false, false, false, 7, 4, 2, NULL, false, true, 4, 914);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (94, 1164, 2, 0, 582, 0, 0, 0, 0, false, false, false, false, false, 8, 4, 2, NULL, false, true, 2, 1164);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (105, 1791, 2, 0, 895.5, 0, 0, 0, 0, false, false, false, false, false, 2, 6, 2, NULL, true, false, 1, 896);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (95, 1791, 2, 0, 895.5, 0, 0, 0, 0, false, false, false, true, false, 2, 5, 2, NULL, false, false, 1, 896);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (96, 761, 2, 0, 380.5, 0, 0, 0, 0, false, false, false, true, false, 4, 5, 2, NULL, false, false, 1, 381);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (97, 650, 2, 0, 325, 0, 0, 0, 0, false, false, false, true, false, 6, 5, 2, NULL, false, false, 1, 325);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (98, 373, 2, 0, 186.5, 0, 0, 0, 0, false, false, false, true, false, 9, 5, 2, NULL, false, false, 1, 187);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (99, 1140, 2, 0, 570, 0, 0, 0, 0, false, false, false, true, false, 1, 5, 2, NULL, false, false, 3, 1710);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (100, 1005, 2, 0, 502.5, 0, 0, 0, 0, false, false, false, true, false, 10, 5, 2, NULL, false, false, 5, 2513);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (101, 947, 2, 0, 473.5, 0, 0, 0, 0, false, false, false, true, false, 3, 5, 2, NULL, false, false, 2, 947);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (102, 497, 2, 0, 248.5, 0, 0, 0, 0, false, false, false, true, false, 5, 5, 2, NULL, false, false, 4, 994);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (103, 457, 2, 0, 228.5, 0, 0, 0, 0, false, false, false, true, false, 7, 5, 2, NULL, false, false, 4, 914);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (104, 1164, 2, 0, 582, 0, 0, 0, 0, false, false, false, true, false, 8, 5, 2, NULL, false, false, 2, 1164);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (106, 761, 2, 0, 380.5, 0, 0, 0, 0, false, false, false, false, false, 4, 6, 2, NULL, true, false, 1, 381);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (107, 650, 2, 0, 325, 0, 0, 0, 0, false, false, false, false, false, 6, 6, 2, NULL, true, false, 1, 325);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (108, 373, 2, 0, 186.5, 0, 0, 0, 0, false, false, false, false, false, 9, 6, 2, NULL, true, false, 1, 187);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (109, 1140, 2, 0, 570, 0, 0, 0, 0, false, false, false, false, false, 1, 6, 2, NULL, true, false, 3, 1710);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (110, 1005, 2, 0, 502.5, 0, 0, 0, 0, false, false, false, false, false, 10, 6, 2, NULL, true, false, 5, 2513);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (111, 947, 2, 0, 473.5, 0, 0, 0, 0, false, false, false, false, false, 3, 6, 2, NULL, true, false, 2, 947);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (112, 497, 2, 0, 248.5, 0, 0, 0, 0, false, false, false, false, false, 5, 6, 2, NULL, true, false, 4, 994);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (113, 457, 2, 0, 228.5, 0, 0, 0, 0, false, false, false, false, false, 7, 6, 2, NULL, true, false, 4, 914);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (114, 1164, 2, 0, 582, 0, 0, 0, 0, false, false, false, false, false, 8, 6, 2, NULL, true, false, 2, 1164);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (115, 1791, 2, 0, 895.5, 0, 0, 0, 0, false, false, false, false, true, 2, 7, 2, NULL, false, false, 1, 896);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (116, 761, 2, 0, 380.5, 0, 0, 0, 0, false, false, false, false, true, 4, 7, 2, NULL, false, false, 1, 381);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (117, 650, 2, 0, 325, 0, 0, 0, 0, false, false, false, false, true, 6, 7, 2, NULL, false, false, 1, 325);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (118, 373, 2, 0, 186.5, 0, 0, 0, 0, false, false, false, false, true, 9, 7, 2, NULL, false, false, 1, 187);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (119, 1140, 2, 0, 570, 0, 0, 0, 0, false, false, false, false, true, 1, 7, 2, NULL, false, false, 3, 1710);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (120, 1005, 2, 0, 502.5, 0, 0, 0, 0, false, false, false, false, true, 10, 7, 2, NULL, false, false, 5, 2513);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (121, 947, 2, 0, 473.5, 0, 0, 0, 0, false, false, false, false, true, 3, 7, 2, NULL, false, false, 2, 947);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (122, 497, 2, 0, 248.5, 0, 0, 0, 0, false, false, false, false, true, 5, 7, 2, NULL, false, false, 4, 994);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (123, 457, 2, 0, 228.5, 0, 0, 0, 0, false, false, false, false, true, 7, 7, 2, NULL, false, false, 4, 914);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (124, 1164, 2, 0, 582, 0, 0, 0, 0, false, false, false, false, true, 8, 7, 2, NULL, false, false, 2, 1164);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (131, 947, 0, 0, 0, 0.94699999999999995, 1.8939999999999999, 1000, 500, false, true, false, false, false, 3, 8, 2, NULL, false, false, 2, 2);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (132, 497, 0, 0, 0, 0.497, 0.99399999999999999, 1000, 500, false, true, false, false, false, 5, 8, 2, NULL, false, false, 4, 2);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (133, 457, 0, 0, 0, 0.45700000000000002, 0.91400000000000003, 1000, 500, false, true, false, false, false, 7, 8, 2, NULL, false, false, 4, 2);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (134, 1164, 0, 0, 0, 1.1639999999999999, 2.3279999999999998, 1000, 500, false, true, false, false, false, 8, 8, 2, NULL, false, false, 2, 3);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (135, 1140, 0, 0, 0, 1.1399999999999999, 2.2799999999999998, 1000, 500, true, false, false, false, false, 1, 2, 3, NULL, false, false, 3, 4);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (136, 1005, 0, 0, 0, 1.0049999999999999, 2.0099999999999998, 1000, 500, true, false, false, false, false, 10, 2, 3, NULL, false, false, 1, 2);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (137, 1791, 0, 0, 0, 1.7909999999999999, 3.5819999999999999, 1000, 500, true, false, false, false, false, 2, 2, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (138, 947, 0, 0, 0, 0.94699999999999995, 1.8939999999999999, 1000, 500, true, false, false, false, false, 3, 2, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (139, 761, 0, 0, 0, 0.76100000000000001, 1.522, 1000, 500, true, false, false, false, false, 4, 2, 3, NULL, false, false, 1, 1);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (156, 1005, 1, 0, 1005, 0, 0, 0, 0, false, false, false, false, false, 10, 4, 3, NULL, false, true, 1, 1005);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (157, 1791, 1, 0, 1791, 0, 0, 0, 0, false, false, false, false, false, 2, 4, 3, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (158, 947, 1, 0, 947, 0, 0, 0, 0, false, false, false, false, false, 3, 4, 3, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (159, 761, 1, 0, 761, 0, 0, 0, 0, false, false, false, false, false, 4, 4, 3, NULL, false, true, 1, 761);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (140, 497, 0, 0, 0, 0.497, 0.99399999999999999, 1000, 500, true, false, false, false, false, 5, 2, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (141, 650, 0, 0, 0, 0.65000000000000002, 1.3, 1000, 500, true, false, false, false, false, 6, 2, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (142, 457, 0, 0, 0, 0.45700000000000002, 0.91400000000000003, 1000, 500, true, false, false, false, false, 7, 2, 3, NULL, false, false, 1, 1);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (143, 1164, 0, 0, 0, 1.1639999999999999, 2.3279999999999998, 1000, 500, true, false, false, false, false, 8, 2, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (144, 373, 0, 0, 0, 0.373, 0.746, 1000, 500, true, false, false, false, false, 9, 2, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (145, 1140, 0, 0, 500, 0, 0, 0, 0, false, false, true, false, false, 1, 3, 3, NULL, false, false, 3, 1500);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (125, 1791, 0, 0, 0, 1.7909999999999999, 3.5819999999999999, 1000, 500, false, true, false, false, false, 2, 8, 2, NULL, false, false, 1, 2);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (126, 761, 0, 0, 0, 0.76100000000000001, 1.522, 1000, 500, false, true, false, false, false, 4, 8, 2, NULL, false, false, 1, 1);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (127, 650, 0, 0, 0, 0.65000000000000002, 1.3, 1000, 500, false, true, false, false, false, 6, 8, 2, NULL, false, false, 1, 1);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (128, 373, 0, 0, 0, 0.373, 0.746, 1000, 500, false, true, false, false, false, 9, 8, 2, NULL, false, false, 1, 1);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (129, 1140, 0, 0, 0, 1.1399999999999999, 2.2799999999999998, 1000, 500, false, true, false, false, false, 1, 8, 2, NULL, false, false, 3, 4);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (130, 1005, 0, 0, 0, 1.0049999999999999, 2.0099999999999998, 1000, 500, false, true, false, false, false, 10, 8, 2, NULL, false, false, 5, 6);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (146, 1005, 0, 0, 300, 0, 0, 0, 0, false, false, true, false, false, 10, 3, 3, NULL, false, false, 1, 300);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (147, 1791, 0, 0, 0, 0, 0, 0, 0, false, false, true, false, false, 2, 3, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (148, 947, 0, 0, 0, 0, 0, 0, 0, false, false, true, false, false, 3, 3, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (149, 761, 0, 0, 200, 0, 0, 0, 0, false, false, true, false, false, 4, 3, 3, NULL, false, false, 1, 200);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (150, 497, 0, 0, 0, 0, 0, 0, 0, false, false, true, false, false, 5, 3, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (151, 650, 0, 0, 0, 0, 0, 0, 0, false, false, true, false, false, 6, 3, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (152, 457, 0, 0, 150, 0, 0, 0, 0, false, false, true, false, false, 7, 3, 3, NULL, false, false, 1, 150);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (153, 1164, 0, 0, 0, 0, 0, 0, 0, false, false, true, false, false, 8, 3, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (154, 373, 0, 0, 0, 0, 0, 0, 0, false, false, true, false, false, 9, 3, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (155, 1140, 1, 0, 1140, 0, 0, 0, 0, false, false, false, false, false, 1, 4, 3, NULL, false, true, 3, 3420);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (160, 497, 1, 0, 497, 0, 0, 0, 0, false, false, false, false, false, 5, 4, 3, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (161, 650, 1, 0, 650, 0, 0, 0, 0, false, false, false, false, false, 6, 4, 3, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (162, 457, 1, 0, 457, 0, 0, 0, 0, false, false, false, false, false, 7, 4, 3, NULL, false, true, 1, 457);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (163, 1164, 1, 0, 1164, 0, 0, 0, 0, false, false, false, false, false, 8, 4, 3, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (164, 373, 1, 0, 373, 0, 0, 0, 0, false, false, false, false, false, 9, 4, 3, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (165, 1140, 1, 0, 1140, 0, 0, 0, 0, false, false, false, false, false, 1, 5, 3, NULL, false, true, 3, 3420);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (166, 1005, 1, 0, 1005, 0, 0, 0, 0, false, false, false, false, false, 10, 5, 3, NULL, false, true, 1, 1005);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (167, 1791, 1, 0, 1791, 0, 0, 0, 0, false, false, false, false, false, 2, 5, 3, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (168, 947, 1, 0, 947, 0, 0, 0, 0, false, false, false, false, false, 3, 5, 3, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (169, 761, 1, 0, 761, 0, 0, 0, 0, false, false, false, false, false, 4, 5, 3, NULL, false, true, 1, 761);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (170, 497, 1, 0, 497, 0, 0, 0, 0, false, false, false, false, false, 5, 5, 3, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (171, 650, 1, 0, 650, 0, 0, 0, 0, false, false, false, false, false, 6, 5, 3, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (172, 457, 1, 0, 457, 0, 0, 0, 0, false, false, false, false, false, 7, 5, 3, NULL, false, true, 1, 457);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (173, 1164, 1, 0, 1164, 0, 0, 0, 0, false, false, false, false, false, 8, 5, 3, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (174, 373, 1, 0, 373, 0, 0, 0, 0, false, false, false, false, false, 9, 5, 3, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (175, 1140, 1, 0, 1140, 0, 0, 0, 0, false, false, false, false, false, 1, 6, 3, NULL, true, false, 3, 3420);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (176, 1005, 1, 0, 1005, 0, 0, 0, 0, false, false, false, false, false, 10, 6, 3, NULL, true, false, 1, 1005);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (177, 1791, 1, 0, 1791, 0, 0, 0, 0, false, false, false, false, false, 2, 6, 3, NULL, true, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (178, 947, 1, 0, 947, 0, 0, 0, 0, false, false, false, false, false, 3, 6, 3, NULL, true, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (179, 761, 1, 0, 761, 0, 0, 0, 0, false, false, false, false, false, 4, 6, 3, NULL, true, false, 1, 761);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (180, 497, 1, 0, 497, 0, 0, 0, 0, false, false, false, false, false, 5, 6, 3, NULL, true, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (181, 650, 1, 0, 650, 0, 0, 0, 0, false, false, false, false, false, 6, 6, 3, NULL, true, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (182, 457, 1, 0, 457, 0, 0, 0, 0, false, false, false, false, false, 7, 6, 3, NULL, true, false, 1, 457);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (183, 1164, 1, 0, 1164, 0, 0, 0, 0, false, false, false, false, false, 8, 6, 3, NULL, true, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (184, 373, 1, 0, 373, 0, 0, 0, 0, false, false, false, false, false, 9, 6, 3, NULL, true, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (185, 1140, 1, 0, 1140, 0, 0, 0, 0, false, false, false, false, true, 1, 7, 3, NULL, false, false, 3, 3420);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (186, 1005, 1, 0, 1005, 0, 0, 0, 0, false, false, false, false, true, 10, 7, 3, NULL, false, false, 1, 1005);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (187, 1791, 1, 0, 1791, 0, 0, 0, 0, false, false, false, false, true, 2, 7, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (188, 947, 1, 0, 947, 0, 0, 0, 0, false, false, false, false, true, 3, 7, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (189, 761, 1, 0, 761, 0, 0, 0, 0, false, false, false, false, true, 4, 7, 3, NULL, false, false, 1, 761);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (190, 497, 1, 0, 497, 0, 0, 0, 0, false, false, false, false, true, 5, 7, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (191, 650, 1, 0, 650, 0, 0, 0, 0, false, false, false, false, true, 6, 7, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (192, 457, 1, 0, 457, 0, 0, 0, 0, false, false, false, false, true, 7, 7, 3, NULL, false, false, 1, 457);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (193, 1164, 1, 0, 1164, 0, 0, 0, 0, false, false, false, false, true, 8, 7, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (194, 373, 1, 0, 373, 0, 0, 0, 0, false, false, false, false, true, 9, 7, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (195, 1140, 0, 0, 0, 1.1399999999999999, 2.2799999999999998, 1000, 500, false, true, false, false, false, 1, 8, 3, NULL, false, false, 3, 4);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (196, 1005, 0, 0, 0, 1.0049999999999999, 2.0099999999999998, 1000, 500, false, true, false, false, false, 10, 8, 3, NULL, false, false, 1, 2);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (197, 1791, 0, 0, 0, 1.7909999999999999, 3.5819999999999999, 1000, 500, false, true, false, false, false, 2, 8, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (198, 947, 0, 0, 0, 0.94699999999999995, 1.8939999999999999, 1000, 500, false, true, false, false, false, 3, 8, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (199, 761, 0, 0, 0, 0.76100000000000001, 1.522, 1000, 500, false, true, false, false, false, 4, 8, 3, NULL, false, false, 1, 1);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (200, 497, 0, 0, 0, 0.497, 0.99399999999999999, 1000, 500, false, true, false, false, false, 5, 8, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (201, 650, 0, 0, 0, 0.65000000000000002, 1.3, 1000, 500, false, true, false, false, false, 6, 8, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (202, 457, 0, 0, 0, 0.45700000000000002, 0.91400000000000003, 1000, 500, false, true, false, false, false, 7, 8, 3, NULL, false, false, 1, 1);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (203, 1164, 0, 0, 0, 1.1639999999999999, 2.3279999999999998, 1000, 500, false, true, false, false, false, 8, 8, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (204, 373, 0, 0, 0, 0.373, 0.746, 1000, 500, false, true, false, false, false, 9, 8, 3, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (205, 1300, 0, 0, 6500, 0, 0, 0, 0, false, false, true, false, false, 11, 3, 5, NULL, false, false, 1, 6500);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (206, 1000, 0, 0, 4000, 0, 0, 0, 0, false, false, true, false, false, 12, 3, 5, NULL, false, false, 3, 12000);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (207, 800, 0, 0, 2000, 0, 0, 0, 0, false, false, true, false, false, 13, 3, 5, NULL, false, false, 2, 4000);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (208, 1300, 1, 0, 1300, 0, 0, 0, 0, false, false, false, false, false, 11, 4, 5, NULL, false, true, 1, 1300);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (209, 1000, 1, 0, 1000, 0, 0, 0, 0, false, false, false, false, false, 12, 4, 5, NULL, false, true, 3, 3000);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (210, 800, 1, 0, 800, 0, 0, 0, 0, false, false, false, false, false, 13, 4, 5, NULL, false, true, 2, 1600);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (213, 800, 1, 0, 800, 0, 0, 0, 0, false, false, false, true, false, 13, 5, 5, NULL, false, false, 2, 1600);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (214, 1300, 1, 0, 1300, 0, 0, 0, 0, false, false, false, false, false, 11, 6, 5, NULL, true, false, 1, 1300);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (212, 1000, 1, 0, 1000, 0, 0, 0, 0, false, false, false, true, false, 12, 5, 5, NULL, false, false, 3, 3000);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (211, 1300, 1, 0, 1300, 0, 0, 0, 0, false, false, false, true, false, 11, 5, 5, NULL, false, false, 1, 1300);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (215, 1000, 1, 0, 1000, 0, 0, 0, 0, false, false, false, false, false, 12, 6, 5, NULL, true, false, 3, 3000);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (216, 800, 1, 0, 800, 0, 0, 0, 0, false, false, false, false, false, 13, 6, 5, NULL, true, false, 2, 1600);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (217, 1300, 1, 0, 1300, 0, 0, 0, 0, false, false, false, false, true, 11, 7, 5, NULL, false, false, 1, 1300);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (218, 1000, 1, 0, 1000, 0, 0, 0, 0, false, false, false, false, true, 12, 7, 5, NULL, false, false, 3, 3000);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (219, 800, 1, 0, 800, 0, 0, 0, 0, false, false, false, false, true, 13, 7, 5, NULL, false, false, 2, 1600);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (220, 1300, 0, 0, 0, 13, 16.25, 100, 80, true, false, false, false, false, 11, 2, 5, NULL, false, false, 1, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (221, 1000, 0, 0, 0, 10, 12.5, 100, 80, true, false, false, false, false, 12, 2, 5, NULL, false, false, 3, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (222, 800, 0, 0, 0, 8, 10, 100, 80, true, false, false, false, false, 13, 2, 5, NULL, false, false, 2, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (223, 1300, 0, 0, 0, 13, 16.25, 100, 80, false, true, false, false, false, 11, 8, 5, NULL, false, false, 1, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (224, 1000, 0, 0, 0, 10, 12.5, 100, 80, false, true, false, false, false, 12, 8, 5, NULL, false, false, 3, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (225, 800, 0, 0, 0, 8, 10, 100, 80, false, true, false, false, false, 13, 8, 5, NULL, false, false, 2, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (226, 1300, 0, 0, 500, 0, 0, 0, 0, false, false, true, false, false, 11, 3, 6, NULL, false, false, 2, 1000);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (227, 1000, 0, 0, 400, 0, 0, 0, 0, false, false, true, false, false, 12, 3, 6, NULL, false, false, 3, 1200);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (228, 800, 0, 0, 300, 0, 0, 0, 0, false, false, true, false, false, 13, 3, 6, NULL, false, false, 3, 900);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (248, 1791, 0, 0, 0, 1.7909999999999999, 3.5819999999999999, 1000, 500, true, false, false, false, false, 2, 2, 4, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (249, 1164, 0, 0, 0, 1.1639999999999999, 2.3279999999999998, 1000, 500, true, false, false, false, false, 8, 2, 4, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (250, 497, 0, 0, 0, 0.497, 0.99399999999999999, 1000, 500, true, false, false, false, false, 5, 2, 4, NULL, false, false, 1, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (251, 947, 0, 0, 0, 0.94699999999999995, 1.8939999999999999, 1000, 500, true, false, false, false, false, 3, 2, 4, NULL, false, false, 1, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (252, 650, 0, 0, 0, 0.65000000000000002, 1.3, 1000, 500, true, false, false, false, false, 6, 2, 4, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (253, 761, 0, 0, 0, 0.76100000000000001, 1.522, 1000, 500, true, false, false, false, false, 4, 2, 4, NULL, false, false, 1, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (234, 800, 2, 0, 400, 0, 0, 0, 0, false, false, false, true, false, 13, 5, 6, NULL, false, false, 3, 1200);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (233, 1000, 2, 0, 500, 0, 0, 0, 0, false, false, false, true, false, 12, 5, 6, NULL, false, false, 3, 1500);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (232, 1300, 2, 0, 650, 0, 0, 0, 0, false, false, false, true, false, 11, 5, 6, NULL, false, false, 2, 1300);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (254, 1005, 0, 0, 0, 1.0049999999999999, 2.0099999999999998, 1000, 500, true, false, false, false, false, 10, 2, 4, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (255, 1140, 0, 0, 0, 1.1399999999999999, 2.2799999999999998, 1000, 500, true, false, false, false, false, 1, 2, 4, NULL, false, false, 2, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (256, 700, 0, 0, 0, 0.69999999999999996, 1.3999999999999999, 1000, 500, true, false, false, false, false, 14, 2, 4, NULL, false, false, 1, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (241, 1300, 0, 0, 0, 1.3, 2.6000000000000001, 1000, 500, true, false, false, false, false, 11, 2, 6, NULL, false, false, 2, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (242, 1000, 0, 0, 0, 1, 2, 1000, 500, true, false, false, false, false, 12, 2, 6, NULL, false, false, 3, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (243, 800, 0, 0, 0, 0.80000000000000004, 1.6000000000000001, 1000, 500, true, false, false, false, false, 13, 2, 6, NULL, false, false, 3, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (258, 1791, 0, 0, 150, 0, 0, 0, 0, false, false, true, false, false, 2, 3, 4, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (244, 1300, 0, 0, 0, 1.3, 2.6000000000000001, 1000, 500, false, true, false, false, false, 11, 8, 6, NULL, false, false, 2, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (245, 1000, 0, 0, 0, 1, 2, 1000, 500, false, true, false, false, false, 12, 8, 6, NULL, false, false, 3, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (246, 800, 0, 0, 0, 0.80000000000000004, 1.6000000000000001, 1000, 500, false, true, false, false, false, 13, 8, 6, NULL, false, false, 3, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (229, 1300, 3, 0, 434, 0, 0, 0, 0, false, false, false, false, false, 11, 4, 6, NULL, false, true, 2, 868);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (230, 1000, 3, 0, 334, 0, 0, 0, 0, false, false, false, false, false, 12, 4, 6, NULL, false, true, 3, 1002);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (231, 800, 3, 0, 267, 0, 0, 0, 0, false, false, false, false, false, 13, 4, 6, NULL, false, true, 3, 801);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (235, 1300, 3, 0, 434, 0, 0, 0, 0, false, false, false, false, false, 11, 6, 6, NULL, true, false, 2, 868);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (236, 1000, 3, 0, 334, 0, 0, 0, 0, false, false, false, false, false, 12, 6, 6, NULL, true, false, 3, 1002);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (237, 800, 3, 0, 267, 0, 0, 0, 0, false, false, false, false, false, 13, 6, 6, NULL, true, false, 3, 801);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (238, 1300, 3, 0, 434, 0, 0, 0, 0, false, false, false, false, true, 11, 7, 6, NULL, false, false, 2, 868);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (239, 1000, 3, 0, 334, 0, 0, 0, 0, false, false, false, false, true, 12, 7, 6, NULL, false, false, 3, 1002);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (240, 800, 3, 0, 267, 0, 0, 0, 0, false, false, false, false, true, 13, 7, 6, NULL, false, false, 3, 801);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (247, 457, 0, 0, 0, 0.45700000000000002, 0.91400000000000003, 1000, 500, true, false, false, false, false, 7, 2, 4, NULL, false, false, 1, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (263, 761, 0, 0, 200, 0, 0, 0, 0, false, false, true, false, false, 4, 3, 4, NULL, false, false, 1, 200);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (264, 1005, 0, 0, 300, 0, 0, 0, 0, false, false, true, false, false, 10, 3, 4, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (265, 1140, 0, 0, 400, 0, 0, 0, 0, false, false, true, false, false, 1, 3, 4, NULL, false, false, 2, 800);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (267, 457, 5, 0, 92, 0, 0, 0, 0, false, false, false, false, false, 7, 4, 4, NULL, false, true, 1, 92);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (268, 1791, 5, 0, 359, 0, 0, 0, 0, false, false, false, false, false, 2, 4, 4, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (269, 1164, 5, 0, 233, 0, 0, 0, 0, false, false, false, false, false, 8, 4, 4, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (270, 497, 5, 0, 100, 0, 0, 0, 0, false, false, false, false, false, 5, 4, 4, NULL, false, true, 1, 100);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (271, 947, 5, 0, 190, 0, 0, 0, 0, false, false, false, false, false, 3, 4, 4, NULL, false, true, 1, 190);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (272, 650, 5, 0, 130, 0, 0, 0, 0, false, false, false, false, false, 6, 4, 4, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (273, 761, 5, 0, 153, 0, 0, 0, 0, false, false, false, false, false, 4, 4, 4, NULL, false, true, 1, 153);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (274, 1005, 5, 0, 201, 0, 0, 0, 0, false, false, false, false, false, 10, 4, 4, NULL, false, true, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (275, 1140, 5, 0, 228, 0, 0, 0, 0, false, false, false, false, false, 1, 4, 4, NULL, false, true, 2, 456);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (259, 1164, 0, 0, 140, 0, 0, 0, 0, false, false, true, false, false, 8, 3, 4, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (260, 497, 0, 0, 175, 0, 0, 0, 0, false, false, true, false, false, 5, 3, 4, NULL, false, false, 1, 175);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (266, 700, 0, 0, 250, 0, 0, 0, 0, false, false, true, false, false, 14, 3, 4, NULL, false, false, 1, 250);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (261, 947, 0, 0, 50, 0, 0, 0, 0, false, false, true, false, false, 3, 3, 4, NULL, false, false, 1, 50);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (262, 650, 0, 0, 250, 0, 0, 0, 0, false, false, true, false, false, 6, 3, 4, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (276, 700, 5, 0, 140, 0, 0, 0, 0, false, false, false, false, false, 14, 4, 4, NULL, false, true, 1, 140);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (287, 457, 5, 0, 92, 0, 0, 0, 0, false, false, false, false, false, 7, 6, 4, NULL, true, false, 1, 92);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (288, 1791, 5, 0, 359, 0, 0, 0, 0, false, false, false, false, false, 2, 6, 4, NULL, true, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (289, 1164, 5, 0, 233, 0, 0, 0, 0, false, false, false, false, false, 8, 6, 4, NULL, true, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (290, 497, 5, 0, 100, 0, 0, 0, 0, false, false, false, false, false, 5, 6, 4, NULL, true, false, 1, 100);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (291, 947, 5, 0, 190, 0, 0, 0, 0, false, false, false, false, false, 3, 6, 4, NULL, true, false, 1, 190);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (292, 650, 5, 0, 130, 0, 0, 0, 0, false, false, false, false, false, 6, 6, 4, NULL, true, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (293, 761, 5, 0, 153, 0, 0, 0, 0, false, false, false, false, false, 4, 6, 4, NULL, true, false, 1, 153);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (294, 1005, 5, 0, 201, 0, 0, 0, 0, false, false, false, false, false, 10, 6, 4, NULL, true, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (295, 1140, 5, 0, 228, 0, 0, 0, 0, false, false, false, false, false, 1, 6, 4, NULL, true, false, 2, 456);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (296, 700, 5, 0, 140, 0, 0, 0, 0, false, false, false, false, false, 14, 6, 4, NULL, true, false, 1, 140);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (297, 457, 5, 0, 92, 0, 0, 0, 0, false, false, false, false, true, 7, 7, 4, NULL, false, false, 1, 92);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (298, 1791, 5, 0, 359, 0, 0, 0, 0, false, false, false, false, true, 2, 7, 4, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (299, 1164, 5, 0, 233, 0, 0, 0, 0, false, false, false, false, true, 8, 7, 4, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (300, 497, 5, 0, 100, 0, 0, 0, 0, false, false, false, false, true, 5, 7, 4, NULL, false, false, 1, 100);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (301, 947, 5, 0, 190, 0, 0, 0, 0, false, false, false, false, true, 3, 7, 4, NULL, false, false, 1, 190);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (302, 650, 5, 0, 130, 0, 0, 0, 0, false, false, false, false, true, 6, 7, 4, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (303, 761, 5, 0, 153, 0, 0, 0, 0, false, false, false, false, true, 4, 7, 4, NULL, false, false, 1, 153);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (304, 1005, 5, 0, 201, 0, 0, 0, 0, false, false, false, false, true, 10, 7, 4, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (305, 1140, 5, 0, 228, 0, 0, 0, 0, false, false, false, false, true, 1, 7, 4, NULL, false, false, 2, 456);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (306, 700, 5, 0, 140, 0, 0, 0, 0, false, false, false, false, true, 14, 7, 4, NULL, false, false, 1, 140);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (307, 457, 0, 0, 0, 0.45700000000000002, 0.91400000000000003, 1000, 500, false, true, false, false, false, 7, 8, 4, NULL, false, false, 1, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (308, 1791, 0, 0, 0, 1.7909999999999999, 3.5819999999999999, 1000, 500, false, true, false, false, false, 2, 8, 4, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (309, 1164, 0, 0, 0, 1.1639999999999999, 2.3279999999999998, 1000, 500, false, true, false, false, false, 8, 8, 4, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (310, 497, 0, 0, 0, 0.497, 0.99399999999999999, 1000, 500, false, true, false, false, false, 5, 8, 4, NULL, false, false, 1, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (311, 947, 0, 0, 0, 0.94699999999999995, 1.8939999999999999, 1000, 500, false, true, false, false, false, 3, 8, 4, NULL, false, false, 1, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (312, 650, 0, 0, 0, 0.65000000000000002, 1.3, 1000, 500, false, true, false, false, false, 6, 8, 4, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (313, 761, 0, 0, 0, 0.76100000000000001, 1.522, 1000, 500, false, true, false, false, false, 4, 8, 4, NULL, false, false, 1, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (314, 1005, 0, 0, 0, 1.0049999999999999, 2.0099999999999998, 1000, 500, false, true, false, false, false, 10, 8, 4, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (315, 1140, 0, 0, 0, 1.1399999999999999, 2.2799999999999998, 1000, 500, false, true, false, false, false, 1, 8, 4, NULL, false, false, 2, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (316, 700, 0, 0, 0, 0.69999999999999996, 1.3999999999999999, 1000, 500, false, true, false, false, false, 14, 8, 4, NULL, false, false, 1, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (277, 457, 3, 0, 153, 0, 0, 0, 0, false, false, false, true, false, 7, 5, 4, NULL, false, false, 1, 153);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (278, 1791, 3, 0, 597, 0, 0, 0, 0, false, false, false, true, false, 2, 5, 4, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (279, 1164, 3, 0, 388, 0, 0, 0, 0, false, false, false, true, false, 8, 5, 4, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (280, 497, 3, 0, 166, 0, 0, 0, 0, false, false, false, true, false, 5, 5, 4, NULL, false, false, 1, 166);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (281, 947, 3, 0, 316, 0, 0, 0, 0, false, false, false, true, false, 3, 5, 4, NULL, false, false, 1, 316);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (282, 650, 3, 0, 217, 0, 0, 0, 0, false, false, false, true, false, 6, 5, 4, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (283, 761, 3, 0, 254, 0, 0, 0, 0, false, false, false, true, false, 4, 5, 4, NULL, false, false, 1, 254);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (284, 1005, 3, 0, 335, 0, 0, 0, 0, false, false, false, true, false, 10, 5, 4, NULL, false, false, 0, 0);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (285, 1140, 3, 0, 380, 0, 0, 0, 0, false, false, false, true, false, 1, 5, 4, NULL, false, false, 2, 760);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (286, 700, 3, 0, 234, 0, 0, 0, 0, false, false, false, true, false, 14, 5, 4, NULL, false, false, 1, 234);
INSERT INTO parametragecritere (idparametragecritere, indice, denominateur, valeur, point, valeurjournee, valeurnuit, denominateurjournee, denominateurnuit, heuresupp, heuresupn, pratiqueprivee, resultatqualitatifdept, performanceindividuelle, idcategorie, idcritere, idstructure, idservice, bonusrevenudept, resultatquantitatifdept, nombre, total1) VALUES (257, 457, 0, 0, 140, 0, 0, 0, 0, false, false, true, false, false, 7, 3, 4, NULL, false, false, 1, 140);


--
-- TOC entry 2799 (class 0 OID 459154)
-- Dependencies: 233
-- Data for Name: parametragepenalite; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (1, 5, 1, 3, 4, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (2, 10, 2, 3, 4, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (3, 15, 3, 3, 4, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (4, 5, 1, 3, 3, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (5, 10, 2, 3, 3, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (6, 15, 3, 3, 3, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (7, 10, 6, 2, NULL, 10, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (8, 10, 4, 2, NULL, 10, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (9, 10, 5, 2, NULL, 10, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (10, 5, 1, 5, 1, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (11, 10, 2, 5, 1, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (12, 5, 7, 5, 1, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (13, 15, 3, 5, 1, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (14, 5, 1, 5, 2, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (15, 5, 7, 5, 2, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (16, 10, 2, 5, 6, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (17, 5, 7, 5, 6, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (18, 5, 4, 5, NULL, 10, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (19, 10, 5, 5, NULL, 10, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (20, 5, 6, 5, NULL, 10, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (21, 5, 4, 6, NULL, 10, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (22, 10, 5, 6, NULL, 10, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (23, 5, 6, 6, NULL, 10, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (24, 5, 1, 6, 1, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (25, 10, 2, 6, 1, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (26, 15, 3, 6, 1, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (27, 5, 7, 6, 1, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (28, 5, 1, 6, 2, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (29, 10, 2, 6, 2, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (30, 15, 3, 6, 2, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (31, 5, 7, 6, 2, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (32, 5, 1, 6, 6, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (33, 10, 2, 6, 6, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (34, 15, 3, 6, 6, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (35, 5, 7, 6, 6, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (36, 5, 1, 4, 4, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (37, 10, 2, 4, 4, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (38, 15, 3, 4, 4, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (39, 5, 7, 4, 4, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (40, 5, 1, 4, 7, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (41, 10, 2, 4, 7, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (42, 15, 3, 4, 7, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (43, 5, 7, 4, 7, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (44, 5, 1, 4, 8, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (45, 10, 2, 4, 8, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (46, 15, 3, 4, 8, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (47, 5, 7, 4, 8, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (48, 5, 1, 4, 9, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (49, 10, 2, 4, 9, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (50, 15, 3, 4, 9, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (51, 5, 7, 4, 9, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (52, 5, 1, 4, 3, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (53, 10, 2, 4, 3, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (54, 15, 3, 4, 3, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (55, 5, 7, 4, 3, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (56, 5, 1, 4, 5, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (57, 10, 2, 4, 5, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (58, 15, 3, 4, 5, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (59, 5, 7, 4, 5, 9, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (60, 5, 4, 4, NULL, 10, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (61, 10, 5, 4, NULL, 10, '-');
INSERT INTO parametragepenalite (idparametragepenalite, pourcentage, idpenalite, idstructure, idservice, idcritere, detail) VALUES (62, 5, 6, 4, NULL, 10, '-');


--
-- TOC entry 2792 (class 0 OID 408395)
-- Dependencies: 226
-- Data for Name: penalite; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO penalite (idpenalite, nom, pourcentage, personnel, service, detail, code) VALUES (1, 'Pénalité 1', 5, false, true, '-', '001');
INSERT INTO penalite (idpenalite, nom, pourcentage, personnel, service, detail, code) VALUES (2, 'Pénalité 2', 10, false, true, '-', '002');
INSERT INTO penalite (idpenalite, nom, pourcentage, personnel, service, detail, code) VALUES (3, 'Penalité 3', 15, false, true, '-', '003');
INSERT INTO penalite (idpenalite, nom, pourcentage, personnel, service, detail, code) VALUES (4, 'Pénalité P1', 5, true, false, '-', '001');
INSERT INTO penalite (idpenalite, nom, pourcentage, personnel, service, detail, code) VALUES (5, 'Pénalité P2', 10, true, false, '-', '002');
INSERT INTO penalite (idpenalite, nom, pourcentage, personnel, service, detail, code) VALUES (6, 'Pénalité P3', 5, true, false, '-', '003');
INSERT INTO penalite (idpenalite, nom, pourcentage, personnel, service, detail, code) VALUES (7, 'Pénalité 4', 5, false, true, '-', '004');


--
-- TOC entry 2757 (class 0 OID 285997)
-- Dependencies: 191
-- Data for Name: periode; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO periode (idperiode, nom, code, idparent, etat) VALUES (1, '2020', '2020', NULL, true);
INSERT INTO periode (idperiode, nom, code, idparent, etat) VALUES (2, '2021', '2021', NULL, true);


--
-- TOC entry 2758 (class 0 OID 286006)
-- Dependencies: 192
-- Data for Name: personnel; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (1, 1, 'Kenne', 'Gervais', '2020-08-11', 'P0000001', true, 3, 1, 1, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (2, 1, 'Tatouo', 'Patrice', '2020-08-11', 'P0000002', true, 4, 1, 1, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (3, 4, 'Meli', 'Carelle', '2020-08-24', 'P0000003', true, 2, 1, 4, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (4, 1, 'Zambo', 'Louis', '2020-11-10', 'P0000004', true, 2, 1, 1, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (5, 1, 'P1', 'P1', '2021-03-10', 'P0000001', true, 2, 2, 2, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (6, 5, 'P2', 'P2', '2021-03-10', 'P0000002', true, 1, 2, 2, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (7, 9, 'P3', 'P3', '2021-03-10', 'P0000003', true, 3, 2, 2, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (8, 3, 'Compta', 'Compta', '2021-03-11', 'P0000004', true, 1, 2, 3, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (9, 1, 'P1', 'P1', '2021-03-11', 'P0000001', true, 1, 3, 3, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (10, 10, 'P2', 'P2', '2021-03-11', 'P0000002', true, 2, 3, 4, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (11, 1, 'P3', 'P3', '2021-03-11', 'P0000003', true, 3, 3, 3, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (12, 4, 'P4', 'P4', '2021-03-11', 'P0000004', true, 4, 3, 5, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (13, 1, 'P5', 'P5', '2021-03-11', 'P0000005', true, 2, 3, 4, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (14, 7, 'P6', 'P6', '2021-03-11', 'P0000006', true, 6, 3, 2, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (15, 11, 'P1', 'P1', '2021-03-31', 'P0000001', true, 7, 5, 1, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (16, 12, 'P2', 'P2', '2021-03-31', 'P0000002', true, 8, 5, 1, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (17, 13, 'P3', 'P3', '2021-03-31', 'P0000003', true, 9, 5, 6, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (18, 12, 'P4', 'P4', '2021-03-31', 'P0000004', true, 10, 5, 6, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (19, 12, 'P5', 'P5', '2021-03-31', 'P0000005', true, 10, 5, 1, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (20, 13, 'P6', 'P6', '2021-03-31', 'P0000006', true, 7, 5, 2, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (21, 11, 'P1', 'P1', '2021-04-05', 'P0000001', true, 7, 6, 1, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (22, 12, 'P2', 'P2', '2021-04-05', 'P0000002', true, 9, 6, 6, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (23, 12, 'P3', 'P3', '2021-04-05', 'P0000003', true, 10, 6, 6, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (25, 13, 'P5', 'P5', '2021-04-05', 'P0000005', true, 8, 6, 1, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (26, 12, 'P6', 'P6', '2021-04-06', 'P0000006', true, 9, 6, 1, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (27, 13, 'P7', 'P7', '2021-04-06', 'P0000007', true, 7, 6, 6, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (28, 13, 'P8', 'P8', '2021-04-06', 'P0000008', true, 10, 6, 2, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (24, 11, 'P4', 'P4', '2021-04-05', 'P0000004', true, 8, 6, 2, 2000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (29, 4, 'Meli', 'Stévie Carelle', '2021-06-25', 'P0000001', true, 1, 4, 5, 500000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (30, 1, 'Pouamoun', 'Abdel', '2021-06-25', 'P0000002', true, 2, 4, 8, 1000000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (31, 3, 'Manadela', 'Arouna', '2021-06-25', 'P0000003', true, 4, 4, 7, 500000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (32, 1, 'Kenne', 'Gervais', '2021-06-25', 'P0000004', true, 3, 4, 4, 500000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (33, 7, 'Garba', 'Abdoulaye', '2021-06-25', 'P0000005', true, 1, 4, 5, 400000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (34, 14, 'Djiméné', 'Serges', '2021-06-25', 'P0000006', true, 12, 4, 9, 500000);
INSERT INTO personnel (idpersonnel, idcategorie, nom, prenom, dateembauche, matricule, etat, idresponsabilite, idstructure, idservice, plafond) VALUES (35, 5, 'Abdoulayde', 'ahmadou', '2021-06-25', 'P0000007', true, 13, 4, 7, 500000);


--
-- TOC entry 2776 (class 0 OID 294615)
-- Dependencies: 210
-- Data for Name: prime; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO prime (idprime, montant, indice, point, montantglobal, etat, idpersonnel, idperiode, idsousperiode, idnote, notepersonnelle, observation, relicat, plafond) VALUES (1, 476540.41831543244, 339.17467495760314, 1405, 3000000, false, 21, 1, 15, 6, 1405, NULL, 0, 2000000);
INSERT INTO prime (idprime, montant, indice, point, montantglobal, etat, idpersonnel, idperiode, idsousperiode, idnote, notepersonnelle, observation, relicat, plafond) VALUES (2, 355115.8846806105, 339.17467495760314, 1047, 3000000, false, 22, 1, 15, 7, 1047, NULL, 0, 2000000);
INSERT INTO prime (idprime, montant, indice, point, montantglobal, etat, idpersonnel, idperiode, idsousperiode, idnote, notepersonnelle, observation, relicat, plafond) VALUES (3, 342905.59638213681, 339.17467495760314, 1011, 3000000, false, 23, 1, 15, 8, 1011, NULL, 0, 2000000);
INSERT INTO prime (idprime, montant, indice, point, montantglobal, etat, idpersonnel, idperiode, idsousperiode, idnote, notepersonnelle, observation, relicat, plafond) VALUES (4, 304239.68343696999, 339.17467495760314, 897, 3000000, false, 25, 1, 15, 10, 897, NULL, 0, 2000000);
INSERT INTO prime (idprime, montant, indice, point, montantglobal, etat, idpersonnel, idperiode, idsousperiode, idnote, notepersonnelle, observation, relicat, plafond) VALUES (5, 328321.08535895986, 339.17467495760314, 968, 3000000, false, 26, 1, 15, 11, 968, NULL, 0, 2000000);
INSERT INTO prime (idprime, montant, indice, point, montantglobal, etat, idpersonnel, idperiode, idsousperiode, idnote, notepersonnelle, observation, relicat, plafond) VALUES (6, 355455.05935556808, 339.17467495760314, 1048, 3000000, false, 27, 1, 15, 12, 1048, NULL, 0, 2000000);
INSERT INTO prime (idprime, montant, indice, point, montantglobal, etat, idpersonnel, idperiode, idsousperiode, idnote, notepersonnelle, observation, relicat, plafond) VALUES (7, 354098.36065573769, 339.17467495760314, 1044, 3000000, false, 28, 1, 15, 13, 1044, NULL, 0, 2000000);
INSERT INTO prime (idprime, montant, indice, point, montantglobal, etat, idpersonnel, idperiode, idsousperiode, idnote, notepersonnelle, observation, relicat, plafond) VALUES (8, 483323.91181458445, 339.17467495760314, 1425, 3000000, false, 24, 1, 15, 9, 1425, NULL, 0, 2000000);
INSERT INTO prime (idprime, montant, indice, point, montantglobal, etat, idpersonnel, idperiode, idsousperiode, idnote, notepersonnelle, observation, relicat, plafond) VALUES (9, 268335.45918367343, 478.31632653061223, 561, 2250000, false, 29, 2, 4, 19, 561, 'RAS', 0, 500000);
INSERT INTO prime (idprime, montant, indice, point, montantglobal, etat, idpersonnel, idperiode, idsousperiode, idnote, notepersonnelle, observation, relicat, plafond) VALUES (10, 422353.31632653059, 478.31632653061223, 883, 2250000, false, 30, 2, 4, 20, 883, 'RAS', 0, 1000000);
INSERT INTO prime (idprime, montant, indice, point, montantglobal, etat, idpersonnel, idperiode, idsousperiode, idnote, notepersonnelle, observation, relicat, plafond) VALUES (11, 384566.32653061225, 478.31632653061223, 804, 2250000, false, 31, 2, 4, 18, 804, 'RAS', 0, 500000);
INSERT INTO prime (idprime, montant, indice, point, montantglobal, etat, idpersonnel, idperiode, idsousperiode, idnote, notepersonnelle, observation, relicat, plafond) VALUES (12, 454878.82653061225, 478.31632653061223, 951, 2250000, false, 32, 2, 4, 17, 951, 'RAS', 0, 500000);
INSERT INTO prime (idprime, montant, indice, point, montantglobal, etat, idpersonnel, idperiode, idsousperiode, idnote, notepersonnelle, observation, relicat, plafond) VALUES (13, 168367.3469387755, 478.31632653061223, 352, 2250000, false, 33, 2, 4, 16, 352, 'RAS', 0, 400000);
INSERT INTO prime (idprime, montant, indice, point, montantglobal, etat, idpersonnel, idperiode, idsousperiode, idnote, notepersonnelle, observation, relicat, plafond) VALUES (14, 328125, 478.31632653061223, 686, 2250000, false, 34, 2, 4, 15, 686, 'RAS', 0, 500000);
INSERT INTO prime (idprime, montant, indice, point, montantglobal, etat, idpersonnel, idperiode, idsousperiode, idnote, notepersonnelle, observation, relicat, plafond) VALUES (15, 223373.72448979592, 478.31632653061223, 467, 2250000, false, 35, 2, 4, 14, 467, 'RAS', 0, 500000);


--
-- TOC entry 2759 (class 0 OID 286017)
-- Dependencies: 193
-- Data for Name: privilege; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO privilege (idprivilege, idutilisateur, idmenu) VALUES (1, 2, 1);
INSERT INTO privilege (idprivilege, idutilisateur, idmenu) VALUES (2, 1, 1);
INSERT INTO privilege (idprivilege, idutilisateur, idmenu) VALUES (3, 4, 3);
INSERT INTO privilege (idprivilege, idutilisateur, idmenu) VALUES (4, 4, 4);


--
-- TOC entry 2760 (class 0 OID 286025)
-- Dependencies: 194
-- Data for Name: recette; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (1, 1, 1, 1, 1, 10000000, 76.923076923076934);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (2, 2, 1, 1, 1, 2000000, 15.384615384615385);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (3, 3, 1, 1, 1, 1000000, 7.6923076923076925);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (4, 1, 2, 1, 1, 5000000, 83.333333333333343);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (5, 2, 2, 1, 1, 300000, 5);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (6, 3, 2, 1, 1, 200000, 3.3333333333333335);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (7, 4, 2, 1, 1, 500000, 8.3333333333333321);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (8, 1, 3, 1, 1, 30000000, 91.463414634146346);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (9, 2, 3, 1, 1, 2000000, 6.0975609756097562);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (10, 3, 3, 1, 1, 500000, 1.524390243902439);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (11, 4, 3, 1, 1, 300000, 0.91463414634146334);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (12, 1, 4, 1, 1, 3000000, 60);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (13, 2, 4, 1, 1, 500000, 10);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (14, 3, 4, 1, 1, 500000, 10);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (15, 4, 4, 1, 1, 1000000, 20);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (16, 1, 1, 1, 2, 1000000, 20);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (17, 2, 1, 1, 2, 1500000, 30);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (18, 3, 1, 1, 2, 1500000, 30);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (19, 4, 1, 1, 2, 1000000, 20);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (20, 1, 2, 1, 2, 1500000, 33.333333333333329);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (21, 2, 2, 1, 2, 1000000, 22.222222222222221);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (22, 3, 2, 1, 2, 1000000, 22.222222222222221);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (23, 4, 2, 1, 2, 1000000, 22.222222222222221);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (24, 1, 1, 1, 3, 2000000, 28.571428571428569);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (25, 2, 1, 1, 3, 1000000, 14.285714285714285);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (26, 3, 1, 1, 3, 1000000, 14.285714285714285);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (27, 4, 1, 1, 3, 3000000, 42.857142857142854);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (28, 1, 13, 1, 2, 3000000, 31.578947368421051);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (29, 2, 13, 1, 2, 5000000, 52.631578947368418);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (30, 3, 13, 1, 2, 1000000, 10.526315789473683);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (31, 4, 13, 1, 2, 500000, 5.2631578947368416);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (32, 1, 13, 1, 3, 5000000, 62.5);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (33, 2, 13, 1, 3, 1000000, 12.5);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (34, 3, 13, 1, 3, 1000000, 12.5);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (35, 4, 13, 1, 3, 1000000, 12.5);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (36, 2, 15, 1, 5, 5000000, 33.333333333333329);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (37, 4, 15, 1, 5, 10000000, 66.666666666666657);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (38, 2, 15, 1, 6, 3000000, 37.5);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (39, 4, 15, 1, 6, 5000000, 62.5);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (40, 2, 16, 1, 6, 3000000, 60);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (41, 4, 16, 1, 6, 2000000, 40);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (42, 1, 4, 2, 4, 10500000, 70);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (43, 2, 4, 2, 4, 1500000, 10);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (44, 3, 4, 2, 4, 2250000, 15);
INSERT INTO recette (idrecette, idsousrubriquerecette, idsousperiode, idperiode, idstructure, montant, pourcentage) VALUES (45, 4, 4, 2, 4, 750000, 5);


--
-- TOC entry 2779 (class 0 OID 374882)
-- Dependencies: 213
-- Data for Name: responsabilite; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO responsabilite (idresponsabilite, nom, code) VALUES (5, 'Responsabilité 5', '5');
INSERT INTO responsabilite (idresponsabilite, nom, code) VALUES (6, 'Responsabilité 6', '6');
INSERT INTO responsabilite (idresponsabilite, nom, code) VALUES (7, 'DSRP F1', 'DSRPF1');
INSERT INTO responsabilite (idresponsabilite, nom, code) VALUES (8, 'DSRP F2', 'DSRPF2');
INSERT INTO responsabilite (idresponsabilite, nom, code) VALUES (9, 'DSRP F3', 'DSRPF3');
INSERT INTO responsabilite (idresponsabilite, nom, code) VALUES (10, 'DSRP F4', 'DSRPF4');
INSERT INTO responsabilite (idresponsabilite, nom, code) VALUES (11, 'DSRP F5', 'DSRPF5');
INSERT INTO responsabilite (idresponsabilite, nom, code) VALUES (1, ' -- Aucune --', '1');
INSERT INTO responsabilite (idresponsabilite, nom, code) VALUES (2, 'Directeur', '2');
INSERT INTO responsabilite (idresponsabilite, nom, code) VALUES (3, 'Surveillant général', '3');
INSERT INTO responsabilite (idresponsabilite, nom, code) VALUES (4, 'Econome', '4');
INSERT INTO responsabilite (idresponsabilite, nom, code) VALUES (12, 'Responsable Entretien Matériel', '7');
INSERT INTO responsabilite (idresponsabilite, nom, code) VALUES (13, 'Caissier (e)', '8');


--
-- TOC entry 2761 (class 0 OID 286035)
-- Dependencies: 195
-- Data for Name: rubriquedepense; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO rubriquedepense (idrubriquedepense, nom, code) VALUES (2, 'Rubrique 2', '1.02');
INSERT INTO rubriquedepense (idrubriquedepense, nom, code) VALUES (1, 'Dépense de fonctionnement', '1.01');
INSERT INTO rubriquedepense (idrubriquedepense, nom, code) VALUES (4, 'Autres dépenses', '1.04');
INSERT INTO rubriquedepense (idrubriquedepense, nom, code) VALUES (3, 'Salaires', '1.03');


--
-- TOC entry 2762 (class 0 OID 286044)
-- Dependencies: 196
-- Data for Name: rubriquerecette; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO rubriquerecette (idrubriquerecette, nom, code) VALUES (1, 'Recette interne', '01');
INSERT INTO rubriquerecette (idrubriquerecette, nom, code) VALUES (2, 'Recette externe', '02');


--
-- TOC entry 2777 (class 0 OID 328643)
-- Dependencies: 211
-- Data for Name: rubriquesc; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO rubriquesc (idrubriquesc, nom) VALUES (1, 'Conscience professionnelle');
INSERT INTO rubriquesc (idrubriquesc, nom) VALUES (2, 'Esprit d''équipe');
INSERT INTO rubriquesc (idrubriquesc, nom) VALUES (3, 'Compétence techniques et adaptabilité dans le travail');
INSERT INTO rubriquesc (idrubriquesc, nom) VALUES (4, 'Volonté de développement personnel');
INSERT INTO rubriquesc (idrubriquesc, nom) VALUES (5, '--');


--
-- TOC entry 2763 (class 0 OID 286053)
-- Dependencies: 197
-- Data for Name: service; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO service (idservice, nom, code) VALUES (3, 'Laboratoire', 'S001');
INSERT INTO service (idservice, nom, code) VALUES (4, 'Chirugie', 'S002');
INSERT INTO service (idservice, nom, code) VALUES (5, 'Maternité', 'S003');
INSERT INTO service (idservice, nom, code) VALUES (2, 'Service 2', 'S004');
INSERT INTO service (idservice, nom, code) VALUES (1, 'Service 1', 'S005');
INSERT INTO service (idservice, nom, code) VALUES (6, 'Service 3', 'S006');
INSERT INTO service (idservice, nom, code) VALUES (7, 'Comptabilité', 'S007');
INSERT INTO service (idservice, nom, code) VALUES (8, 'Direction', 'S008');
INSERT INTO service (idservice, nom, code) VALUES (9, 'Entretien', 'S009');


--
-- TOC entry 2766 (class 0 OID 286083)
-- Dependencies: 200
-- Data for Name: souscritere; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO souscritere (idsouscritere, idcritere, nom, code, detail, service, personnel, incitatif, multiplicateur, idrubriquesc, positif, numerateur, denominateur, signe, pointmax) VALUES (7, 7, 'Organisation', '1.07', '- Ne dispose jamais d''un plan journalier d''activités (Constat lors des supervision interne) = 0 points
- Ne dispose pas toujours d''un plan journalier d''activités lors des supervision internes = 4 points
- Dispose d''un plan  d''activités, mais non  respecté lors des supervisions internes = 8 points
- Dispose toujours d''un plan d''activités respecté lors des supervisions internes = 12 points', false, true, false, 0, 3, true, 0, 0, '+', 12);
INSERT INTO souscritere (idsouscritere, idcritere, nom, code, detail, service, personnel, incitatif, multiplicateur, idrubriquesc, positif, numerateur, denominateur, signe, pointmax) VALUES (8, 7, 'Qualité du travail', '1.08', '- Ne respecte pas les normes de ses taches (selon le rapport des supervisions internes) = 0 points
- Ne respecte pas les normes de ses taches (constat fait 2 fois / trimestre lors des supervisions) = 4 points
- Ne respectes pas les normes de ses taches (constat fait 1 fois / trimestre lors des supervisions) = 8 points
- Respecte toujours les normes de ses taches = 12 points', false, true, false, 0, 3, true, 0, 0, '+', 12);
INSERT INTO souscritere (idsouscritere, idcritere, nom, code, detail, service, personnel, incitatif, multiplicateur, idrubriquesc, positif, numerateur, denominateur, signe, pointmax) VALUES (11, 5, 'S critère Qualité 1', '1', '-', NULL, NULL, NULL, NULL, 5, true, NULL, NULL, NULL, 50);
INSERT INTO souscritere (idsouscritere, idcritere, nom, code, detail, service, personnel, incitatif, multiplicateur, idrubriquesc, positif, numerateur, denominateur, signe, pointmax) VALUES (2, 7, 'Disponibilité', '1.02', '- A été absent de son poste sans motif connu / sans permission plus de 7 fois / mois = 0 points
- A été absent de son poste sans motif connu / sans permission(4 à 7 fois / mois) = 3 points
- A été absent de son poste sans motif connu / sans permission(1 à 3 fois / mois) = 6 points
- N''a  jamais été absent de son poste sans motif connu / sans permission = 9 points', false, true, false, 0, 1, true, 0, 0, '+', 9);
INSERT INTO souscritere (idsouscritere, idcritere, nom, code, detail, service, personnel, incitatif, multiplicateur, idrubriquesc, positif, numerateur, denominateur, signe, pointmax) VALUES (3, 7, 'Tenue de travail', '1.03', '- Portée au poste de travail (moins de 4 fois / mois) = 0 points
- Portée quelques fois au poste de travail (plus de 4 fois / mois) = 3 points
- Tenue toujours portée mais négligée (salle ou déchirée ou non repassée) = 6 points
- Tenue toujours portée mais négligée (lavée, repassée et non déchirée) = 9 points', false, true, false, 0, 1, true, 0, 0, '+', 9);
INSERT INTO souscritere (idsouscritere, idcritere, nom, code, detail, service, personnel, incitatif, multiplicateur, idrubriquesc, positif, numerateur, denominateur, signe, pointmax) VALUES (4, 7, 'Relations interpersonnelles', '1.04', '- A été souvent en conflit avec les collègues et rapporté plus de 4 fois / trimestre à la hiérarchie = o points
- A été souvent en conflit avec les collègues et rapporté 2 - 4 fois / trimestre à la hiérarchie = 3 points
- A été en conflit avec les collègues 1 fois / trimestre = 6 points
- N''a jamais été en conflit avec les collègues = 9 points', false, true, false, 0, 2, true, 0, 0, '+', 9);
INSERT INTO souscritere (idsouscritere, idcritere, nom, code, detail, service, personnel, incitatif, multiplicateur, idrubriquesc, positif, numerateur, denominateur, signe, pointmax) VALUES (5, 7, 'Sens de coopération et collaboration', '1.05', '- A plus de 4 fois par trimestre refusé de donner assistance et / ou expertise aux collègues = 0 points
- A 2 - 3 fois par trimestre refusé de donner assistance et /ou expertise aux collègues = 3 points
- A 1 fois par trimestre refusé de donner assistance et /ou expertise aux collègues = 6 points
- N''a jamais refusé de donner assistance et /ou expertise aux collègues = 9 points', false, true, false, 0, 2, true, 0, 0, '+', 9);
INSERT INTO souscritere (idsouscritere, idcritere, nom, code, detail, service, personnel, incitatif, multiplicateur, idrubriquesc, positif, numerateur, denominateur, signe, pointmax) VALUES (6, 7, 'Initiative', '1.06', '- N''a jamais accompli accompli un travail supplémentaire sous - prétexte qu''il n''a pas le temps matériel = 0 points 
- A attendu les ordres de la hiérarchie  pour accomplir au moins un travail supplémentaire = 3 points
- A commencé un travail supplémentaire, mais sans terminer = 6 points
- A accompli au moins travail supplémentaire sans attendre les ordres de la hiérarchie = 9 points', false, true, false, 0, 2, true, 0, 0, '+', 9);
INSERT INTO souscritere (idsouscritere, idcritere, nom, code, detail, service, personnel, incitatif, multiplicateur, idrubriquesc, positif, numerateur, denominateur, signe, pointmax) VALUES (9, 7, 'Quantité du travail', '1.09', '- Ne termine pas 5 fois ou plus par trimestre les taches du plan  journalier (constat supervision) = 0 points
- Ne termine pas 3 à 4 fois par trimestre les taches du plan journalier (constat supervision) = 4 points
- Ne termine pas 1 à 2 fois par trimestre les taches du plan journalier (constat supervision) = 8 points
- Termine toujours les taches définies dans son plan journalier (constat supervision) = 12 points', false, true, false, 0, 4, true, 0, 0, '+', 12);
INSERT INTO souscritere (idsouscritere, idcritere, nom, code, detail, service, personnel, incitatif, multiplicateur, idrubriquesc, positif, numerateur, denominateur, signe, pointmax) VALUES (10, 7, 'Prise en compte des recommandations des supervisions internes et externes précédents', '1.10', '- Ne tiens pas compte des recommandations des supervisions internes et externes précédents plus de 4 fois par trimestre (constat lors des supervisions) = 0 points
- Ne tiens pas compte des recommandations reçues lors de supervisions internes et externes précédentes 2 à 3 fois par trimestre (constat lors des supervisions )  = 3 points
- Ne tient pas compte des recommandations reçues lors des supervisions internes et externes précédentes 1 fois par trimestre (constat lors des supervisions) = 6 points 
- Tiens toujours compte des recommandations des supervisions internes et externes = 10 points', false, true, false, 0, 4, true, 0, 0, '+', 10);
INSERT INTO souscritere (idsouscritere, idcritere, nom, code, detail, service, personnel, incitatif, multiplicateur, idrubriquesc, positif, numerateur, denominateur, signe, pointmax) VALUES (12, 5, 'S critère Qualité 2', '2', '-', NULL, NULL, NULL, NULL, 5, true, NULL, NULL, NULL, 50);
INSERT INTO souscritere (idsouscritere, idcritere, nom, code, detail, service, personnel, incitatif, multiplicateur, idrubriquesc, positif, numerateur, denominateur, signe, pointmax) VALUES (13, 5, 'S critère Qualité 3', '3', '-', NULL, NULL, NULL, NULL, 5, true, NULL, NULL, NULL, 20);
INSERT INTO souscritere (idsouscritere, idcritere, nom, code, detail, service, personnel, incitatif, multiplicateur, idrubriquesc, positif, numerateur, denominateur, signe, pointmax) VALUES (1, 7, 'Ponctualité', '1.01', '- Est arrivé en retard plus de 7 fois / mois = 0 points
- Est arrivé en retard 4 à 7 fois / mois = 3 points
- Est arrivé en retard 1 à 3 fois / mois = 6 points
- N''est jamais arrivé en retard = 9 points', false, true, false, 0, 1, true, 0, 0, '+', 9);


--
-- TOC entry 2767 (class 0 OID 286093)
-- Dependencies: 201
-- Data for Name: souscritereservice; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (1, 4, 11, '-', 50, 1);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (2, 4, 12, '-', 50, 1);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (3, 4, 13, '-', 20, 1);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (4, 3, 11, '-', 50, 1);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (5, 3, 12, '-', 50, 1);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (6, 3, 13, '-', 20, 1);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (7, 4, 11, '-', 50, 3);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (8, 4, 12, '-', 50, 3);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (9, 3, 11, '-', 50, 3);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (10, 3, 13, '-', 20, 3);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (11, 5, 11, '-', 50, 3);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (12, 5, 12, '-', 50, 3);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (13, 1, 12, '-', 50, 3);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (14, 1, 13, '-', 20, 3);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (15, 2, 11, '-', 50, 3);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (16, 2, 12, '-', 50, 3);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (17, 2, 13, '-', 20, 3);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (18, 6, 11, '-', 50, 3);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (19, 6, 12, '-', 50, 3);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (20, 6, 13, '-', 20, 3);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (21, 1, 11, '-', 50, 5);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (22, 1, 12, '-', 50, 5);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (23, 1, 13, '-', 20, 5);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (24, 2, 11, '-', 50, 5);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (25, 2, 12, '-', 50, 5);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (26, 2, 13, '-', 20, 5);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (27, 6, 11, '-', 50, 5);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (28, 6, 12, '-', 50, 5);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (29, 6, 13, '-', 20, 5);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (30, 1, 11, '-', 50, 6);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (31, 1, 12, '-', 50, 6);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (32, 1, 13, '-', 20, 6);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (33, 2, 11, '-', 50, 6);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (34, 2, 12, '-', 50, 6);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (35, 2, 13, '-', 20, 6);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (36, 6, 11, '-', 50, 6);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (37, 6, 12, '-', 50, 6);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (38, 6, 13, '-', 20, 6);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (39, 4, 11, '-', 50, 4);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (40, 4, 13, '-', 20, 4);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (41, 7, 11, '-', 50, 4);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (42, 7, 12, '-', 50, 4);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (43, 7, 13, '-', 20, 4);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (44, 8, 11, '-', 50, 4);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (45, 8, 12, '-', 50, 4);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (46, 8, 13, '-', 20, 4);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (47, 9, 11, '-', 50, 4);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (48, 9, 13, '-', 20, 4);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (49, 3, 11, '-', 50, 4);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (50, 3, 12, '-', 50, 4);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (51, 3, 13, '-', 20, 4);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (52, 5, 11, '-', 50, 4);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (53, 5, 12, '-', 50, 4);
INSERT INTO souscritereservice (idsouscritereservice, idservice, idsouscritere, detail, pointmax, idstructure) VALUES (54, 5, 13, '-', 20, 4);


--
-- TOC entry 2768 (class 0 OID 286101)
-- Dependencies: 202
-- Data for Name: sousperiode; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO sousperiode (idsousperiode, nom, code, numero, idtypesousperiode) VALUES (1, 'Janvier', 'M1', 1, 4);
INSERT INTO sousperiode (idsousperiode, nom, code, numero, idtypesousperiode) VALUES (2, 'Février', 'M2', 2, 4);
INSERT INTO sousperiode (idsousperiode, nom, code, numero, idtypesousperiode) VALUES (3, 'Mars', 'M3', 3, 4);
INSERT INTO sousperiode (idsousperiode, nom, code, numero, idtypesousperiode) VALUES (4, 'Avril', 'M4', 4, 4);
INSERT INTO sousperiode (idsousperiode, nom, code, numero, idtypesousperiode) VALUES (5, 'Mai', 'M5', 5, 4);
INSERT INTO sousperiode (idsousperiode, nom, code, numero, idtypesousperiode) VALUES (6, 'Juin', 'M6', 6, 4);
INSERT INTO sousperiode (idsousperiode, nom, code, numero, idtypesousperiode) VALUES (7, 'Juillet', 'M7', 7, 4);
INSERT INTO sousperiode (idsousperiode, nom, code, numero, idtypesousperiode) VALUES (8, 'Aout', 'M8', 8, 4);
INSERT INTO sousperiode (idsousperiode, nom, code, numero, idtypesousperiode) VALUES (9, 'Septembre', 'M9', 9, 4);
INSERT INTO sousperiode (idsousperiode, nom, code, numero, idtypesousperiode) VALUES (10, 'Octobre', 'M10', 10, 4);
INSERT INTO sousperiode (idsousperiode, nom, code, numero, idtypesousperiode) VALUES (11, 'Novembre', 'M11', 11, 4);
INSERT INTO sousperiode (idsousperiode, nom, code, numero, idtypesousperiode) VALUES (12, 'Décembre', 'M12', 12, 4);
INSERT INTO sousperiode (idsousperiode, nom, code, numero, idtypesousperiode) VALUES (13, 'Semestre 1', 'S1', NULL, 2);
INSERT INTO sousperiode (idsousperiode, nom, code, numero, idtypesousperiode) VALUES (14, 'Semestre 2', 'S2', NULL, 2);
INSERT INTO sousperiode (idsousperiode, nom, code, numero, idtypesousperiode) VALUES (15, 'Trimestre 1', 'T1', NULL, 3);
INSERT INTO sousperiode (idsousperiode, nom, code, numero, idtypesousperiode) VALUES (16, 'Trimestre 2', 'T2', NULL, 3);
INSERT INTO sousperiode (idsousperiode, nom, code, numero, idtypesousperiode) VALUES (17, 'Trimestre 3', 'T3', NULL, 3);


--
-- TOC entry 2764 (class 0 OID 286063)
-- Dependencies: 198
-- Data for Name: sousrubriquedepense; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO sousrubriquedepense (idsousrubriquedepense, idrubriquedepense, nom, code, special) VALUES (1, 3, 'Salaire de base payés par la FOSA', '1.01', false);
INSERT INTO sousrubriquedepense (idsousrubriquedepense, idrubriquedepense, nom, code, special) VALUES (4, 3, 'Salaire de base payés par le gouvernement', '1.04', false);
INSERT INTO sousrubriquedepense (idsousrubriquedepense, idrubriquedepense, nom, code, special) VALUES (3, 4, 'Prime du personnel', '1.03', true);
INSERT INTO sousrubriquedepense (idsousrubriquedepense, idrubriquedepense, nom, code, special) VALUES (2, 4, 'Augmentation de la réserve bancaire', '1.02', false);
INSERT INTO sousrubriquedepense (idsousrubriquedepense, idrubriquedepense, nom, code, special) VALUES (5, 1, 'Médicaments et matériel médical', '1.05', false);


--
-- TOC entry 2765 (class 0 OID 286073)
-- Dependencies: 199
-- Data for Name: sousrubriquerecette; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO sousrubriquerecette (idsousrubriquerecette, idrubriquerecette, nom, code) VALUES (1, 1, 'Recouvrement de couts direct (Actes + Médicament)', 'SR1.01');
INSERT INTO sousrubriquerecette (idsousrubriquerecette, idrubriquerecette, nom, code) VALUES (2, 2, 'Subsides PBF', 'SR1.02');
INSERT INTO sousrubriquerecette (idsousrubriquerecette, idrubriquerecette, nom, code) VALUES (3, 2, 'Assurance maladies', 'SR1.03');
INSERT INTO sousrubriquerecette (idsousrubriquerecette, idrubriquerecette, nom, code) VALUES (4, 2, 'Autres recettes (activités génératrices de recettes)', 'SR1.04');


--
-- TOC entry 2773 (class 0 OID 286148)
-- Dependencies: 207
-- Data for Name: statutstructure; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO statutstructure (idstatutstructure, nom, etat) VALUES (1, '--', 'Actif');
INSERT INTO statutstructure (idstatutstructure, nom, etat) VALUES (2, 'Publique', 'Actif');
INSERT INTO statutstructure (idstatutstructure, nom, etat) VALUES (3, 'Privé', 'Actif');


--
-- TOC entry 2769 (class 0 OID 286110)
-- Dependencies: 203
-- Data for Name: structure; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO structure (idstructure, idinstitution, idtypestructure, idstatutstructure, nom, code, article, nomrespo, arretecreation, designation, presentation, etat, dateouverture, vision, axeintervention, objectifgeneral, objectifspecifique) VALUES (3, 1, 4, 2, 'Hopital central de yaoundé', 'HCY', NULL, '-', '-', NULL, '-', 'Actif', NULL, '-', '-', '-', '-');
INSERT INTO structure (idstructure, idinstitution, idtypestructure, idstatutstructure, nom, code, article, nomrespo, arretecreation, designation, presentation, etat, dateouverture, vision, axeintervention, objectifgeneral, objectifspecifique) VALUES (2, 1, 4, 2, 'Hopital général de yaoundé', 'HGY', NULL, '-', '-', NULL, '-', 'Actif', NULL, '-', '-', '-', '-');
INSERT INTO structure (idstructure, idinstitution, idtypestructure, idstatutstructure, nom, code, article, nomrespo, arretecreation, designation, presentation, etat, dateouverture, vision, axeintervention, objectifgeneral, objectifspecifique) VALUES (1, 1, 3, 1, 'Hopital de District de Cité - Verte', 'HD_CV', '70', '--', '-', NULL, '-', 'Actif', NULL, '-', NULL, '-', '-');
INSERT INTO structure (idstructure, idinstitution, idtypestructure, idstatutstructure, nom, code, article, nomrespo, arretecreation, designation, presentation, etat, dateouverture, vision, axeintervention, objectifgeneral, objectifspecifique) VALUES (4, 1, 3, 2, 'Hopital de district de byem - assi', 'HD BYEM-ASSI', NULL, '-', '-', NULL, '-', 'Actif', NULL, '-', '-', '-', '-');
INSERT INTO structure (idstructure, idinstitution, idtypestructure, idstatutstructure, nom, code, article, nomrespo, arretecreation, designation, presentation, etat, dateouverture, vision, axeintervention, objectifgeneral, objectifspecifique) VALUES (5, 1, 5, 2, 'DRSP CENTRE', 'DRSP-CE', NULL, '-', '-', NULL, '-', 'Actif', NULL, '-', '-', '-', '-');
INSERT INTO structure (idstructure, idinstitution, idtypestructure, idstatutstructure, nom, code, article, nomrespo, arretecreation, designation, presentation, etat, dateouverture, vision, axeintervention, objectifgeneral, objectifspecifique) VALUES (6, 1, 5, 2, 'DRSP LITTORAL', 'DRSP-LIT', NULL, '-', '-', NULL, '-', 'Actif', NULL, '-', '-', '-', '-');


--
-- TOC entry 2802 (class 0 OID 467397)
-- Dependencies: 236
-- Data for Name: typesousperiode; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO typesousperiode (idtypesousperiode, nom, code) VALUES (1, 'Annuel', '1');
INSERT INTO typesousperiode (idtypesousperiode, nom, code) VALUES (2, 'Sémestriel', '2');
INSERT INTO typesousperiode (idtypesousperiode, nom, code) VALUES (3, 'Trimestriel', '3');
INSERT INTO typesousperiode (idtypesousperiode, nom, code) VALUES (4, 'Mensuel', '4');


--
-- TOC entry 2774 (class 0 OID 286157)
-- Dependencies: 208
-- Data for Name: typestructure; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO typestructure (idtypestructure, nom, etat) VALUES (1, '--', 'Actif');
INSERT INTO typestructure (idtypestructure, nom, etat) VALUES (3, 'Hopital de district', 'Actif');
INSERT INTO typestructure (idtypestructure, nom, etat) VALUES (2, 'Hopital régional', 'Actif');
INSERT INTO typestructure (idtypestructure, nom, etat) VALUES (4, 'Hopital de reference', 'Actif');
INSERT INTO typestructure (idtypestructure, nom, etat) VALUES (5, 'DRSP', 'Actif');


--
-- TOC entry 2804 (class 0 OID 467423)
-- Dependencies: 238
-- Data for Name: typestructurecategorie; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (1, 3, 1);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (2, 3, 10);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (3, 3, 2);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (4, 3, 3);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (5, 4, 1);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (6, 4, 10);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (7, 4, 2);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (8, 4, 3);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (9, 4, 4);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (10, 4, 5);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (11, 4, 6);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (12, 4, 7);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (13, 4, 8);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (14, 4, 9);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (15, 2, 1);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (16, 2, 10);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (17, 2, 2);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (18, 2, 3);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (19, 2, 4);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (20, 2, 5);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (21, 2, 6);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (22, 2, 7);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (23, 2, 8);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (24, 2, 9);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (25, 5, 11);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (26, 5, 12);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (27, 5, 13);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (28, 3, 4);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (29, 3, 14);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (30, 3, 5);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (31, 3, 6);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (32, 3, 7);
INSERT INTO typestructurecategorie (idtypestructurecategorie, idtypestructure, idcategorie) VALUES (33, 3, 8);


--
-- TOC entry 2805 (class 0 OID 475574)
-- Dependencies: 239
-- Data for Name: typestructureresponsabilite; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO typestructureresponsabilite (idtypestructureresponsabilite, idtypestructure, idresponsabilite) VALUES (1, 3, 1);
INSERT INTO typestructureresponsabilite (idtypestructureresponsabilite, idtypestructure, idresponsabilite) VALUES (2, 3, 2);
INSERT INTO typestructureresponsabilite (idtypestructureresponsabilite, idtypestructure, idresponsabilite) VALUES (3, 3, 3);
INSERT INTO typestructureresponsabilite (idtypestructureresponsabilite, idtypestructure, idresponsabilite) VALUES (4, 4, 4);
INSERT INTO typestructureresponsabilite (idtypestructureresponsabilite, idtypestructure, idresponsabilite) VALUES (5, 4, 5);
INSERT INTO typestructureresponsabilite (idtypestructureresponsabilite, idtypestructure, idresponsabilite) VALUES (6, 4, 6);
INSERT INTO typestructureresponsabilite (idtypestructureresponsabilite, idtypestructure, idresponsabilite) VALUES (7, 2, 3);
INSERT INTO typestructureresponsabilite (idtypestructureresponsabilite, idtypestructure, idresponsabilite) VALUES (8, 2, 4);
INSERT INTO typestructureresponsabilite (idtypestructureresponsabilite, idtypestructure, idresponsabilite) VALUES (9, 2, 5);
INSERT INTO typestructureresponsabilite (idtypestructureresponsabilite, idtypestructure, idresponsabilite) VALUES (10, 5, 7);
INSERT INTO typestructureresponsabilite (idtypestructureresponsabilite, idtypestructure, idresponsabilite) VALUES (11, 5, 8);
INSERT INTO typestructureresponsabilite (idtypestructureresponsabilite, idtypestructure, idresponsabilite) VALUES (12, 5, 9);
INSERT INTO typestructureresponsabilite (idtypestructureresponsabilite, idtypestructure, idresponsabilite) VALUES (13, 5, 10);
INSERT INTO typestructureresponsabilite (idtypestructureresponsabilite, idtypestructure, idresponsabilite) VALUES (14, 3, 4);
INSERT INTO typestructureresponsabilite (idtypestructureresponsabilite, idtypestructure, idresponsabilite) VALUES (15, 3, 12);
INSERT INTO typestructureresponsabilite (idtypestructureresponsabilite, idtypestructure, idresponsabilite) VALUES (16, 3, 13);


--
-- TOC entry 2801 (class 0 OID 467382)
-- Dependencies: 235
-- Data for Name: typestructureservice; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO typestructureservice (idtypestructureservice, idtypestructure, idservice) VALUES (1, 3, 3);
INSERT INTO typestructureservice (idtypestructureservice, idtypestructure, idservice) VALUES (2, 3, 4);
INSERT INTO typestructureservice (idtypestructureservice, idtypestructure, idservice) VALUES (3, 3, 5);
INSERT INTO typestructureservice (idtypestructureservice, idtypestructure, idservice) VALUES (4, 4, 3);
INSERT INTO typestructureservice (idtypestructureservice, idtypestructure, idservice) VALUES (5, 4, 4);
INSERT INTO typestructureservice (idtypestructureservice, idtypestructure, idservice) VALUES (6, 4, 5);
INSERT INTO typestructureservice (idtypestructureservice, idtypestructure, idservice) VALUES (7, 2, 3);
INSERT INTO typestructureservice (idtypestructureservice, idtypestructure, idservice) VALUES (8, 2, 4);
INSERT INTO typestructureservice (idtypestructureservice, idtypestructure, idservice) VALUES (9, 2, 5);
INSERT INTO typestructureservice (idtypestructureservice, idtypestructure, idservice) VALUES (10, 5, 2);
INSERT INTO typestructureservice (idtypestructureservice, idtypestructure, idservice) VALUES (11, 5, 1);
INSERT INTO typestructureservice (idtypestructureservice, idtypestructure, idservice) VALUES (12, 5, 6);
INSERT INTO typestructureservice (idtypestructureservice, idtypestructure, idservice) VALUES (13, 3, 7);
INSERT INTO typestructureservice (idtypestructureservice, idtypestructure, idservice) VALUES (14, 3, 8);
INSERT INTO typestructureservice (idtypestructureservice, idtypestructure, idservice) VALUES (15, 3, 9);


--
-- TOC entry 2806 (class 0 OID 475589)
-- Dependencies: 240
-- Data for Name: typestructuresousrubriquedepense; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO typestructuresousrubriquedepense (id, idtypestructure, idsousrubriquedepense) VALUES (1, 3, 4);
INSERT INTO typestructuresousrubriquedepense (id, idtypestructure, idsousrubriquedepense) VALUES (2, 3, 5);
INSERT INTO typestructuresousrubriquedepense (id, idtypestructure, idsousrubriquedepense) VALUES (3, 3, 3);
INSERT INTO typestructuresousrubriquedepense (id, idtypestructure, idsousrubriquedepense) VALUES (4, 3, 2);
INSERT INTO typestructuresousrubriquedepense (id, idtypestructure, idsousrubriquedepense) VALUES (5, 3, 1);
INSERT INTO typestructuresousrubriquedepense (id, idtypestructure, idsousrubriquedepense) VALUES (6, 4, 1);
INSERT INTO typestructuresousrubriquedepense (id, idtypestructure, idsousrubriquedepense) VALUES (7, 4, 2);
INSERT INTO typestructuresousrubriquedepense (id, idtypestructure, idsousrubriquedepense) VALUES (8, 4, 3);
INSERT INTO typestructuresousrubriquedepense (id, idtypestructure, idsousrubriquedepense) VALUES (9, 4, 4);
INSERT INTO typestructuresousrubriquedepense (id, idtypestructure, idsousrubriquedepense) VALUES (10, 4, 5);
INSERT INTO typestructuresousrubriquedepense (id, idtypestructure, idsousrubriquedepense) VALUES (11, 2, 1);
INSERT INTO typestructuresousrubriquedepense (id, idtypestructure, idsousrubriquedepense) VALUES (12, 2, 2);
INSERT INTO typestructuresousrubriquedepense (id, idtypestructure, idsousrubriquedepense) VALUES (13, 2, 3);
INSERT INTO typestructuresousrubriquedepense (id, idtypestructure, idsousrubriquedepense) VALUES (14, 2, 4);
INSERT INTO typestructuresousrubriquedepense (id, idtypestructure, idsousrubriquedepense) VALUES (15, 2, 5);
INSERT INTO typestructuresousrubriquedepense (id, idtypestructure, idsousrubriquedepense) VALUES (16, 5, 1);
INSERT INTO typestructuresousrubriquedepense (id, idtypestructure, idsousrubriquedepense) VALUES (17, 5, 2);
INSERT INTO typestructuresousrubriquedepense (id, idtypestructure, idsousrubriquedepense) VALUES (18, 5, 3);


--
-- TOC entry 2807 (class 0 OID 475604)
-- Dependencies: 241
-- Data for Name: typestructuresousrubriquerecette; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO typestructuresousrubriquerecette (id, idtypestructure, idsousrubriquerecette) VALUES (1, 3, 1);
INSERT INTO typestructuresousrubriquerecette (id, idtypestructure, idsousrubriquerecette) VALUES (2, 3, 2);
INSERT INTO typestructuresousrubriquerecette (id, idtypestructure, idsousrubriquerecette) VALUES (3, 3, 3);
INSERT INTO typestructuresousrubriquerecette (id, idtypestructure, idsousrubriquerecette) VALUES (4, 3, 4);
INSERT INTO typestructuresousrubriquerecette (id, idtypestructure, idsousrubriquerecette) VALUES (5, 4, 1);
INSERT INTO typestructuresousrubriquerecette (id, idtypestructure, idsousrubriquerecette) VALUES (6, 4, 2);
INSERT INTO typestructuresousrubriquerecette (id, idtypestructure, idsousrubriquerecette) VALUES (7, 4, 3);
INSERT INTO typestructuresousrubriquerecette (id, idtypestructure, idsousrubriquerecette) VALUES (8, 4, 4);
INSERT INTO typestructuresousrubriquerecette (id, idtypestructure, idsousrubriquerecette) VALUES (9, 2, 1);
INSERT INTO typestructuresousrubriquerecette (id, idtypestructure, idsousrubriquerecette) VALUES (10, 2, 2);
INSERT INTO typestructuresousrubriquerecette (id, idtypestructure, idsousrubriquerecette) VALUES (11, 2, 3);
INSERT INTO typestructuresousrubriquerecette (id, idtypestructure, idsousrubriquerecette) VALUES (12, 2, 4);
INSERT INTO typestructuresousrubriquerecette (id, idtypestructure, idsousrubriquerecette) VALUES (13, 5, 2);
INSERT INTO typestructuresousrubriquerecette (id, idtypestructure, idsousrubriquerecette) VALUES (14, 5, 4);


--
-- TOC entry 2803 (class 0 OID 467408)
-- Dependencies: 237
-- Data for Name: typestructuretypesousperiode; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO typestructuretypesousperiode (idtypestructuretypesousperiode, idtypestructure, idtypesousperiode) VALUES (1, 3, 4);
INSERT INTO typestructuretypesousperiode (idtypestructuretypesousperiode, idtypestructure, idtypesousperiode) VALUES (2, 2, 4);
INSERT INTO typestructuretypesousperiode (idtypestructuretypesousperiode, idtypestructure, idtypesousperiode) VALUES (3, 4, 3);
INSERT INTO typestructuretypesousperiode (idtypestructuretypesousperiode, idtypestructure, idtypesousperiode) VALUES (4, 4, 2);
INSERT INTO typestructuretypesousperiode (idtypestructuretypesousperiode, idtypestructure, idtypesousperiode) VALUES (5, 4, 4);
INSERT INTO typestructuretypesousperiode (idtypestructuretypesousperiode, idtypestructure, idtypesousperiode) VALUES (6, 5, 3);


--
-- TOC entry 2770 (class 0 OID 286122)
-- Dependencies: 204
-- Data for Name: utilisateur; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO utilisateur (idutilisateur, nom, prenom, login, password, photo, actif, template, theme, datecreation, datederniereconnexion, heurederniereconnexion) VALUES (1, 'Kenne', 'Gervais', 'admin', '0DPiKuNIrrVmD8IUCuw1hQxNqZc=', 'user_avatar.png', true, NULL, 'hot-sneaks', NULL, NULL, NULL);
INSERT INTO utilisateur (idutilisateur, nom, prenom, login, password, photo, actif, template, theme, datecreation, datederniereconnexion, heurederniereconnexion) VALUES (2, 'Pouamoun', 'Abdel', 'abdel', '4W57tAauz4z00EN4RXZRSWk9wHY=', 'user_avatar.png', true, NULL, 'hot-sneaks', NULL, NULL, NULL);
INSERT INTO utilisateur (idutilisateur, nom, prenom, login, password, photo, actif, template, theme, datecreation, datederniereconnexion, heurederniereconnexion) VALUES (3, 'Tatouo', 'Patrice', 'patrice', '7a3QdxxCFs7YGiOhon1tTj0dM/o=', 'user_avatar.png', true, NULL, 'hot-sneaks', NULL, NULL, NULL);
INSERT INTO utilisateur (idutilisateur, nom, prenom, login, password, photo, actif, template, theme, datecreation, datederniereconnexion, heurederniereconnexion) VALUES (4, 'Mandela', 'Arouna Nelson', 'arouna', 'DkKdAeknMYdJyv9Xay4F8BMPoq0=', 'user_avatar.png', true, NULL, 'hot-sneaks', NULL, NULL, NULL);


--
-- TOC entry 2771 (class 0 OID 286131)
-- Dependencies: 205
-- Data for Name: utilisateurstructure; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO utilisateurstructure (idutilisateur, idstructure) VALUES (3, 1);
INSERT INTO utilisateurstructure (idutilisateur, idstructure) VALUES (1, 1);
INSERT INTO utilisateurstructure (idutilisateur, idstructure) VALUES (4, 1);
INSERT INTO utilisateurstructure (idutilisateur, idstructure) VALUES (1, 2);
INSERT INTO utilisateurstructure (idutilisateur, idstructure) VALUES (1, 3);
INSERT INTO utilisateurstructure (idutilisateur, idstructure) VALUES (1, 4);
INSERT INTO utilisateurstructure (idutilisateur, idstructure) VALUES (1, 5);
INSERT INTO utilisateurstructure (idutilisateur, idstructure) VALUES (1, 6);


--
-- TOC entry 2507 (class 2606 OID 475578)
-- Name: fk_typestructureresponsabilite; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typestructureresponsabilite
    ADD CONSTRAINT fk_typestructureresponsabilite PRIMARY KEY (idtypestructureresponsabilite);


--
-- TOC entry 2509 (class 2606 OID 475593)
-- Name: pjk_typestructuresousrubriquedepense; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typestructuresousrubriquedepense
    ADD CONSTRAINT pjk_typestructuresousrubriquedepense PRIMARY KEY (id);


--
-- TOC entry 2287 (class 2606 OID 285901)
-- Name: pk_categorie; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY categorie
    ADD CONSTRAINT pk_categorie PRIMARY KEY (idcategorie);


--
-- TOC entry 2292 (class 2606 OID 285907)
-- Name: pk_categoriestructure; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY categoriestructure
    ADD CONSTRAINT pk_categoriestructure PRIMARY KEY (idstructure, idcategorie);


--
-- TOC entry 2429 (class 2606 OID 374902)
-- Name: pk_cible; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY cible
    ADD CONSTRAINT pk_cible PRIMARY KEY (idcible);


--
-- TOC entry 2295 (class 2606 OID 285918)
-- Name: pk_critere; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY critere
    ADD CONSTRAINT pk_critere PRIMARY KEY (idcritere);


--
-- TOC entry 2431 (class 2606 OID 374927)
-- Name: pk_critereresponsabilite; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY critereresponsabilite
    ADD CONSTRAINT pk_critereresponsabilite PRIMARY KEY (idcritereresponsabilite);


--
-- TOC entry 2517 (class 2606 OID 475670)
-- Name: pk_critereservice; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY critereservice
    ADD CONSTRAINT pk_critereservice PRIMARY KEY (idservice, idcritere);


--
-- TOC entry 2300 (class 2606 OID 285932)
-- Name: pk_criterestructure; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY criterestructure
    ADD CONSTRAINT pk_criterestructure PRIMARY KEY (idstructure, idcritere);


--
-- TOC entry 2307 (class 2606 OID 285940)
-- Name: pk_depense; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY depense
    ADD CONSTRAINT pk_depense PRIMARY KEY (iddepense);


--
-- TOC entry 2403 (class 2606 OID 286143)
-- Name: pk_detailsc; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY detailsc
    ADD CONSTRAINT pk_detailsc PRIMARY KEY (iddetailsc);


--
-- TOC entry 2493 (class 2606 OID 442808)
-- Name: pk_effectif_responsabilite; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY effectifresponsabilite
    ADD CONSTRAINT pk_effectif_responsabilite PRIMARY KEY (ideffectifresponsabilite);


--
-- TOC entry 2491 (class 2606 OID 442793)
-- Name: pk_effectifcategorie; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY effectifcategorie
    ADD CONSTRAINT pk_effectifcategorie PRIMARY KEY (ideffectifcategorie);


--
-- TOC entry 2421 (class 2606 OID 346607)
-- Name: pk_element_reponse; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY elementreponse
    ADD CONSTRAINT pk_element_reponse PRIMARY KEY (idelementreponse);


--
-- TOC entry 2445 (class 2606 OID 399467)
-- Name: pk_evaluationbonuspp; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationbonuspp
    ADD CONSTRAINT pk_evaluationbonuspp PRIMARY KEY (idevaluationbonuspp);


--
-- TOC entry 2467 (class 2606 OID 399487)
-- Name: pk_evaluationbonusrdeptpersonnel; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationbonusrdeptpersonnel
    ADD CONSTRAINT pk_evaluationbonusrdeptpersonnel PRIMARY KEY (idevaluationbonusrdeptpersonnel);


--
-- TOC entry 2437 (class 2606 OID 391270)
-- Name: pk_evaluationheuresupp; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationheuresupp
    ADD CONSTRAINT pk_evaluationheuresupp PRIMARY KEY (idevaluationheuresupp);


--
-- TOC entry 2473 (class 2606 OID 400202)
-- Name: pk_evaluationpenalitedept; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationpenalitedept
    ADD CONSTRAINT pk_evaluationpenalitedept PRIMARY KEY (idevaluationpenalitedept);


--
-- TOC entry 2486 (class 2606 OID 416591)
-- Name: pk_evaluationpenalitepersonnel; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationpenalitepersonnel
    ADD CONSTRAINT pk_evaluationpenalitepersonnel PRIMARY KEY (idevaluationpenalitepersonnel);


--
-- TOC entry 2313 (class 2606 OID 285958)
-- Name: pk_evaluationpersonnel; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationpersonnel
    ADD CONSTRAINT pk_evaluationpersonnel PRIMARY KEY (idevaluationpersonnel);


--
-- TOC entry 2441 (class 2606 OID 399462)
-- Name: pk_evaluationresponsabilite; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationresponsabilite
    ADD CONSTRAINT pk_evaluationresponsabilite PRIMARY KEY (idevaluationresponsabilite);


--
-- TOC entry 2457 (class 2606 OID 399477)
-- Name: pk_evaluationrprimeqltifdept; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationrprimeqltifdept
    ADD CONSTRAINT pk_evaluationrprimeqltifdept PRIMARY KEY (idevaluationrprimeqltifdept);


--
-- TOC entry 2462 (class 2606 OID 399482)
-- Name: pk_evaluationrprimeqltifpersonnel; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationrprimeqltifpersonnel
    ADD CONSTRAINT pk_evaluationrprimeqltifpersonnel PRIMARY KEY (idevaluationrprimeqltifpersonnel);


--
-- TOC entry 2450 (class 2606 OID 399472)
-- Name: pk_evaluationrqntifdept; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationrqntifdept
    ADD CONSTRAINT pk_evaluationrqntifdept PRIMARY KEY (idevaluationrqntifdept);


--
-- TOC entry 2425 (class 2606 OID 374897)
-- Name: pk_indicateur; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY indicateur
    ADD CONSTRAINT pk_indicateur PRIMARY KEY (idindicateur);


--
-- TOC entry 2497 (class 2606 OID 459194)
-- Name: pk_indicateurqteservice; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY indicateurqteservice
    ADD CONSTRAINT pk_indicateurqteservice PRIMARY KEY (idindicateurqteservice);


--
-- TOC entry 2316 (class 2606 OID 285971)
-- Name: pk_institution; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY institution
    ADD CONSTRAINT pk_institution PRIMARY KEY (idinstitution);


--
-- TOC entry 2479 (class 2606 OID 408404)
-- Name: pk_lignepenalitedept; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY lignepenalitedept
    ADD CONSTRAINT pk_lignepenalitedept PRIMARY KEY (idlignepenalitedept);


--
-- TOC entry 2489 (class 2606 OID 416596)
-- Name: pk_lignepenalitepersonnel; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY lignepenalitepersonnel
    ADD CONSTRAINT pk_lignepenalitepersonnel PRIMARY KEY (idlignepenalitepersonnel);


--
-- TOC entry 2483 (class 2606 OID 408409)
-- Name: pk_ligneprimequalitedept; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ligneprimequalitedept
    ADD CONSTRAINT pk_ligneprimequalitedept PRIMARY KEY (idligneprimequalitedept);


--
-- TOC entry 2319 (class 2606 OID 285980)
-- Name: pk_menu; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY menu
    ADD CONSTRAINT pk_menu PRIMARY KEY (idmenu);


--
-- TOC entry 2323 (class 2606 OID 285986)
-- Name: pk_mouchard; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY mouchard
    ADD CONSTRAINT pk_mouchard PRIMARY KEY (idmouchard);


--
-- TOC entry 2329 (class 2606 OID 285993)
-- Name: pk_note; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY note
    ADD CONSTRAINT pk_note PRIMARY KEY (idnote);


--
-- TOC entry 2413 (class 2606 OID 294568)
-- Name: pk_noteservice; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY noteservice
    ADD CONSTRAINT pk_noteservice PRIMARY KEY (idnoteservice);


--
-- TOC entry 2433 (class 2606 OID 374947)
-- Name: pk_parametragecritere; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY parametragecritere
    ADD CONSTRAINT pk_parametragecritere PRIMARY KEY (idparametragecritere);


--
-- TOC entry 2495 (class 2606 OID 459158)
-- Name: pk_parametragepenalite; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY parametragepenalite
    ADD CONSTRAINT pk_parametragepenalite PRIMARY KEY (idparametragepenalite);


--
-- TOC entry 2475 (class 2606 OID 408399)
-- Name: pk_penalite; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY penalite
    ADD CONSTRAINT pk_penalite PRIMARY KEY (idpenalite);


--
-- TOC entry 2332 (class 2606 OID 286004)
-- Name: pk_periode; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY periode
    ADD CONSTRAINT pk_periode PRIMARY KEY (idperiode);


--
-- TOC entry 2339 (class 2606 OID 286013)
-- Name: pk_personnel; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY personnel
    ADD CONSTRAINT pk_personnel PRIMARY KEY (idpersonnel);


--
-- TOC entry 2416 (class 2606 OID 294619)
-- Name: pk_prime; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY prime
    ADD CONSTRAINT pk_prime PRIMARY KEY (idprime);


--
-- TOC entry 2343 (class 2606 OID 286021)
-- Name: pk_privilege; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY privilege
    ADD CONSTRAINT pk_privilege PRIMARY KEY (idprivilege);


--
-- TOC entry 2350 (class 2606 OID 286029)
-- Name: pk_recette; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY recette
    ADD CONSTRAINT pk_recette PRIMARY KEY (idrecette);


--
-- TOC entry 2423 (class 2606 OID 374886)
-- Name: pk_responsabilite; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY responsabilite
    ADD CONSTRAINT pk_responsabilite PRIMARY KEY (idresponsabilite);


--
-- TOC entry 2353 (class 2606 OID 286042)
-- Name: pk_rubriquedepense; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY rubriquedepense
    ADD CONSTRAINT pk_rubriquedepense PRIMARY KEY (idrubriquedepense);


--
-- TOC entry 2356 (class 2606 OID 286051)
-- Name: pk_rubriquerecette; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY rubriquerecette
    ADD CONSTRAINT pk_rubriquerecette PRIMARY KEY (idrubriquerecette);


--
-- TOC entry 2418 (class 2606 OID 328650)
-- Name: pk_rubriquesc; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY rubriquesc
    ADD CONSTRAINT pk_rubriquesc PRIMARY KEY (idrubriquesc);


--
-- TOC entry 2359 (class 2606 OID 286060)
-- Name: pk_service; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY service
    ADD CONSTRAINT pk_service PRIMARY KEY (idservice);


--
-- TOC entry 2372 (class 2606 OID 286090)
-- Name: pk_souscritere; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY souscritere
    ADD CONSTRAINT pk_souscritere PRIMARY KEY (idsouscritere);


--
-- TOC entry 2378 (class 2606 OID 286097)
-- Name: pk_souscritereservice; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY souscritereservice
    ADD CONSTRAINT pk_souscritereservice PRIMARY KEY (idsouscritereservice);


--
-- TOC entry 2382 (class 2606 OID 286108)
-- Name: pk_sousperiode; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY sousperiode
    ADD CONSTRAINT pk_sousperiode PRIMARY KEY (idsousperiode);


--
-- TOC entry 2363 (class 2606 OID 286070)
-- Name: pk_sousrubriquedepense; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY sousrubriquedepense
    ADD CONSTRAINT pk_sousrubriquedepense PRIMARY KEY (idsousrubriquedepense);


--
-- TOC entry 2367 (class 2606 OID 286080)
-- Name: pk_sousrubriquerecette; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY sousrubriquerecette
    ADD CONSTRAINT pk_sousrubriquerecette PRIMARY KEY (idsousrubriquerecette);


--
-- TOC entry 2405 (class 2606 OID 286155)
-- Name: pk_statutstructure; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY statutstructure
    ADD CONSTRAINT pk_statutstructure PRIMARY KEY (idstatutstructure);


--
-- TOC entry 2388 (class 2606 OID 286117)
-- Name: pk_structure; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY structure
    ADD CONSTRAINT pk_structure PRIMARY KEY (idstructure);


--
-- TOC entry 2501 (class 2606 OID 467401)
-- Name: pk_type_sous_periode; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typesousperiode
    ADD CONSTRAINT pk_type_sous_periode PRIMARY KEY (idtypesousperiode);


--
-- TOC entry 2408 (class 2606 OID 286164)
-- Name: pk_typestructure; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typestructure
    ADD CONSTRAINT pk_typestructure PRIMARY KEY (idtypestructure);


--
-- TOC entry 2499 (class 2606 OID 467386)
-- Name: pk_typestructureService; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typestructureservice
    ADD CONSTRAINT "pk_typestructureService" PRIMARY KEY (idtypestructureservice);


--
-- TOC entry 2505 (class 2606 OID 467427)
-- Name: pk_typestructurecategorie; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typestructurecategorie
    ADD CONSTRAINT pk_typestructurecategorie PRIMARY KEY (idtypestructurecategorie);


--
-- TOC entry 2512 (class 2606 OID 475608)
-- Name: pk_typestructuresousrubriquerecette; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typestructuresousrubriquerecette
    ADD CONSTRAINT pk_typestructuresousrubriquerecette PRIMARY KEY (id);


--
-- TOC entry 2503 (class 2606 OID 467412)
-- Name: pk_typestructuretypesousperiode; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typestructuretypesousperiode
    ADD CONSTRAINT pk_typestructuretypesousperiode PRIMARY KEY (idtypestructuretypesousperiode);


--
-- TOC entry 2391 (class 2606 OID 286129)
-- Name: pk_utilisateur; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY utilisateur
    ADD CONSTRAINT pk_utilisateur PRIMARY KEY (idutilisateur);


--
-- TOC entry 2396 (class 2606 OID 286135)
-- Name: pk_utilisateurstructure; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY utilisateurstructure
    ADD CONSTRAINT pk_utilisateurstructure PRIMARY KEY (idutilisateur, idstructure);


--
-- TOC entry 2345 (class 1259 OID 286033)
-- Name: association10_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association10_fk ON recette USING btree (idsousrubriquerecette);


--
-- TOC entry 2369 (class 1259 OID 286092)
-- Name: association11_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association11_fk ON souscritere USING btree (idcritere);


--
-- TOC entry 2374 (class 1259 OID 286099)
-- Name: association16_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association16_fk ON souscritereservice USING btree (idservice);


--
-- TOC entry 2375 (class 1259 OID 286100)
-- Name: association17_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association17_fk ON souscritereservice USING btree (idsouscritere);


--
-- TOC entry 2333 (class 1259 OID 286015)
-- Name: association1_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association1_fk ON personnel USING btree (idcategorie);


--
-- TOC entry 2296 (class 1259 OID 285934)
-- Name: association20_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association20_fk ON criterestructure USING btree (idstructure);


--
-- TOC entry 2297 (class 1259 OID 285935)
-- Name: association20_fk2; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association20_fk2 ON criterestructure USING btree (idcritere);


--
-- TOC entry 2398 (class 1259 OID 286145)
-- Name: association22_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association22_fk ON detailsc USING btree (idsouscritere);


--
-- TOC entry 2308 (class 1259 OID 285962)
-- Name: association23_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association23_fk ON evaluationpersonnel USING btree (iddetailsc);


--
-- TOC entry 2384 (class 1259 OID 286119)
-- Name: association24_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association24_fk ON structure USING btree (idinstitution);


--
-- TOC entry 2399 (class 1259 OID 286146)
-- Name: association25_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association25_fk ON detailsc USING btree (idcategorie);


--
-- TOC entry 2288 (class 1259 OID 285909)
-- Name: association26_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association26_fk ON categoriestructure USING btree (idstructure);


--
-- TOC entry 2289 (class 1259 OID 285910)
-- Name: association26_fk2; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association26_fk2 ON categoriestructure USING btree (idcategorie);


--
-- TOC entry 2385 (class 1259 OID 286120)
-- Name: association28_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association28_fk ON structure USING btree (idtypestructure);


--
-- TOC entry 2386 (class 1259 OID 286121)
-- Name: association29_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association29_fk ON structure USING btree (idstatutstructure);


--
-- TOC entry 2393 (class 1259 OID 286137)
-- Name: association31_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association31_fk ON utilisateurstructure USING btree (idutilisateur);


--
-- TOC entry 2394 (class 1259 OID 286138)
-- Name: association31_fk2; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association31_fk2 ON utilisateurstructure USING btree (idstructure);


--
-- TOC entry 2301 (class 1259 OID 285945)
-- Name: association32_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association32_fk ON depense USING btree (idsousperiode);


--
-- TOC entry 2346 (class 1259 OID 286034)
-- Name: association33_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association33_fk ON recette USING btree (idsousperiode);


--
-- TOC entry 2340 (class 1259 OID 286023)
-- Name: association34_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association34_fk ON privilege USING btree (idutilisateur);


--
-- TOC entry 2341 (class 1259 OID 286024)
-- Name: association35_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association35_fk ON privilege USING btree (idmenu);


--
-- TOC entry 2400 (class 1259 OID 286147)
-- Name: association36_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association36_fk ON detailsc USING btree (idstructure);


--
-- TOC entry 2324 (class 1259 OID 285995)
-- Name: association38_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association38_fk ON note USING btree (idperiode);


--
-- TOC entry 2320 (class 1259 OID 285988)
-- Name: association39_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association39_fk ON mouchard USING btree (idutilisateur);


--
-- TOC entry 2365 (class 1259 OID 286082)
-- Name: association3_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association3_fk ON sousrubriquerecette USING btree (idrubriquerecette);


--
-- TOC entry 2513 (class 1259 OID 475681)
-- Name: association40_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association40_fk ON critereservice USING btree (idservice);


--
-- TOC entry 2514 (class 1259 OID 475682)
-- Name: association40_fk2; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association40_fk2 ON critereservice USING btree (idcritere);


--
-- TOC entry 2325 (class 1259 OID 285996)
-- Name: association41_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association41_fk ON note USING btree (idsousperiode);


--
-- TOC entry 2347 (class 1259 OID 286031)
-- Name: association4_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association4_fk ON recette USING btree (idperiode);


--
-- TOC entry 2361 (class 1259 OID 286072)
-- Name: association5_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association5_fk ON sousrubriquedepense USING btree (idrubriquedepense);


--
-- TOC entry 2302 (class 1259 OID 285942)
-- Name: association6_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association6_fk ON depense USING btree (idperiode);


--
-- TOC entry 2303 (class 1259 OID 285943)
-- Name: association7_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association7_fk ON depense USING btree (idstructure);


--
-- TOC entry 2348 (class 1259 OID 286032)
-- Name: association8_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association8_fk ON recette USING btree (idstructure);


--
-- TOC entry 2304 (class 1259 OID 285944)
-- Name: association9_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX association9_fk ON depense USING btree (idsousrubriquedepense);


--
-- TOC entry 2285 (class 1259 OID 285902)
-- Name: categorie_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX categorie_pk ON categorie USING btree (idcategorie);


--
-- TOC entry 2290 (class 1259 OID 285908)
-- Name: categoriestructure_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX categoriestructure_pk ON categoriestructure USING btree (idstructure, idcategorie);


--
-- TOC entry 2293 (class 1259 OID 285919)
-- Name: critere_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX critere_pk ON critere USING btree (idcritere);


--
-- TOC entry 2515 (class 1259 OID 475683)
-- Name: critereservice_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX critereservice_pk ON critereservice USING btree (idservice, idcritere);


--
-- TOC entry 2298 (class 1259 OID 285933)
-- Name: criterestructure_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX criterestructure_pk ON criterestructure USING btree (idstructure, idcritere);


--
-- TOC entry 2305 (class 1259 OID 285941)
-- Name: depense_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX depense_pk ON depense USING btree (iddepense);


--
-- TOC entry 2401 (class 1259 OID 286144)
-- Name: detailsc_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX detailsc_pk ON detailsc USING btree (iddetailsc);


--
-- TOC entry 2309 (class 1259 OID 285959)
-- Name: evaluationpersonnel_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX evaluationpersonnel_pk ON evaluationpersonnel USING btree (idevaluationpersonnel);


--
-- TOC entry 2463 (class 1259 OID 483917)
-- Name: fki_cible_evaluationbonusrdeptpersonnel; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_cible_evaluationbonusrdeptpersonnel ON evaluationbonusrdeptpersonnel USING btree (idcible);


--
-- TOC entry 2446 (class 1259 OID 484031)
-- Name: fki_cible_evaluationrqntifdept; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_cible_evaluationrqntifdept ON evaluationrqntifdept USING btree (idcible);


--
-- TOC entry 2426 (class 1259 OID 383085)
-- Name: fki_critere_cible; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_critere_cible ON cible USING btree (idcritere);


--
-- TOC entry 2442 (class 1259 OID 484055)
-- Name: fki_critere_evaluation_bonus_pp; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_critere_evaluation_bonus_pp ON evaluationbonuspp USING btree (idcritere);


--
-- TOC entry 2434 (class 1259 OID 484049)
-- Name: fki_critere_evaluation_heure_supp; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_critere_evaluation_heure_supp ON evaluationheuresupp USING btree (idcritere);


--
-- TOC entry 2438 (class 1259 OID 484043)
-- Name: fki_critere_evaluation_responsabilite; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_critere_evaluation_responsabilite ON evaluationresponsabilite USING btree (idcritere);


--
-- TOC entry 2451 (class 1259 OID 484061)
-- Name: fki_critere_evaluationrprimeqltifdept; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_critere_evaluationrprimeqltifdept ON evaluationrprimeqltifdept USING btree (idcritere);


--
-- TOC entry 2310 (class 1259 OID 356191)
-- Name: fki_elementreponse_evaluationpersonnel; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_elementreponse_evaluationpersonnel ON evaluationpersonnel USING btree (idelementreponse);


--
-- TOC entry 2476 (class 1259 OID 483875)
-- Name: fki_evaluationpenalitedept_lignepenalite_dept; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_evaluationpenalitedept_lignepenalite_dept ON lignepenalitedept USING btree (idevaluationpenalitedept);


--
-- TOC entry 2458 (class 1259 OID 484019)
-- Name: fki_evaluationrprimeqltifdept_evaluationrprimeqltifpersonnel; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_evaluationrprimeqltifdept_evaluationrprimeqltifpersonnel ON evaluationrprimeqltifpersonnel USING btree (idevaluationrprimeqltifdept);


--
-- TOC entry 2480 (class 1259 OID 483881)
-- Name: fki_evaluationrprimeqltifdept_ligneprimequalitedept; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_evaluationrprimeqltifdept_ligneprimequalitedept ON ligneprimequalitedept USING btree (idevaluationrprimeqltifdept);


--
-- TOC entry 2443 (class 1259 OID 416619)
-- Name: fki_note_evaluation_bonuspp; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_note_evaluation_bonuspp ON evaluationbonuspp USING btree (idnote);


--
-- TOC entry 2311 (class 1259 OID 416607)
-- Name: fki_note_evaluation_individuelle; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_note_evaluation_individuelle ON evaluationpersonnel USING btree (idnote);


--
-- TOC entry 2464 (class 1259 OID 416625)
-- Name: fki_note_evaluationbonusrdeptpersonnel; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_note_evaluationbonusrdeptpersonnel ON evaluationbonusrdeptpersonnel USING btree (idnote);


--
-- TOC entry 2435 (class 1259 OID 416631)
-- Name: fki_note_evaluationheuresupp; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_note_evaluationheuresupp ON evaluationheuresupp USING btree (idnote);


--
-- TOC entry 2484 (class 1259 OID 416637)
-- Name: fki_note_evaluationpenalitepersonnel; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_note_evaluationpenalitepersonnel ON evaluationpenalitepersonnel USING btree (idnote);


--
-- TOC entry 2439 (class 1259 OID 416613)
-- Name: fki_note_evaluationresponsabilite; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_note_evaluationresponsabilite ON evaluationresponsabilite USING btree (idnote);


--
-- TOC entry 2459 (class 1259 OID 416643)
-- Name: fki_note_evaluationrprimeqltifpersonnel; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_note_evaluationrprimeqltifpersonnel ON evaluationrprimeqltifpersonnel USING btree (idnote);


--
-- TOC entry 2447 (class 1259 OID 484037)
-- Name: fki_note_evaluationrqntifdept; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_note_evaluationrqntifdept ON evaluationrqntifdept USING btree (idnote);


--
-- TOC entry 2414 (class 1259 OID 294645)
-- Name: fki_note_prime; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_note_prime ON prime USING btree (idnote);


--
-- TOC entry 2477 (class 1259 OID 483869)
-- Name: fki_penalite_lignepenalitedept; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_penalite_lignepenalitedept ON lignepenalitedept USING btree (idpenalite);


--
-- TOC entry 2487 (class 1259 OID 483863)
-- Name: fki_penalite_lignepenalitepersonnel; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_penalite_lignepenalitepersonnel ON lignepenalitepersonnel USING btree (idpenalite);


--
-- TOC entry 2468 (class 1259 OID 483947)
-- Name: fki_periode_evaluationpenalitedept; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_periode_evaluationpenalitedept ON evaluationpenalitedept USING btree (idperiode);


--
-- TOC entry 2452 (class 1259 OID 484001)
-- Name: fki_periode_evaluationrprimeqltifdept; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_periode_evaluationrprimeqltifdept ON evaluationrprimeqltifdept USING btree (idperiode);


--
-- TOC entry 2410 (class 1259 OID 294574)
-- Name: fki_periode_noteservice; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_periode_noteservice ON noteservice USING btree (idperiode);


--
-- TOC entry 2465 (class 1259 OID 483911)
-- Name: fki_personnel_evaluationbonusrdeptpersonnel; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_personnel_evaluationbonusrdeptpersonnel ON evaluationbonusrdeptpersonnel USING btree (idpersonnel);


--
-- TOC entry 2460 (class 1259 OID 484013)
-- Name: fki_personnel_evaluationrprimeqltifpersonnel; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_personnel_evaluationrprimeqltifpersonnel ON evaluationrprimeqltifpersonnel USING btree (idpersonnel);


--
-- TOC entry 2448 (class 1259 OID 484025)
-- Name: fki_personnel_evaluationrqntifdept; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_personnel_evaluationrqntifdept ON evaluationrqntifdept USING btree (idpersonnel);


--
-- TOC entry 2326 (class 1259 OID 294596)
-- Name: fki_personnel_note; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_personnel_note ON note USING btree (idpersonnel);


--
-- TOC entry 2334 (class 1259 OID 374892)
-- Name: fki_responsabilite_personnel; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_responsabilite_personnel ON personnel USING btree (idresponsabilite);


--
-- TOC entry 2370 (class 1259 OID 328656)
-- Name: fki_rubriquesc_souscritere; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_rubriquesc_souscritere ON souscritere USING btree (idrubriquesc);


--
-- TOC entry 2469 (class 1259 OID 483941)
-- Name: fki_service_evaluationpenalitedept; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_service_evaluationpenalitedept ON evaluationpenalitedept USING btree (idservice);


--
-- TOC entry 2453 (class 1259 OID 483995)
-- Name: fki_service_evaluationrprimeqltifdept; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_service_evaluationrprimeqltifdept ON evaluationrprimeqltifdept USING btree (idservice);


--
-- TOC entry 2411 (class 1259 OID 294590)
-- Name: fki_service_noteservice; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_service_noteservice ON noteservice USING btree (idservice);


--
-- TOC entry 2335 (class 1259 OID 459123)
-- Name: fki_service_personnel; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_service_personnel ON personnel USING btree (idservice);


--
-- TOC entry 2419 (class 1259 OID 346613)
-- Name: fki_souscritere_element_reponse; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_souscritere_element_reponse ON elementreponse USING btree (idsouscritere);


--
-- TOC entry 2481 (class 1259 OID 483887)
-- Name: fki_souscritereservice_ligneprimequalitedept; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_souscritereservice_ligneprimequalitedept ON ligneprimequalitedept USING btree (idsouscritereservice);


--
-- TOC entry 2427 (class 1259 OID 383079)
-- Name: fki_sousperiode_cible; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_sousperiode_cible ON cible USING btree (idsousperiode);


--
-- TOC entry 2470 (class 1259 OID 483953)
-- Name: fki_sousperiode_evaluationpenalitedept; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_sousperiode_evaluationpenalitedept ON evaluationpenalitedept USING btree (idsousperiode);


--
-- TOC entry 2454 (class 1259 OID 484007)
-- Name: fki_sousperiode_evaluationrprimeqltifdept; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_sousperiode_evaluationrprimeqltifdept ON evaluationrprimeqltifdept USING btree (idsousperiode);


--
-- TOC entry 2510 (class 1259 OID 475624)
-- Name: fki_srr_typestructuresousrubriquerecette; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_srr_typestructuresousrubriquerecette ON typestructuresousrubriquerecette USING btree (idsousrubriquerecette);


--
-- TOC entry 2471 (class 1259 OID 459141)
-- Name: fki_structure_evaluation_penalite_departement; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_structure_evaluation_penalite_departement ON evaluationpenalitedept USING btree (idstructure);


--
-- TOC entry 2455 (class 1259 OID 459135)
-- Name: fki_structure_evaluationprimeqltifdept; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_structure_evaluationprimeqltifdept ON evaluationrprimeqltifdept USING btree (idstructure);


--
-- TOC entry 2336 (class 1259 OID 459117)
-- Name: fki_structure_personnel; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_structure_personnel ON personnel USING btree (idstructure);


--
-- TOC entry 2376 (class 1259 OID 459129)
-- Name: fki_structure_souscritere_service; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_structure_souscritere_service ON souscritereservice USING btree (idstructure);


--
-- TOC entry 2380 (class 1259 OID 467407)
-- Name: fki_type_sous_periode_sous_periode; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_type_sous_periode_sous_periode ON sousperiode USING btree (idtypesousperiode);


--
-- TOC entry 2314 (class 1259 OID 285972)
-- Name: institution_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX institution_pk ON institution USING btree (idinstitution);


--
-- TOC entry 2317 (class 1259 OID 285981)
-- Name: menu_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX menu_pk ON menu USING btree (idmenu);


--
-- TOC entry 2321 (class 1259 OID 285987)
-- Name: mouchard_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX mouchard_pk ON mouchard USING btree (idmouchard);


--
-- TOC entry 2327 (class 1259 OID 285994)
-- Name: note_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX note_pk ON note USING btree (idnote);


--
-- TOC entry 2330 (class 1259 OID 286005)
-- Name: periode_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX periode_pk ON periode USING btree (idperiode);


--
-- TOC entry 2337 (class 1259 OID 286014)
-- Name: personnel_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX personnel_pk ON personnel USING btree (idpersonnel);


--
-- TOC entry 2344 (class 1259 OID 286022)
-- Name: privilege_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX privilege_pk ON privilege USING btree (idprivilege);


--
-- TOC entry 2351 (class 1259 OID 286030)
-- Name: recette_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX recette_pk ON recette USING btree (idrecette);


--
-- TOC entry 2354 (class 1259 OID 286043)
-- Name: rubriquedepense_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX rubriquedepense_pk ON rubriquedepense USING btree (idrubriquedepense);


--
-- TOC entry 2357 (class 1259 OID 286052)
-- Name: rubriquerecette_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX rubriquerecette_pk ON rubriquerecette USING btree (idrubriquerecette);


--
-- TOC entry 2360 (class 1259 OID 286061)
-- Name: service_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX service_pk ON service USING btree (idservice);


--
-- TOC entry 2373 (class 1259 OID 286091)
-- Name: souscritere_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX souscritere_pk ON souscritere USING btree (idsouscritere);


--
-- TOC entry 2379 (class 1259 OID 286098)
-- Name: souscritereservice_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX souscritereservice_pk ON souscritereservice USING btree (idsouscritereservice);


--
-- TOC entry 2383 (class 1259 OID 286109)
-- Name: sousperiode_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX sousperiode_pk ON sousperiode USING btree (idsousperiode);


--
-- TOC entry 2364 (class 1259 OID 286071)
-- Name: sousrubriquedepense_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX sousrubriquedepense_pk ON sousrubriquedepense USING btree (idsousrubriquedepense);


--
-- TOC entry 2368 (class 1259 OID 286081)
-- Name: sousrubriquerecette_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX sousrubriquerecette_pk ON sousrubriquerecette USING btree (idsousrubriquerecette);


--
-- TOC entry 2406 (class 1259 OID 286156)
-- Name: statutstructure_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX statutstructure_pk ON statutstructure USING btree (idstatutstructure);


--
-- TOC entry 2389 (class 1259 OID 286118)
-- Name: structure_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX structure_pk ON structure USING btree (idstructure);


--
-- TOC entry 2409 (class 1259 OID 286165)
-- Name: typestructure_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX typestructure_pk ON typestructure USING btree (idtypestructure);


--
-- TOC entry 2392 (class 1259 OID 286130)
-- Name: utilisateur_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX utilisateur_pk ON utilisateur USING btree (idutilisateur);


--
-- TOC entry 2397 (class 1259 OID 286136)
-- Name: utilisateurstructure_pk; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX utilisateurstructure_pk ON utilisateurstructure USING btree (idutilisateur, idstructure);


--
-- TOC entry 2518 (class 2606 OID 286166)
-- Name: fk_categori_associati_categori; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY categoriestructure
    ADD CONSTRAINT fk_categori_associati_categori FOREIGN KEY (idcategorie) REFERENCES categorie(idcategorie) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2519 (class 2606 OID 286171)
-- Name: fk_categori_associati_structur; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY categoriestructure
    ADD CONSTRAINT fk_categori_associati_structur FOREIGN KEY (idstructure) REFERENCES structure(idstructure) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2608 (class 2606 OID 442794)
-- Name: fk_categorie_effectif_cat; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY effectifcategorie
    ADD CONSTRAINT fk_categorie_effectif_cat FOREIGN KEY (idcategorie) REFERENCES categorie(idcategorie);


--
-- TOC entry 2576 (class 2606 OID 374948)
-- Name: fk_categorie_parametragecategorie; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY parametragecritere
    ADD CONSTRAINT fk_categorie_parametragecategorie FOREIGN KEY (idcategorie) REFERENCES categorie(idcategorie);


--
-- TOC entry 2624 (class 2606 OID 467433)
-- Name: fk_categorie_typestructurecategorie; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typestructurecategorie
    ADD CONSTRAINT fk_categorie_typestructurecategorie FOREIGN KEY (idcategorie) REFERENCES categorie(idcategorie);


--
-- TOC entry 2596 (class 2606 OID 483912)
-- Name: fk_cible_evaluationbonusrdeptpersonnel; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationbonusrdeptpersonnel
    ADD CONSTRAINT fk_cible_evaluationbonusrdeptpersonnel FOREIGN KEY (idcible) REFERENCES cible(idcible);


--
-- TOC entry 2584 (class 2606 OID 484026)
-- Name: fk_cible_evaluationrqntifdept; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationrqntifdept
    ADD CONSTRAINT fk_cible_evaluationrqntifdept FOREIGN KEY (idcible) REFERENCES cible(idcible);


--
-- TOC entry 2572 (class 2606 OID 383080)
-- Name: fk_critere_cible; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY cible
    ADD CONSTRAINT fk_critere_cible FOREIGN KEY (idcritere) REFERENCES critere(idcritere);


--
-- TOC entry 2575 (class 2606 OID 374938)
-- Name: fk_critere_critereresponsabilite; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY critereresponsabilite
    ADD CONSTRAINT fk_critere_critereresponsabilite FOREIGN KEY (idcritere) REFERENCES critere(idcritere);


--
-- TOC entry 2582 (class 2606 OID 484050)
-- Name: fk_critere_evaluation_bonus_pp; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationbonuspp
    ADD CONSTRAINT fk_critere_evaluation_bonus_pp FOREIGN KEY (idcritere) REFERENCES critere(idcritere);


--
-- TOC entry 2578 (class 2606 OID 484044)
-- Name: fk_critere_evaluation_heure_supp; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationheuresupp
    ADD CONSTRAINT fk_critere_evaluation_heure_supp FOREIGN KEY (idcritere) REFERENCES critere(idcritere);


--
-- TOC entry 2580 (class 2606 OID 484038)
-- Name: fk_critere_evaluation_responsabilite; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationresponsabilite
    ADD CONSTRAINT fk_critere_evaluation_responsabilite FOREIGN KEY (idcritere) REFERENCES critere(idcritere);


--
-- TOC entry 2590 (class 2606 OID 484056)
-- Name: fk_critere_evaluationrprimeqltifdept; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationrprimeqltifdept
    ADD CONSTRAINT fk_critere_evaluationrprimeqltifdept FOREIGN KEY (idcritere) REFERENCES critere(idcritere);


--
-- TOC entry 2615 (class 2606 OID 459174)
-- Name: fk_critere_parametragepenalite; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY parametragepenalite
    ADD CONSTRAINT fk_critere_parametragepenalite FOREIGN KEY (idcritere) REFERENCES critere(idcritere);


--
-- TOC entry 2520 (class 2606 OID 286186)
-- Name: fk_criteres_associati_critere; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY criterestructure
    ADD CONSTRAINT fk_criteres_associati_critere FOREIGN KEY (idcritere) REFERENCES critere(idcritere) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2631 (class 2606 OID 475671)
-- Name: fk_criteres_associati_critere; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY critereservice
    ADD CONSTRAINT fk_criteres_associati_critere FOREIGN KEY (idcritere) REFERENCES critere(idcritere) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2632 (class 2606 OID 475676)
-- Name: fk_criteres_associati_service; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY critereservice
    ADD CONSTRAINT fk_criteres_associati_service FOREIGN KEY (idservice) REFERENCES service(idservice) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2521 (class 2606 OID 286191)
-- Name: fk_criteres_associati_structur; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY criterestructure
    ADD CONSTRAINT fk_criteres_associati_structur FOREIGN KEY (idstructure) REFERENCES structure(idstructure) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2523 (class 2606 OID 286201)
-- Name: fk_depense_associati_periode; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY depense
    ADD CONSTRAINT fk_depense_associati_periode FOREIGN KEY (idperiode) REFERENCES periode(idperiode) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2522 (class 2606 OID 286196)
-- Name: fk_depense_associati_sousperi; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY depense
    ADD CONSTRAINT fk_depense_associati_sousperi FOREIGN KEY (idsousperiode) REFERENCES sousperiode(idsousperiode) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2525 (class 2606 OID 286211)
-- Name: fk_depense_associati_sousrubr; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY depense
    ADD CONSTRAINT fk_depense_associati_sousrubr FOREIGN KEY (idsousrubriquedepense) REFERENCES sousrubriquedepense(idsousrubriquedepense) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2524 (class 2606 OID 286206)
-- Name: fk_depense_associati_structur; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY depense
    ADD CONSTRAINT fk_depense_associati_structur FOREIGN KEY (idstructure) REFERENCES structure(idstructure) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2557 (class 2606 OID 286361)
-- Name: fk_detailsc_associati_categori; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY detailsc
    ADD CONSTRAINT fk_detailsc_associati_categori FOREIGN KEY (idcategorie) REFERENCES categorie(idcategorie) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2556 (class 2606 OID 286356)
-- Name: fk_detailsc_associati_souscrit; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY detailsc
    ADD CONSTRAINT fk_detailsc_associati_souscrit FOREIGN KEY (idsouscritere) REFERENCES souscritere(idsouscritere) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2558 (class 2606 OID 286366)
-- Name: fk_detailsc_associati_structur; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY detailsc
    ADD CONSTRAINT fk_detailsc_associati_structur FOREIGN KEY (idstructure) REFERENCES structure(idstructure) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2527 (class 2606 OID 356186)
-- Name: fk_elementreponse_evaluationpersonnel; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationpersonnel
    ADD CONSTRAINT fk_elementreponse_evaluationpersonnel FOREIGN KEY (idelementreponse) REFERENCES elementreponse(idelementreponse);


--
-- TOC entry 2526 (class 2606 OID 286236)
-- Name: fk_evaluati_associati_detailsc; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationpersonnel
    ADD CONSTRAINT fk_evaluati_associati_detailsc FOREIGN KEY (iddetailsc) REFERENCES detailsc(iddetailsc) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2606 (class 2606 OID 416597)
-- Name: fk_evaluation_personnel_lignepenalite; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY lignepenalitepersonnel
    ADD CONSTRAINT fk_evaluation_personnel_lignepenalite FOREIGN KEY (idevaluationpenalitepersonnel) REFERENCES evaluationpenalitepersonnel(idevaluationpenalitepersonnel);


--
-- TOC entry 2602 (class 2606 OID 483870)
-- Name: fk_evaluationpenalitedept_lignepenalite_dept; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY lignepenalitedept
    ADD CONSTRAINT fk_evaluationpenalitedept_lignepenalite_dept FOREIGN KEY (idevaluationpenalitedept) REFERENCES evaluationpenalitedept(idevaluationpenalitedept);


--
-- TOC entry 2593 (class 2606 OID 484014)
-- Name: fk_evaluationrprimeqltifdept_evaluationrprimeqltifpersonnel; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationrprimeqltifpersonnel
    ADD CONSTRAINT fk_evaluationrprimeqltifdept_evaluationrprimeqltifpersonnel FOREIGN KEY (idevaluationrprimeqltifdept) REFERENCES evaluationrprimeqltifdept(idevaluationrprimeqltifdept);


--
-- TOC entry 2603 (class 2606 OID 483876)
-- Name: fk_evaluationrprimeqltifdept_ligneprimequalitedept; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ligneprimequalitedept
    ADD CONSTRAINT fk_evaluationrprimeqltifdept_ligneprimequalitedept FOREIGN KEY (idevaluationrprimeqltifdept) REFERENCES evaluationrprimeqltifdept(idevaluationrprimeqltifdept);


--
-- TOC entry 2567 (class 2606 OID 374903)
-- Name: fk_indicateur_cible; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY cible
    ADD CONSTRAINT fk_indicateur_cible FOREIGN KEY (idindicateur) REFERENCES indicateur(idindicateur);


--
-- TOC entry 2618 (class 2606 OID 459205)
-- Name: fk_indicateurqteservice; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY indicateurqteservice
    ADD CONSTRAINT fk_indicateurqteservice FOREIGN KEY (idindicateur) REFERENCES indicateur(idindicateur);


--
-- TOC entry 2529 (class 2606 OID 286246)
-- Name: fk_mouchard_associati_utilisat; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY mouchard
    ADD CONSTRAINT fk_mouchard_associati_utilisat FOREIGN KEY (idutilisateur) REFERENCES utilisateur(idutilisateur) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2530 (class 2606 OID 286251)
-- Name: fk_note_associati_periode; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY note
    ADD CONSTRAINT fk_note_associati_periode FOREIGN KEY (idperiode) REFERENCES periode(idperiode) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2531 (class 2606 OID 286256)
-- Name: fk_note_associati_sousperi; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY note
    ADD CONSTRAINT fk_note_associati_sousperi FOREIGN KEY (idsousperiode) REFERENCES sousperiode(idsousperiode) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2581 (class 2606 OID 416614)
-- Name: fk_note_evaluation_bonuspp; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationbonuspp
    ADD CONSTRAINT fk_note_evaluation_bonuspp FOREIGN KEY (idnote) REFERENCES note(idnote);


--
-- TOC entry 2528 (class 2606 OID 416602)
-- Name: fk_note_evaluation_individuelle; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationpersonnel
    ADD CONSTRAINT fk_note_evaluation_individuelle FOREIGN KEY (idnote) REFERENCES note(idnote);


--
-- TOC entry 2594 (class 2606 OID 416620)
-- Name: fk_note_evaluationbonusrdeptpersonnel; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationbonusrdeptpersonnel
    ADD CONSTRAINT fk_note_evaluationbonusrdeptpersonnel FOREIGN KEY (idnote) REFERENCES note(idnote);


--
-- TOC entry 2577 (class 2606 OID 416626)
-- Name: fk_note_evaluationheuresupp; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationheuresupp
    ADD CONSTRAINT fk_note_evaluationheuresupp FOREIGN KEY (idnote) REFERENCES note(idnote);


--
-- TOC entry 2605 (class 2606 OID 416632)
-- Name: fk_note_evaluationpenalitepersonnel; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationpenalitepersonnel
    ADD CONSTRAINT fk_note_evaluationpenalitepersonnel FOREIGN KEY (idnote) REFERENCES note(idnote);


--
-- TOC entry 2579 (class 2606 OID 416608)
-- Name: fk_note_evaluationresponsabilite; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationresponsabilite
    ADD CONSTRAINT fk_note_evaluationresponsabilite FOREIGN KEY (idnote) REFERENCES note(idnote);


--
-- TOC entry 2591 (class 2606 OID 416638)
-- Name: fk_note_evaluationrprimeqltifpersonnel; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationrprimeqltifpersonnel
    ADD CONSTRAINT fk_note_evaluationrprimeqltifpersonnel FOREIGN KEY (idnote) REFERENCES note(idnote);


--
-- TOC entry 2585 (class 2606 OID 484032)
-- Name: fk_note_evaluationrqntifdept; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationrqntifdept
    ADD CONSTRAINT fk_note_evaluationrqntifdept FOREIGN KEY (idnote) REFERENCES note(idnote);


--
-- TOC entry 2565 (class 2606 OID 294640)
-- Name: fk_note_prime; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY prime
    ADD CONSTRAINT fk_note_prime FOREIGN KEY (idnote) REFERENCES note(idnote);


--
-- TOC entry 2601 (class 2606 OID 483864)
-- Name: fk_penalite_lignepenalitedept; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY lignepenalitedept
    ADD CONSTRAINT fk_penalite_lignepenalitedept FOREIGN KEY (idpenalite) REFERENCES penalite(idpenalite);


--
-- TOC entry 2607 (class 2606 OID 483858)
-- Name: fk_penalite_lignepenalitepersonnel; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY lignepenalitepersonnel
    ADD CONSTRAINT fk_penalite_lignepenalitepersonnel FOREIGN KEY (idpenalite) REFERENCES penalite(idpenalite);


--
-- TOC entry 2612 (class 2606 OID 459159)
-- Name: fk_penalite_parametragepenalite; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY parametragepenalite
    ADD CONSTRAINT fk_penalite_parametragepenalite FOREIGN KEY (idpenalite) REFERENCES penalite(idpenalite);


--
-- TOC entry 2599 (class 2606 OID 483942)
-- Name: fk_periode_evaluationpenalitedept; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationpenalitedept
    ADD CONSTRAINT fk_periode_evaluationpenalitedept FOREIGN KEY (idperiode) REFERENCES periode(idperiode);


--
-- TOC entry 2588 (class 2606 OID 483996)
-- Name: fk_periode_evaluationrprimeqltifdept; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationrprimeqltifdept
    ADD CONSTRAINT fk_periode_evaluationrprimeqltifdept FOREIGN KEY (idperiode) REFERENCES periode(idperiode);


--
-- TOC entry 2559 (class 2606 OID 294569)
-- Name: fk_periode_noteservice; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY noteservice
    ADD CONSTRAINT fk_periode_noteservice FOREIGN KEY (idperiode) REFERENCES periode(idperiode);


--
-- TOC entry 2563 (class 2606 OID 294630)
-- Name: fk_periode_prime; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY prime
    ADD CONSTRAINT fk_periode_prime FOREIGN KEY (idperiode) REFERENCES periode(idperiode);


--
-- TOC entry 2570 (class 2606 OID 374918)
-- Name: fk_periode_service; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY cible
    ADD CONSTRAINT fk_periode_service FOREIGN KEY (idperiode) REFERENCES periode(idperiode);


--
-- TOC entry 2533 (class 2606 OID 286261)
-- Name: fk_personne_associati_categori; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY personnel
    ADD CONSTRAINT fk_personne_associati_categori FOREIGN KEY (idcategorie) REFERENCES categorie(idcategorie) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2595 (class 2606 OID 483906)
-- Name: fk_personnel_evaluationbonusrdeptpersonnel; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationbonusrdeptpersonnel
    ADD CONSTRAINT fk_personnel_evaluationbonusrdeptpersonnel FOREIGN KEY (idpersonnel) REFERENCES personnel(idpersonnel);


--
-- TOC entry 2592 (class 2606 OID 484008)
-- Name: fk_personnel_evaluationrprimeqltifpersonnel; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationrprimeqltifpersonnel
    ADD CONSTRAINT fk_personnel_evaluationrprimeqltifpersonnel FOREIGN KEY (idpersonnel) REFERENCES personnel(idpersonnel);


--
-- TOC entry 2583 (class 2606 OID 484020)
-- Name: fk_personnel_evaluationrqntifdept; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationrqntifdept
    ADD CONSTRAINT fk_personnel_evaluationrqntifdept FOREIGN KEY (idpersonnel) REFERENCES personnel(idpersonnel);


--
-- TOC entry 2532 (class 2606 OID 294591)
-- Name: fk_personnel_note; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY note
    ADD CONSTRAINT fk_personnel_note FOREIGN KEY (idpersonnel) REFERENCES personnel(idpersonnel);


--
-- TOC entry 2562 (class 2606 OID 294625)
-- Name: fk_personnel_prime; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY prime
    ADD CONSTRAINT fk_personnel_prime FOREIGN KEY (idpersonnel) REFERENCES personnel(idpersonnel);


--
-- TOC entry 2538 (class 2606 OID 286276)
-- Name: fk_privileg_associati_menu; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY privilege
    ADD CONSTRAINT fk_privileg_associati_menu FOREIGN KEY (idmenu) REFERENCES menu(idmenu) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2537 (class 2606 OID 286271)
-- Name: fk_privileg_associati_utilisat; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY privilege
    ADD CONSTRAINT fk_privileg_associati_utilisat FOREIGN KEY (idutilisateur) REFERENCES utilisateur(idutilisateur) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2541 (class 2606 OID 286291)
-- Name: fk_recette_associati_periode; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY recette
    ADD CONSTRAINT fk_recette_associati_periode FOREIGN KEY (idperiode) REFERENCES periode(idperiode) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2540 (class 2606 OID 286286)
-- Name: fk_recette_associati_sousperi; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY recette
    ADD CONSTRAINT fk_recette_associati_sousperi FOREIGN KEY (idsousperiode) REFERENCES sousperiode(idsousperiode) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2539 (class 2606 OID 286281)
-- Name: fk_recette_associati_sousrubr; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY recette
    ADD CONSTRAINT fk_recette_associati_sousrubr FOREIGN KEY (idsousrubriquerecette) REFERENCES sousrubriquerecette(idsousrubriquerecette) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2542 (class 2606 OID 286296)
-- Name: fk_recette_associati_structur; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY recette
    ADD CONSTRAINT fk_recette_associati_structur FOREIGN KEY (idstructure) REFERENCES structure(idstructure) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2574 (class 2606 OID 374933)
-- Name: fk_responsabilite_critereresponsabilite; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY critereresponsabilite
    ADD CONSTRAINT fk_responsabilite_critereresponsabilite FOREIGN KEY (idresponsabilite) REFERENCES responsabilite(idresponsabilite);


--
-- TOC entry 2611 (class 2606 OID 442814)
-- Name: fk_responsabilite_effectif_responsabilite; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY effectifresponsabilite
    ADD CONSTRAINT fk_responsabilite_effectif_responsabilite FOREIGN KEY (idresponsabilite) REFERENCES responsabilite(idresponsabilite);


--
-- TOC entry 2534 (class 2606 OID 374887)
-- Name: fk_responsabilite_personnel; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY personnel
    ADD CONSTRAINT fk_responsabilite_personnel FOREIGN KEY (idresponsabilite) REFERENCES responsabilite(idresponsabilite);


--
-- TOC entry 2626 (class 2606 OID 475584)
-- Name: fk_responsabilite_typestructureresponsabilite; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typestructureresponsabilite
    ADD CONSTRAINT fk_responsabilite_typestructureresponsabilite FOREIGN KEY (idresponsabilite) REFERENCES responsabilite(idresponsabilite);


--
-- TOC entry 2546 (class 2606 OID 328651)
-- Name: fk_rubriquesc_souscritere; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY souscritere
    ADD CONSTRAINT fk_rubriquesc_souscritere FOREIGN KEY (idrubriquesc) REFERENCES rubriquesc(idrubriquesc);


--
-- TOC entry 2568 (class 2606 OID 374908)
-- Name: fk_service_cible; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY cible
    ADD CONSTRAINT fk_service_cible FOREIGN KEY (idservice) REFERENCES service(idservice);


--
-- TOC entry 2598 (class 2606 OID 483936)
-- Name: fk_service_evaluationpenalitedept; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationpenalitedept
    ADD CONSTRAINT fk_service_evaluationpenalitedept FOREIGN KEY (idservice) REFERENCES service(idservice);


--
-- TOC entry 2587 (class 2606 OID 483990)
-- Name: fk_service_evaluationrprimeqltifdept; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationrprimeqltifdept
    ADD CONSTRAINT fk_service_evaluationrprimeqltifdept FOREIGN KEY (idservice) REFERENCES service(idservice);


--
-- TOC entry 2617 (class 2606 OID 459200)
-- Name: fk_service_indicateurqteservice; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY indicateurqteservice
    ADD CONSTRAINT fk_service_indicateurqteservice FOREIGN KEY (idservice) REFERENCES service(idservice);


--
-- TOC entry 2561 (class 2606 OID 294585)
-- Name: fk_service_noteservice; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY noteservice
    ADD CONSTRAINT fk_service_noteservice FOREIGN KEY (idservice) REFERENCES service(idservice);


--
-- TOC entry 2614 (class 2606 OID 459169)
-- Name: fk_service_parametragepenalite; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY parametragepenalite
    ADD CONSTRAINT fk_service_parametragepenalite FOREIGN KEY (idservice) REFERENCES service(idservice);


--
-- TOC entry 2536 (class 2606 OID 459118)
-- Name: fk_service_personnel; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY personnel
    ADD CONSTRAINT fk_service_personnel FOREIGN KEY (idservice) REFERENCES service(idservice);


--
-- TOC entry 2619 (class 2606 OID 467387)
-- Name: fk_service_typestructure_service; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typestructureservice
    ADD CONSTRAINT fk_service_typestructure_service FOREIGN KEY (idservice) REFERENCES service(idservice);


--
-- TOC entry 2560 (class 2606 OID 294580)
-- Name: fk_sous_periode_noteservice; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY noteservice
    ADD CONSTRAINT fk_sous_periode_noteservice FOREIGN KEY (idsousperiode) REFERENCES sousperiode(idsousperiode);


--
-- TOC entry 2545 (class 2606 OID 286316)
-- Name: fk_souscrit_associati_critere; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY souscritere
    ADD CONSTRAINT fk_souscrit_associati_critere FOREIGN KEY (idcritere) REFERENCES critere(idcritere) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2547 (class 2606 OID 286321)
-- Name: fk_souscrit_associati_service; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY souscritereservice
    ADD CONSTRAINT fk_souscrit_associati_service FOREIGN KEY (idservice) REFERENCES service(idservice) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2548 (class 2606 OID 286326)
-- Name: fk_souscrit_associati_souscrit; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY souscritereservice
    ADD CONSTRAINT fk_souscrit_associati_souscrit FOREIGN KEY (idsouscritere) REFERENCES souscritere(idsouscritere) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2566 (class 2606 OID 346608)
-- Name: fk_souscritere_element_reponse; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY elementreponse
    ADD CONSTRAINT fk_souscritere_element_reponse FOREIGN KEY (idsouscritere) REFERENCES souscritere(idsouscritere);


--
-- TOC entry 2604 (class 2606 OID 483882)
-- Name: fk_souscritereservice_ligneprimequalitedept; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ligneprimequalitedept
    ADD CONSTRAINT fk_souscritereservice_ligneprimequalitedept FOREIGN KEY (idsouscritereservice) REFERENCES souscritereservice(idsouscritereservice);


--
-- TOC entry 2571 (class 2606 OID 383074)
-- Name: fk_sousperiode_cible; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY cible
    ADD CONSTRAINT fk_sousperiode_cible FOREIGN KEY (idsousperiode) REFERENCES sousperiode(idsousperiode);


--
-- TOC entry 2600 (class 2606 OID 483948)
-- Name: fk_sousperiode_evaluationpenalitedept; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationpenalitedept
    ADD CONSTRAINT fk_sousperiode_evaluationpenalitedept FOREIGN KEY (idsousperiode) REFERENCES sousperiode(idsousperiode);


--
-- TOC entry 2589 (class 2606 OID 484002)
-- Name: fk_sousperiode_evaluationrprimeqltifdept; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationrprimeqltifdept
    ADD CONSTRAINT fk_sousperiode_evaluationrprimeqltifdept FOREIGN KEY (idsousperiode) REFERENCES sousperiode(idsousperiode);


--
-- TOC entry 2564 (class 2606 OID 294635)
-- Name: fk_sousperiode_prime; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY prime
    ADD CONSTRAINT fk_sousperiode_prime FOREIGN KEY (idsousperiode) REFERENCES sousperiode(idsousperiode);


--
-- TOC entry 2543 (class 2606 OID 286306)
-- Name: fk_sousrubr_associati_rubrique; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY sousrubriquedepense
    ADD CONSTRAINT fk_sousrubr_associati_rubrique FOREIGN KEY (idrubriquedepense) REFERENCES rubriquedepense(idrubriquedepense) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2544 (class 2606 OID 286311)
-- Name: fk_sousrubr_associati_rubrique; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY sousrubriquerecette
    ADD CONSTRAINT fk_sousrubr_associati_rubrique FOREIGN KEY (idrubriquerecette) REFERENCES rubriquerecette(idrubriquerecette) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2628 (class 2606 OID 475599)
-- Name: fk_srd_typestructuresousrubriquedepense; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typestructuresousrubriquedepense
    ADD CONSTRAINT fk_srd_typestructuresousrubriquedepense FOREIGN KEY (idsousrubriquedepense) REFERENCES sousrubriquedepense(idsousrubriquedepense);


--
-- TOC entry 2630 (class 2606 OID 475619)
-- Name: fk_srr_typestructuresousrubriquerecette; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typestructuresousrubriquerecette
    ADD CONSTRAINT fk_srr_typestructuresousrubriquerecette FOREIGN KEY (idsousrubriquerecette) REFERENCES sousrubriquerecette(idsousrubriquerecette);


--
-- TOC entry 2551 (class 2606 OID 286331)
-- Name: fk_structur_associati_institut; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY structure
    ADD CONSTRAINT fk_structur_associati_institut FOREIGN KEY (idinstitution) REFERENCES institution(idinstitution) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2553 (class 2606 OID 286341)
-- Name: fk_structur_associati_statutst; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY structure
    ADD CONSTRAINT fk_structur_associati_statutst FOREIGN KEY (idstatutstructure) REFERENCES statutstructure(idstatutstructure) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2552 (class 2606 OID 286336)
-- Name: fk_structur_associati_typestru; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY structure
    ADD CONSTRAINT fk_structur_associati_typestru FOREIGN KEY (idtypestructure) REFERENCES typestructure(idtypestructure) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2569 (class 2606 OID 374913)
-- Name: fk_structure_cible; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY cible
    ADD CONSTRAINT fk_structure_cible FOREIGN KEY (idstructure) REFERENCES structure(idstructure);


--
-- TOC entry 2573 (class 2606 OID 374928)
-- Name: fk_structure_critereresponsabilite; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY critereresponsabilite
    ADD CONSTRAINT fk_structure_critereresponsabilite FOREIGN KEY (idstructure) REFERENCES structure(idstructure);


--
-- TOC entry 2609 (class 2606 OID 442799)
-- Name: fk_structure_effectif_cat; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY effectifcategorie
    ADD CONSTRAINT fk_structure_effectif_cat FOREIGN KEY (idstructure) REFERENCES structure(idstructure);


--
-- TOC entry 2610 (class 2606 OID 442809)
-- Name: fk_structure_effectif_responsabilite; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY effectifresponsabilite
    ADD CONSTRAINT fk_structure_effectif_responsabilite FOREIGN KEY (idstructure) REFERENCES structure(idstructure);


--
-- TOC entry 2597 (class 2606 OID 459136)
-- Name: fk_structure_evaluation_penalite_departement; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationpenalitedept
    ADD CONSTRAINT fk_structure_evaluation_penalite_departement FOREIGN KEY (idstructure) REFERENCES structure(idstructure);


--
-- TOC entry 2586 (class 2606 OID 459130)
-- Name: fk_structure_evaluationprimeqltifdept; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY evaluationrprimeqltifdept
    ADD CONSTRAINT fk_structure_evaluationprimeqltifdept FOREIGN KEY (idstructure) REFERENCES structure(idstructure);


--
-- TOC entry 2616 (class 2606 OID 459195)
-- Name: fk_structure_indicateurqteservice; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY indicateurqteservice
    ADD CONSTRAINT fk_structure_indicateurqteservice FOREIGN KEY (idstructure) REFERENCES structure(idstructure);


--
-- TOC entry 2613 (class 2606 OID 459164)
-- Name: fk_structure_parametragepenalite; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY parametragepenalite
    ADD CONSTRAINT fk_structure_parametragepenalite FOREIGN KEY (idstructure) REFERENCES structure(idstructure);


--
-- TOC entry 2535 (class 2606 OID 459112)
-- Name: fk_structure_personnel; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY personnel
    ADD CONSTRAINT fk_structure_personnel FOREIGN KEY (idstructure) REFERENCES structure(idstructure);


--
-- TOC entry 2549 (class 2606 OID 459124)
-- Name: fk_structure_souscritere_service; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY souscritereservice
    ADD CONSTRAINT fk_structure_souscritere_service FOREIGN KEY (idstructure) REFERENCES structure(idstructure);


--
-- TOC entry 2627 (class 2606 OID 475594)
-- Name: fk_ts_typestructuresousrubriquedepense; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typestructuresousrubriquedepense
    ADD CONSTRAINT fk_ts_typestructuresousrubriquedepense FOREIGN KEY (idtypestructure) REFERENCES typestructure(idtypestructure);


--
-- TOC entry 2629 (class 2606 OID 475609)
-- Name: fk_ts_typestructuresousrubriquerecette; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typestructuresousrubriquerecette
    ADD CONSTRAINT fk_ts_typestructuresousrubriquerecette FOREIGN KEY (idtypestructure) REFERENCES typestructure(idtypestructure);


--
-- TOC entry 2621 (class 2606 OID 467413)
-- Name: fk_ts_typestructuretypesousperiode; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typestructuretypesousperiode
    ADD CONSTRAINT fk_ts_typestructuretypesousperiode FOREIGN KEY (idtypestructure) REFERENCES typestructure(idtypestructure);


--
-- TOC entry 2622 (class 2606 OID 467418)
-- Name: fk_tsp_typestructuretypesousperiode; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typestructuretypesousperiode
    ADD CONSTRAINT fk_tsp_typestructuretypesousperiode FOREIGN KEY (idtypesousperiode) REFERENCES typesousperiode(idtypesousperiode);


--
-- TOC entry 2550 (class 2606 OID 467402)
-- Name: fk_type_sous_periode_sous_periode; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY sousperiode
    ADD CONSTRAINT fk_type_sous_periode_sous_periode FOREIGN KEY (idtypesousperiode) REFERENCES typesousperiode(idtypesousperiode);


--
-- TOC entry 2620 (class 2606 OID 467392)
-- Name: fk_typestructure_typestructure_service; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typestructureservice
    ADD CONSTRAINT fk_typestructure_typestructure_service FOREIGN KEY (idtypestructure) REFERENCES typestructure(idtypestructure);


--
-- TOC entry 2623 (class 2606 OID 467428)
-- Name: fk_typestructure_typestructurecategorie; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typestructurecategorie
    ADD CONSTRAINT fk_typestructure_typestructurecategorie FOREIGN KEY (idtypestructure) REFERENCES typestructure(idtypestructure);


--
-- TOC entry 2625 (class 2606 OID 475579)
-- Name: fk_typestructure_typestructureresponsabilite; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typestructureresponsabilite
    ADD CONSTRAINT fk_typestructure_typestructureresponsabilite FOREIGN KEY (idtypestructure) REFERENCES typestructure(idtypestructure);


--
-- TOC entry 2554 (class 2606 OID 286346)
-- Name: fk_utilisat_associati_structur; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY utilisateurstructure
    ADD CONSTRAINT fk_utilisat_associati_structur FOREIGN KEY (idstructure) REFERENCES structure(idstructure) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2555 (class 2606 OID 286351)
-- Name: fk_utilisat_associati_utilisat; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY utilisateurstructure
    ADD CONSTRAINT fk_utilisat_associati_utilisat FOREIGN KEY (idutilisateur) REFERENCES utilisateur(idutilisateur) ON UPDATE RESTRICT ON DELETE RESTRICT;


-- Completed on 2021-06-25 12:36:10

--
-- PostgreSQL database dump complete
--

