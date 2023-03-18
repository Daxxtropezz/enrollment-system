CREATE SCHEMA enrollmentsystem;
USE enrollmentsystem;
SET SQL_SAFE_UPDATES = 0;

CREATE TABLE account
(
	a_user varchar(25) primary key,
    a_pass varchar(50) not null,
    a_type varchar(25) not null
);

CREATE TABLE TEACHER
(
	t_num int auto_increment primary key,
    t_img varchar(300) not null,
    t_name varchar(150) not null,
    t_email varchar(100) not null,
    t_contact varchar(15) not null,
    t_sex varchar(10) not null,
    t_bdate date not null,
    a_user varchar(25) not null,
    CONSTRAINT aUser1FK FOREIGN KEY (a_user) REFERENCES account(a_user)
);
ALTER TABLE TEACHER auto_increment = 7202100;

CREATE TABLE SECTION
(
	sec_code varchar(50) primary key,
    sec_name varchar(300) not null,
    sec_adviser varchar(300) not null,
    sec_schoolyear varchar(50) not null,
    t_num int not null,
    CONSTRAINT tNum1FK FOREIGN KEY (t_num) REFERENCES teacher(t_num)
);

CREATE TABLE SCHEDULE
(
	s_code int auto_increment primary key,
    s_subject varchar(100) not null,
    s_description varchar(700) not null,
    s_day varchar(10) not null,
    s_starttime time not null,
    s_endtime time not null,
    s_teacher varchar(300) not null,
    sec_code varchar(50) not null,
    CONSTRAINT secCodeFK FOREIGN KEY (sec_code) REFERENCES section(sec_code),
    t_num int not null,
    CONSTRAINT tNum2FK FOREIGN KEY (t_num) REFERENCES teacher(t_num)
);
ALTER TABLE schedule auto_increment = 2021000;

CREATE TABLE enrollee
(
	e_num int auto_increment primary key,
    e_stat varchar(15) not null,
    e_fname varchar(50) not null,
    e_lname varchar(50) not null,
    e_minit varchar(3) not null,
    e_sex varchar(10) not null,
    e_email varchar(100) not null,
    e_address varchar(200) not null,
    e_bdate date not null,
    e_religion varchar(50) not null,
    e_nationality varchar(50) not null,
    e_civilstatus varchar(15) not null,
    e_contactnum varchar(15) not null,
    e_guardian varchar(100) not null,
    e_guardiannum varchar(15) not null,
    sec_code varchar(50) not null,
    CONSTRAINT secCdFK FOREIGN KEY (sec_code) REFERENCES section(sec_code),
    a_user varchar(25) not null,
    CONSTRAINT aUser2FK FOREIGN KEY (a_user) REFERENCES account(a_user)
);
ALTER TABLE enrollee auto_increment = 100;

CREATE TABLE requirements
(
	req_ID int auto_increment primary key,
    ReportCard varchar(300) not null,
    Form137 varchar(300) not null,
    BirthCertificate varchar(300) not null,
    GoodMoral varchar(300) not null,
    TranscriptOfRecords varchar(300) not null,
    e_num int not null,
    CONSTRAINT eNumFK FOREIGN KEY (e_num) REFERENCES enrollee(e_num)
);
ALTER TABLE requirements auto_increment = 202100;

INSERT INTO account 
VALUES
('unknown','unknown','UNKNOWN'),
('chris.derla','derla0721','TEACHER'),
('miss.tres','josianpride71','TEACHER'),
('velezcruz21','francecruz57','TEACHER'),
('randyOlan','11stan21','TEACHER'),
('prettygrace','nico42','TEACHER'),
('manolo.gary77','playpal77','TEACHER'),
('nuestro.nolan','nolannuestro8','TEACHER'),
('kaRAPATAN','mabuhay22','TEACHER'),
('cardo56','cardoramosmalakas27','TEACHER'),
('topaz.john','riley73','TEACHER'),
('larry78','bulaklak78','TEACHER'),
('ms.garin','ferdinand74','TEACHER'),
('ms.jingle','rupert71','TEACHER'),
('rupertcruz','michellevelez96','TEACHER'),
('rosesantos','chichay123','TEACHER'),
('madam_diane','tropangjosiah8','TEACHER'),
('ms.gabby','hernandez654','TEACHER'),
('cruz.rowell','sirrowell65','TEACHER');

INSERT INTO teacher 
VALUES
(6202100,'N/A','N/A','N/A','N/A','N/A','0000-00-00','unknown'),
(6202101,'N/A','Christine Derla','christinederla@gmail.com','09300873437','FEMALE','1986-07-21','chris.derla'),
(6202102,'N/A','Judy Trespeces','judytrespeces@gmail.com','09631668949','FEMALE','1988-10-20','miss.tres'),
(6202103,'N/A','Michelle Velez','michellevelez@gmail.com','09267874474','FEMALE','1982-01-09','velezcruz21'),
(6202104,'N/A','Randy Olanda','randyolanda@gmail.com','09946899265','MALE','1978-08-11','randyOlan'),
(6202105,'N/A','Grace Faustino','gracefaustino@gmail.com','09597201226','FEMALE','1993-01-06','prettygrace'),
(6202106,'N/A','Gary Manolo','garymanolo@gmail.com','09499263446','MALE','1976-01-26','manolo.gary77'),
(6202107,'N/A','Danielle Nuestro','danillenuestro@gmail.com','09784944262','MALE','1966-08-28','nuestro.nolan'),
(6202108,'N/A','Christoff Rapatan','christoffrapatan@gmail.com','09388858877','MALE','1970-03-19','kaRAPATAN'),
(6202109,'N/A','Ricardo Ramos','ricardoramos@gmail.com','09466522142','MALE','1983-05-09','cardo56'),
(6202110,'N/A','John Topaz','johntopaz@gmail.com','09103533888','MALE','1984-03-15','topaz.john'),
(6202111,'N/A','Larry Embiado','larryembiado@gmail.com','09904677247','MALE','1973-04-10','larry78'),
(6202112,'N/A','Jessille Garin','jessillegarin@gmail.com','09797720256','FEMALE','1985-12-26','ms.garin'),
(6202113,'N/A','Jingle Ola','jingleola@gmail.com','09264750938','FEMALE','1992-07-05','ms.jingle'),
(6202114,'N/A','Rue Cruz','ruecruz96@gmail.com','09923290217','MALE','1977-09-29','rupertcruz'),
(6202115,'N/A','Rose Santos','rosesantos@gmail.com','09649152828','FEMALE','1984-03-15','rosesantos'),
(6202116,'N/A','Diane Ramirez','dianeramirez@gmail.com','09517876255','FEMALE','1975-10-13','madam_diane'),
(6202117,'N/A','Gabby Hernandez','gabbyhernandez@gmail.com','09674806113','FEMALE','1980-01-31','ms.gabby'),
(6202118,'N/A','Rowell Cruz','rowellcruz65@gmail.com','09666532052','MALE','1973-01-12','cruz.rowell');

INSERT INTO section VALUES('N/A','N/A','N/A','N/A', 6202100);
INSERT INTO schedule(s_subject,s_description,s_day,s_starttime,s_endtime,s_teacher,sec_code,t_num) 
VALUES('','N/A','N/A','00:00:00','00:00:00','N/A','N/A',6202100);

INSERT INTO SECTION 
VALUES
('JON20210601' ,'7 - Rizal'      ,'Randy Olanda'   ,'2020-2021',6202104),
('JTW20210602' ,'8 - Sampaguita' ,'Michelle Velez' ,'2020-2021',6202103),
('JTH20210603' ,'9 - Oxygen'	 ,'Rowell Cruz'	   ,'2020-2021',6202118),
('JFO20210604' ,'10 - Gold'	 ,'Rose Santos'	   ,'2020-2021',6202115);

INSERT INTO SCHEDULE (s_subject,s_description,s_day,s_starttime,s_endtime,s_teacher,sec_code,t_num) 
VALUES
/*FIRST YEAR*/
('Filipino 1'                 ,"Ang layunin ng pagtuturo ng kursong ito ay malinang ang kakayahang komunikatibo, mapanuring pag-iisip at pangunawa at pagpapahalagang pampanitikan gamit ang teknolohiya at iba’t ibang uri ng teksto at akdang pampanitikang rehiyunal upang maipagmalaki ang sariling kultura, gayundin ang iba’t ibang kulturang panrelihiyon.",
	'M/TH'   ,'07:00:00','07:50:00','Judy Trespeces'		,'JON20210601',6202102),
('English 1'                  ,"This course introduces the student to Philippine Literature and other text types which aim to provide him/her the necessary communicative competence to develop a deeper understanding and appreciation of Philippine Culture.",
	'T/F'    ,'07:50:00','08:40:00','Grace Faustino'		,'JON20210601',6202105),
('Mathematics 1'              ,"The course covers key concepts and principles of numbers and number sense (sets and real number system); measurement (conversion of units of measurement); patterns and algebra (algebraic expressions and properties of real numbers as applied in linear equations and inequalities in one variable); and geometry (sides and angles of polygons) as applied – using appropriate technology – in critical thinking, problem solving, reasoning, communicating, making connections, representations, and decisions in real life.",
	'M/TH'   ,'08:40:00','09:30:00','Christine Derla'	,'JON20210601',6202101),
('Science 1'                  ,"The course covers key concepts and skills to enable learners recognize the system of classification of matter through semi-guided investigations but emphasizing fair testing.",
	'T/F'    ,'09:30:00','10:20:00','Michelle Velez'		,'JON20210601',6202101),
('BREAK TIME'                 ,"-",'-'      ,'10:20:00','10:40:00','-'					,'JON20210601',6202100),
('Araling Panlipunan 1'       ,"Ang kursong ito ay sumasaklaw sa paksaing “Pampanga” at “Araling Asyano” na naglalayong malinang sa mga mag-aaral ang malalim na pag-unawa at pagpapahalaga sa kamalayan sa heograpiya, kasaysayan, kultura, lipunan, pamahalaan, at ekonomiya ng Pampanga at ng Asya tungo sa pagbuo ng pagkakakilanlang Kapampangan at Asyano at magkakatuwang na pag-unlad at pagharap sa mga hamon sa Pampanga at sa Asya.",
	'W/F'    ,'10:40:00','11:10:00','Randy Olanda'		,'JON20210601',6202104),
('ESP 1'					  ,"Ang Edukasyon sa Pagpapakatao (EsP) ay isa sa mga asignatura ng Pinaunlad na Programa ng Batayang Edukasyon na K to 12 na gagabay at huhubog sa mga kabataan. Tunguhin nito ang paghubog ng kabataang nagpapasya at kumikilos nang mapanagutan tungo sa kabutihang panlahat.",
	'T'      ,'11:10:00','12:30:00','Gary Manolo'		,'JON20210601',6202106),
('MAPEH 1'                    ,"This program aims to equip students with necessary knowledge and skills to live healthily. In general, MAPEH is designed to develop the physical, mental, emotional and artistic/ aesthetic abilities of every student.",
	'W/TH'   ,'12:30:00','01:20:00','Danielle Nuestro'	,'JON20210601',6202107),
('TLE 1'                      ,"It aims to reinforce the work skill training and value orientation of the first year students. It exposes the learner to a variety of experiences which are meaningful and relevant to his development.",
	'M'      ,'01:20:00','02:00:00','Ricardo Ramos'		,'JON20210601',6202109),
/*SECOND YEAR*/
('Filipino 2'                 ,"Ang layunin ng pagtuturo ng kursong ito ay malinang ang kakayahang komunikatibo, mapanuring pag-iisip at pangunawa at pagpapahalagang pampanitikan gamit ang teknolohiya at iba’t ibang uri ng teksto at akdang pampanitikang pambansa upang maipagmalaki ang kulturang Pilipino.",
	'M/TH'   ,'07:00:00','07:50:00','John Topaz'			,'JTW20210602',6202110),
('English 2'                  ,"This course focuses on developing the student’s communicative competence as demonstrated through his/her understanding of Afro-Asian literature and other texts types for a deeper appreciation of Philippine culture and those of other countries.",
	'T/F'    ,'07:50:00','08:40:00','Larry Embiado'		,'JTW20210602',6202111),
('Mathematics 2'              ,"The course covers key concepts and principles of patterns and algebra (factors of polynomials, rational algebraic expressions, linear equations and inequalities in two variables, systems of linear equations and inequalities in two variables); geometry (axiomatic structure of geometry, triangle congruence, inequalities in a triangle, and parallel and perpendicular lines) as applied - using appropriate technology - in critical thinking, problem solving, reasoning, communicating, making connections, representations, and decisions in real life.",
	'M/TH'   ,'08:40:00','09:30:00','Jessille Garin'		,'JTW20210602',6202112),
('Science 2'                  ,"The course covers key concepts and skills to enable learners recognize reproduction as a process of cell division resulting in growth of organisms."
   ,'T/F'    ,'09:30:00','10:20:00','Jingle Ola'			,'JTW20210602',6202112),
('BREAK TIME'                 ,"-",'-'      ,'10:20:00','10:40:00','-'					,'JTW20210602',6202100),
('Araling Panlipunan 2'       ,"Ang kursong ito ay sumasaklaw sa paksaing “Kasaysayan ng Daigdig” na naglalayong malinang sa mga mag-aaral ang mga kasanayan sa pagsisiyasat, pagsusuri ng datos at iba’t ibang sanggunian, pagsasaliksik, mapanuring pag-iisip, mabisang komunikasyon, at pag-unawa sa kasaysayan, politika, ekonomiya, kultura, at lipunan ng Daigdig mula sa sinaunang panahon hanggang sa kasalukuyan.",
	'W/F'    ,'10:40:00','11:10:00','Rue Cruz'		,'JTW20210602',6202114),
('ESP 2'					  ,"Ang Edukasyon sa Pagpapakatao (EsP) ay isa sa mga asignatura ng Pinaunlad na Programa ng Batayang Edukasyon na K to 12 na gagabay at huhubog sa mga kabataan. Tunguhin nito ang paghubog ng kabataang nagpapasya at kumikilos nang mapanagutan tungo sa kabutihang panlahat.",
	'T'      ,'11:10:00','12:30:00','Rose santos'		,'JTW20210602',6202115),
('MAPEH 2'                    ,"This program aims to equip students with necessary knowledge and skills to live healthily. In general, MAPEH is designed to develop the physical, mental, emotional and artistic/ aesthetic abilities of every student.",
	'W/TH'   ,'12:30:00','01:20:00','Diane Ramirez'		,'JTW20210602',6202116),
('TLE 2'                      ,"It aims to reinforce the work skill training and value orientation of the first year students. It exposes the learner to a variety of experiences which are meaningful and relevant to his development.",
	'M'      ,'01:20:00','02:00:00','Rue Cruz'		,'JTW20210602',6202114),
/*THIRD YEAR*/
('Filipino 3'                 ,"Ang layunin ng pagtuturo ng kursong ito ay malinang ang kakayahang komunikatibo, mapanuring pag-iisip at pangunawa at pagpapahalagang pampanitikan gamit ang teknolohiya at iba’t ibang uri ng teksto at saling akdang Asyano upang mapatibay ang pagkakakilanlang Asyano.",
	'M'       ,'07:00:00','07:50:00','Christine Derla'	,'JTH20210603',6202101),
('English 3'                  ,"This course is designed for the student to demonstrate communicative competence through his/her understanding of British-American Literature, including Philippine Literature and other text types for a deeper appreciation of Philippine Culture and those of other countries.",
	'W/TH'    ,'07:50:00','08:40:00','Judy Trespeces'	,'JTH20210603',6202102),
('Mathematics 3'              ,"The course covers key concepts and principles of patterns and algebra (sequences, series, polynomials, polynomial equations, and polynomial functions); geometry (circles and coordinate geometry); and statistics and probability (combinatorics and probability, and measures of position) as applied - using appropriate technology - in critical thinking, problem solving, reasoning, communicating, making connections, representations, and decisions in real life.",
	'T'       ,'08:40:00','09:30:00','Michelle Velez'	,'JTH20210603',6202103),
('Science 3'                  ,"The course covers key concepts and skills to enable learners expand their knowledge to a deeper understanding of the respiratory and circulatory systems to promote overall health.",
	'W/F'     ,'09:30:00','10:20:00','Randy Olanda'		,'JTH20210603',6202104),
('BREAK TIME'                 ,"-",'-'       ,'10:20:00','10:40:00','-'					,'JTH20210603',6202100),
('Araling Panlipunan 3'       ,"This course is the study of economics which aims to develop among the students a deep understanding and appreciation of the basic concepts and timely issues in economics that practices and appreciates disciplines in social sciences toward the development of a citizen of the country and the world who critically analyzes, contemplates, values nature, productive, fair and humane.",
	'T/F'     ,'10:40:00','11:10:00','Grace Faustino'	,'JTH20210603',6202105),
('ESP 3'					  ,"Ang Edukasyon sa Pagpapakatao (EsP) ay isa sa mga asignatura ng Pinaunlad na Programa ng Batayang Edukasyon na K to 12 na gagabay at huhubog sa mga kabataan. Tunguhin nito ang paghubog ng kabataang nagpapasya at kumikilos nang mapanagutan tungo sa kabutihang panlahat.",
	'M/TH'    ,'11:10:00','12:30:00','Gary Manolo'		,'JTH20210603',6202105),
('MAPEH 3'                    ,"This program aims to equip students with necessary knowledge and skills to live healthily. In general, MAPEH is designed to develop the physical, mental, emotional and artistic/ aesthetic abilities of every student.",
	'T/F'     ,'12:30:00','01:20:00','Danielle Nuestro'	,'JTH20210603',6202107),
('TLE 3'                      ,"It aims to reinforce the work skill training and value orientation of the first year students. It exposes the learner to a variety of experiences which are meaningful and relevant to his development.",
	'M/TH'    ,'01:20:00','02:00:00','Ricardo Ramos'		,'JTH20210603',6202109),
/*FOURTH YEAR*/
('Filipino 4'                 ,"Ang layunin ng pagtuturo ng kursong ito ay malinang ang kakayahang komunikatibo, mapanuring pag-iisip at pangunawa at pagpapahalagang pampanitikan gamit ang teknolohiya at iba’t ibang uri ng teksto at saling-akdang pandaigdig tungo sa pagkakaroon ng kamalayang global.",
	'M'  	,'07:00:00','07:50:00','John Topaz'			,'JFO20210604',6202109),
('English 4'                  ,"This course is designed to enhance the student’s communicative competence that will demonstrate his/her understanding of literature and other text types for a deeper appreciation of World Literature, including Philippine Literature. The course also incorporates an understanding of how world literature and other text types serve as sources of wisdom in resolving conflicts.",
	'W/TH'   ,'07:50:00','08:40:00','Larry Embiado'		,'JFO20210604',6202111),
('Mathematics 4'              ,"The course covers key concepts and principles of patterns and algebra (sequences, series, polynomials, polynomial equations, and polynomial functions); geometry (circles and coordinate geometry); and statistics and probability (combinatorics and probability, and measures of position) as applied - using appropriate technology - in critical thinking, problem solving, reasoning, communicating, making connections, representations, and decisions in real life.",
	'T'   	,'08:40:00','09:30:00','Jessille Garin'		,'JFO20210604',6202112),
('Science 4'                  ,"The course covers key concepts and skills to enable learners to complete the study of the entire organism with their deeper study of the excretory and reproductive systems; explain in greater detail how genetic information is passed from parents to offspring and how diversity of species increases the probability of adaptation and survival in changing environments.",
	'W/F'    ,'09:30:00','10:20:00','Jingle Ola'			,'JFO20210604',6202113),
('BREAK TIME'                 ,"-",'-'      ,'10:20:00','10:40:00','-'					,'JFO20210604',6202113),
('Araling Panlipunan 4'       ,"This course is the study of economics which aims to develop among the students a deep understanding and appreciation of the basic concepts and timely issues in economics that practices and appreciates disciplines in social sciences toward the development of a citizen of the country and the world who critically analyzes, contemplates, values nature, productive, fair and humane.",
	'T/F'    ,'10:40:00','11:10:00','Rowell Cruz'		,'JFO20210604',6202118),
('ESP 4'					  ,"Ang Edukasyon sa Pagpapakatao (EsP) ay isa sa mga asignatura ng Pinaunlad na Programa ng Batayang Edukasyon na K to 12 na gagabay at huhubog sa mga kabataan. Tunguhin nito ang paghubog ng kabataang nagpapasya at kumikilos nang mapanagutan tungo sa kabutihang panlahat.",
	'M/TH'	,'11:10:00','12:30:00','Rose santos'		,'JFO20210604',6202115),
('MAPEH 4'                    ,"This program aims to equip students with necessary knowledge and skills to live healthily. In general, MAPEH is designed to develop the physical, mental, emotional and artistic/ aesthetic abilities of every student.",
	'T/F'   	,'12:30:00','01:20:00','Diane Ramirez'		,'JFO20210604',6202116),
('TLE 4'                      ,"It aims to reinforce the work skill training and value orientation of the first year students. It exposes the learner to a variety of experiences which are meaningful and relevant to his development.",
	'M/TH'	,'01:20:00','02:00:00','Rowell Cruz'		,'JFO20210604',6202118);