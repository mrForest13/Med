update visit
set visit_date_from = TO_DATE('2017-01-09 07:30','YYYY-MM-DD HH24:MI'),
visit_date_to = TO_DATE('2017-01-09 08:00','YYYY-MM-DD HH24:MI')
where VISIT_ID=3 or VISIT_ID=14;