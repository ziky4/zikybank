insert into TXTYPE VALUES(1, 'transfer')
insert into TXTYPE VALUES(2, 'deposit')
insert into TXTYPE VALUES(3, 'withdraw')

insert into BANK VALUES(4444, 'Ziky bank')
insert into BANK VALUES(2277, 'CSOB')
insert into BANK VALUES(1441, 'KB')
insert into BANK VALUES(9966, 'NB')

insert into CURRENCY VALUES('cz', 'Kc')
insert into CURRENCY VALUES('eu', '$')
insert into CURRENCY VALUES('en', '€')

insert into ACCOUNTTYPE VALUES(1, 5.0, 2.0, 60, 'savings')
insert into ACCOUNTTYPE VALUES(2, 5.0, 2.0, 60, 'personal')
insert into ACCOUNTTYPE VALUES(3, 5.0, 2.0, 60, 'student')

insert into ROLE VALUES('user')
insert into ROLE VALUES('employee')
insert into ROLE VALUES('admin')

insert into PERSON VALUES('1000', 'Cajk 7 Havirov', DATE('05/04/1992'), 'zikadad@email.com', 'Radek', 6056878, 'Zika', '21232f297a57a5a743894a0e4a801fc3', '600599887')
insert into PERSON VALUES('1001', 'Kala 4 Ostrava', DATE('02/01/1973'), 'lololo@email.com', 'Petr', 4488779, 'Vida', '21232f297a57a5a743894a0e4a801fc3', '702564893')
insert into PERSON VALUES('1002', 'Nova 1 Karvina', DATE('11/11/1968'), 'asdd@email.com', 'Josef', 1122778, 'Cajk', '21232f297a57a5a743894a0e4a801fc3', '774125663')
insert into PERSON VALUES('1003', 'Bila 6 Trinec', DATE('10/26/1989'), 'grrr@email.com', 'Jiri', 6050201, 'Kulo', '21232f297a57a5a743894a0e4a801fc3', '666882564')

insert into PERSON_ROLE VALUES('1000', 'user')
insert into PERSON_ROLE VALUES('1001', 'user')
insert into PERSON_ROLE VALUES('1002', 'employee')
insert into PERSON_ROLE VALUES('1003', 'admin')

insert into ACCOUNT VALUES(101010, 2000.0, TIMESTAMP('2014-01-01 14:03:20'), 5000, 20000, 30000, 'cz', 2, '1000')
insert into ACCOUNT VALUES(101515, 2450.0, TIMESTAMP('1014-02-02 16:25:19'), 3000, 10000, 15000, 'eu', 1, '1001')