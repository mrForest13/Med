--standards user
insert all into uzytkownik values
(1,0,'Marcin','Nowak','nowak','nowak')
into uzytkownik values
(2,0,'Jan','Kowalski','kowalski','kowalski')
into uzytkownik values
(3,0,'Anna','Nowak','anowak','anowak')
into uzytkownik values
(4,0,'Aneta','Pawlowska','pawlowska','pawlowska')
into uzytkownik values
(5,0,'Jacek','Makowski','makowski','makowski')
select * from dual;

insert all into uzytkownik_pacjent values
(1,1,'7204035334563','marcnow@vp.pl','400554333','1972-04-03','M')
into uzytkownik_pacjent values
(2,2,'8811130056334','jankow@vp.pl','400534333','1988-11-13','M')
into uzytkownik_pacjent values
(3,3,'2012313456334','annnow@vp.pl','477654333','2000-12-31','K')
into uzytkownik_pacjent values
(4,4,'9712033456334','annpaw@vp.pl','876554333','1997-12-03','K')
into uzytkownik_pacjent values
(5,5,'8512033456334','maku@vp.pl','65454333','1985-12-03','M')
select * from dual;


--lekarze
insert all into uzytkownik values
(6,1,'Bogdan','Twardowski','bogdan','bogdan')
into uzytkownik values
(7,1,'Janina','Debowska','janka','janka')
into uzytkownik values
(8,1,'Anna','Czyzykowska','czyzewska','czyzewska')
into uzytkownik values
(9,1,'Czeslaw','Nowakowski','nowakowska','nowakowska')
into uzytkownik values
(10,1,'Grzegorz','Makowski','gmakowski','gmakowski')
select * from dual;

insert all into uzytkownik_doktor values
(1,6,'dermatolog','lek. med.')
into uzytkownik_doktor values
(2,7,'stomatolog','lek. med.')
into uzytkownik_doktor values
(3,8,'stomatolog','lek. med.')
into uzytkownik_doktor values
(4,9,'chirurg stomatolog','lek. med.')
into uzytkownik_doktor values
(5,10,'neurochirurg','lek. med.')
select * from dual;


--pracownik_lab
insert all into uzytkownik values
(11,2,'Grazyna','Jurecka','grazyna','grazyna')
into uzytkownik values
(12,2,'Janina','Mak','janinamak','janinamak')
into uzytkownik values
(13,2,'Agata','Czerep','czerep','czerep')
select * from dual;

insert all into uzytkownik_lab values
(1,11,'mgr ','1','0')
into uzytkownik_lab values
(2,12,'mgr','0','1')
into uzytkownik_lab values
(3,13,'mgr','0','0')
select * from dual;


--obsluga
insert all into uzytkownik values
(14,3,'Karolina','Sobczak','karolinasobczak','karolinasobczak')
into uzytkownik values
(15,3,'Patrycja','Bialkowska','patrycjabialkowska','patrycjabialkowska')
into uzytkownik values
(16,3,'Weronika','Kowalczyk','weronikakowalczyk','weronikakowalczyk')
select * from dual;

insert all into uzytkownik_obsluga values
(1,14,'2013-04-03','2018-04-03')
into uzytkownik_obsluga values
(2,15,'2013-04-03','2017-04-03')
into uzytkownik_obsluga values
(3,16,'2013-04-03','2018-04-03')
select * from dual;


--recepta
insert all into recepta values
(1,1,10,'2016-05-13','Y')
into recepta values
(2,1,8,'2016-06-14','Y')
into recepta values
(3,2,7,'2016-09-30','N')
select * from dual;

--lek
insert all into lek values
(1,'Dermolox Forte',60)
into lek values
(2,'Visitan',0)
into lek values
(3,'Urolox',90)
into lek values
(4,'Dentax',70)
into lek values
(5,'Mafonax',10)
into lek values
(6,'Timax',20)
select * from dual;

--recepta_lek
insert all into recepta_lek values
(1,1,1)
into recepta_lek values
(2,2,1)
into recepta_lek values
(3,3,3)
into recepta_lek values
(4,6,3)
into recepta_lek values
(5,5,2)
into recepta_lek values
(6,1,2)
select * from dual;

--typy wizyt
insert all into visit_types values
(1,'Internista','N')
into visit_types values
(2,'Stomatolog','N')
into visit_types values
(3,'Dermatolog','N')
into visit_types values
(4,'Chirurg stomatolog','N')
into visit_types values
(5,'Neurochirurg','Y')
into visit_types values
(6,'Laboratorium','N')
into visit_types values
(7,'Ginekolog','Y')
select * from dual;

--wizyty
insert all into visit values
(1,7,5,2,TO_DATE('2017-01-02 15:30','YYYY-MM-DD HH24:MI'),TO_DATE('2017-01-02 16:30','YYYY-MM-DD HH24:MI'),0,'Y','')
into visit values
(2,7,5,1,TO_DATE('2017-01-15 16:30','YYYY-MM-DD HH24:MI'),TO_DATE('2017-01-15 17:00','YYYY-MM-DD HH24:MI'),100,'N','')
into visit values
(3,8,2,'',TO_DATE('2017-01-19 16:30','YYYY-MM-DD HH24:MI'),TO_DATE('2017-01-19 17:00','YYYY-MM-DD HH24:MI'),100,'N','')
into visit values
(4,8,2,'',TO_DATE('2017-01-19 18:30','YYYY-MM-DD HH24:MI'),TO_DATE('2017-01-19 19:00','YYYY-MM-DD HH24:MI'),100,'N','')
into visit values
(5,8,2,1,TO_DATE('2017-01-03 16:30','YYYY-MM-DD HH24:MI'),TO_DATE('2017-01-03 17:00','YYYY-MM-DD HH24:MI'),100,'Y','')
into visit values
(6,7,3,'',TO_DATE('2017-01-18 17:30','YYYY-MM-DD HH24:MI'),TO_DATE('2017-01-18 18:00','YYYY-MM-DD HH24:MI'),100,'N','')
into visit values
(7,7,3,'',TO_DATE('2017-01-16 11:30','YYYY-MM-DD HH24:MI'),TO_DATE('2017-01-16 12:00','YYYY-MM-DD HH24:MI'),100,'N','')
into visit values
(8,7,3,'',TO_DATE('2017-01-16 12:00','YYYY-MM-DD HH24:MI'),TO_DATE('2017-01-16 12:30','YYYY-MM-DD HH24:MI'),100,'N','')
into visit values
(9,7,3,1,TO_DATE('2017-01-05 13:30','YYYY-MM-DD HH24:MI'),TO_DATE('2017-01-05 14:00','YYYY-MM-DD HH24:MI'),100,'Y','')
into visit values
(10,8,7,'',TO_DATE('2017-01-17 16:30','YYYY-MM-DD HH24:MI'),TO_DATE('2017-01-17 17:00','YYYY-MM-DD HH24:MI'),100,'N','')
into visit values
(11,7,7,1,TO_DATE('2017-01-02 15:30','YYYY-MM-DD HH24:MI'),TO_DATE('2016-01-02 16:30','YYYY-MM-DD HH24:MI'),0,'Y','')
into visit values
(12,7,3,'',TO_DATE('2017-01-17 16:30','YYYY-MM-DD HH24:MI'),TO_DATE('2017-01-17 17:00','YYYY-MM-DD HH24:MI'),100,'N','')
into visit values --LABORATORIUM
(13,7,6,1,TO_DATE('2017-01-06 16:30','YYYY-MM-DD HH24:MI'),TO_DATE('2017-01-06 17:00','YYYY-MM-DD HH24:MI'),0,'Y','')
into visit values
(14,7,6,1,TO_DATE('2017-01-05 16:30','YYYY-MM-DD HH24:MI'),TO_DATE('2017-01-05 17:00','YYYY-MM-DD HH24:MI'),0,'Y','')
into visit values
(15,7,6,1,TO_DATE('2017-01-03 16:30','YYYY-MM-DD HH24:MI'),TO_DATE('2017-01-03 17:00','YYYY-MM-DD HH24:MI'),0,'Y','')
into visit values
(16,7,6,1,TO_DATE('2017-01-06 16:30','YYYY-MM-DD HH24:MI'),TO_DATE('2017-01-06 17:00','YYYY-MM-DD HH24:MI'),0,'Y','')
select * from dual;

--badania lab
insert all into lab values
(1,13,'','Y')
into lab values
(2,14,'','Y')
into lab values
(3,15,'','N')
into lab values
(4,16,'','Y')
select * from dual;

--poszczegolne wyniki
insert all into sample values
(1,1,'Morfologia','10','10','20','RO / ML','')
into sample values
(2,1,'Plytki krwi','35','5','50','RO / ML','wynik do konsultacji')
into sample values
(3,1,'Rozmaz','50','40','70','RO / ML','wynik do konsultacji')
into sample values
(4,2,'TSH','4','2','7','RO / ML','')
into sample values
(5,2,'Testosteron','100','50','200','RO / ML','wynik do konsultacji')
into sample values
(6,1,'Progesteron','10','2','80','RO / ML','')
into sample values
(7,4,'Igg','50','40','70','RO / ML','')
into sample values
(8,4,'Ign','50','40','70','RO / ML','')
into sample values
(9,4,'Rozmaz','50','40','70','RO / ML','wynik do konsultacji')
select * from dual;

--skierowania
insert all into referal values
(1,1,6,7,'N','2013-04-03')
into referal values
(2,2,9,5,'N','2013-04-03')
into referal values
(3,3,7,5,'N','2013-04-03')
into referal values
(4,3,8,7,'N','2013-04-03')
select * from dual;