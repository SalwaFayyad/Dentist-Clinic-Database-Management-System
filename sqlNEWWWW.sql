  drop database if exists clinic;
create database clinic ;
		use clinic;
        
	drop table if exists Patients; 
		create table Patients(
		PID integer ,
		Pname varchar(32),
		Pphone varchar(32) ,
		Pgender varchar(16),
		Pemail varchar(64) ,
		Birthdate date not null,
		primary key (PID));
        
insert into Patients values(1,'Mohammad Nael','0698476367','male','mohammad12@gmail.com','2020-1-1');
insert into Patients values(2,'Adel Saleh','0569873456','male','salehadel@gmail.com','2010-1-3');
insert into Patients values(3,'Yasmine Hadi','09876544','female','yasmeen45@gmail.com','1999-1-1');
insert into Patients values(4,'Tala Jad','09876543','female','tala2001@gmail.com','2008-2-5');
insert into Patients values(5,'Taher Hussien','098766545','male','tahertaher@gmail.com','2000-3-9');
insert into Patients values(6,'Naya Ali','0987865789','female','nayaali89@gmail.com','2010-1-3');
insert into Patients values(7,'Mahmoud Almasri','059832158','male','mohamoudmasri@gmail.com','1999-1-1');
insert into Patients values(8,'Zahia Zaher','0987321678','female','zaherzahia12@gmail.com','2002-1-1');
insert into Patients values(9,'Mohammad Ramzi','098796547','male','mohammad120@gmail.com','1897-2-8');
insert into Patients values(10,'Laila Barakat','097845846','female','laila1999@gmail.com','1987-1-7');

        select*from patients;
        -- -----------------------------------------------------------------------------------------------------
        
        drop table if exists cost; 
		create table cost(
		PID integer ,
        PName varchar(32),
		Date_of_visit date,
		treatment_cost int,
		pay_cost int,
		-- remaining_cost  treatment_cost - pay_cost ,
		primary key(PID,Date_of_visit),
		foreign key (PID) references Patients(PID) on delete cascade

		);
		insert into cost values(1,'Mohammad Nael','2023-01-12',2000,500);
		insert into cost values(2,'Adel Saleh','2023-01-5',1500,300);
		insert into cost values(3,'Yasmine Hadi','2023-09-4',1000,700);
		insert into cost values(4,'Tala Jad','2020-09-8',300,300);
        insert into cost values(5,'Taher Hussien','2023-01-2',350,300);
        insert into cost values(6,'Naya Ali','2023-01-8',300,765);
        insert into cost values(7,'Mahmoud Almasri','2023-07-4',809,250);
        insert into cost values(8,'Zahia Zaher','2023-01-3',304,98);
        insert into cost values(9,'Mohammad Ramzi','2023-01-4',3876,654);
        insert into cost values(10,'Laila Barakat','2023-01-31',300,890);
      
		
        
        select*from cost;
	
		-- -----------------------------------------------------------------------------------------------------

        drop table if exists medicalstate; 
        create table medicalstate(
		PID integer ,
		PName varchar(32),
		Pmedicalstate varchar(64) ,
        DateOfLastVisit date not null,
        foreign key ( PID) references patients (pid ) on delete cascade,
		primary key (PID,Pmedicalstate,DateOfLastVisit)
        );
        
        insert into medicalstate values(1,'Mohammad Nael','Pregnant','2022-5-2');
		insert into medicalstate values(1,'Mohammad Nael','Diabetes','2022-4-28');
		insert into medicalstate values(2,'Adel Saleh','Diabetes','2023-2-20');
		insert into medicalstate values(3,'Yasmine Hadi','Diabetes','2023-2-20');
		insert into medicalstate values(3,'Yasmine Hadi','Pregnant','2023-3-12');
		insert into medicalstate values(3,'Yasmine Hadi','Pressure','2023-3-12');
		insert into medicalstate values(4,'Tala Jad','Pressure','2023-7-12');
		insert into medicalstate values(5,'Taher Hussien','Diabetes','2023-9-9');
		insert into medicalstate values(6,'Naya Ali','Pregnant','2023-7-9');
		insert into medicalstate values(7,'Mahmoud Almasri','Diabetes','2023-6-9');
		insert into medicalstate values(8,'Zahia Zaher','Pregnant','2023-7-12');
		insert into medicalstate values(9,'Mohammad Ramzi','Diabetes','2023-4-9');
		insert into medicalstate values(10,'Laila Barakati','Pregnant','2023-8-9');

          select*from medicalstate;
          
          insert into medicalstate (PID,PName,Pmedicalstate,DateOfLastVisit) values(1,'Mohammad Nael','Sensitive','2022-5-2');
                    insert into medicalstate (PID,PName,Pmedicalstate,DateOfLastVisit) values(2,'Mohammad Nael','Sensitive','2022-5-2');


        -- -----------------------------------------------------------------------------------------------------




		drop table if exists Diagnosis; 
		create table Diagnosis(
		Did integer, 
		Dtype varchar(32),
		primary key(Did));

insert into Diagnosis values(1,'Impacted Teeth');
insert into Diagnosis values(2,'Nerves');
insert into Diagnosis values(3,'Anodontia'); 
insert into Diagnosis values(4,'Supernumerary Teeth');
insert into Diagnosis values(5,'Motteled Teeth');
insert into Diagnosis values(6,'Embedded Teeth');
insert into Diagnosis values(7,'Dental Root Caries');
insert into Diagnosis values(8,'Radicular Cyst');
insert into Diagnosis values(9,'Periodontosis');
insert into Diagnosis values(10,'Partial Loss Of Teeth');
insert into Diagnosis values(11,'Retained Dental Root');
insert into Diagnosis values(12,'Pulpitis');
insert into Diagnosis values(13,'Cracked Tooth');
insert into Diagnosis values(14,'Ankylosis of Tooth');


select * from Diagnosis;
		-- -----------------------------------------------------------------------------------------------------


		drop table if exists Treatment; -- نوع العلاج 
		create table Treatment(
		Treatment_id integer,
		Did integer,
		Ttype varchar(32),
		Treatment_Cost_Paid_Amount double,
		primary key(Treatment_id),
		foreign key (Did) references Diagnosis(Did) on delete cascade);
                
        
insert into Treatment values(100,1,'Metal Crown',500);
insert into Treatment values(200,2,'Zironia',350);
insert into Treatment values(300,2,'Pulptomy',400);
insert into Treatment values(400,4,'All Ceramic',300);
insert into Treatment values(500,5,'Heavy Calculus',100);
insert into Treatment values(600,6,'Endodontic TRT',450);
insert into Treatment values(700,7,'Non vital Pulptomy',300);
insert into Treatment values(800,8,'Minor Calculus',600);
insert into Treatment values(900,9,'Post and core',50);
insert into Treatment values(1000,10,'Scaling',300);
insert into Treatment values(1100,11,'Loose',200);
insert into Treatment values(1200,12,'Abscess',150);
insert into Treatment values(1300,13,'Pocket cleaning',700);
insert into Treatment values(1400,14,'Cyst',250);
insert into Treatment values(1500,1,'Granuloma',200);
insert into Treatment values(1600,2,'Ulcered Gingiva',150);
insert into Treatment values(1700,3,'Swajed Crown',400);

select * from Treatment;
select Treatment_id from Treatment where Ttype ='Metal Crown';

        
        -- -----------------------------------------------------------------------------------------------------

		drop table if exists Perscription_Medication; -- التشخيص كامل 
		create table Perscription_Medication(
		PID integer,
        Pname varchar(32),
		Dname varchar(32),
        Treatment_id int,
        Tname varchar(32),
        lastDate Date,
        Notes varchar(128),
        primary key(PID,lastDate),
       foreign key (PID) references Patients(PID) on delete cascade,
	   foreign key (Treatment_id) references treatment(Treatment_id) on delete set null
		);

		insert into Perscription_Medication values(1,'Mohammad Nael','Nerves',100,'Metal Crown','2002-05-02','Take medicine three times a day  after eating');
		insert into Perscription_Medication values(2,'Adel Saleh','Anodontia',400,'All Ceramic','2002-06-02','Take medicine two times a day  before eating');
		insert into Perscription_Medication values(3,'Yasmine Hadi','Anodontia',400,'All Ceramic','2006-06-02','Take medicine three times a day  after eating');
        insert into Perscription_Medication values(4,'Tala Jad','Nerves',400,'All Ceramic','2006-03-02','Take medicine three times a day  after eating');
        insert into Perscription_Medication values(5,'Taher Hussien','Motteled Teeth',100,'Metal Crown','2006-06-02','Take medicine Two times a day  after eating');
        insert into Perscription_Medication values(6,'Naya Ali','Anodontia',400,'All Ceramic','2006-08-02','Take medicine one time a day  after eating');
        insert into Perscription_Medication values(7,'Mahmoud Almasri','Motteled Teeth',100,'Metal Crown','2023-9-7','Take medicine three times a day  after eating');
        insert into Perscription_Medication values(8,'Zahia Zaher','Anodontia',400,'All Ceramic','2006-9-8','Take medicine one Time before breakfast');
        insert into Perscription_Medication values(9,'Mohammad Ramzi','Nerves',400,'All Ceramic','2020-08-07','Take medicine three times a day  after eating');
        insert into Perscription_Medication values(10,'Laila Barakat','Partial Loss Of Teeth',400,'All Ceramic','2021-07-23','Take medicine three times a day ');
       
      select*from Perscription_Medication;

-- -------------------------------------------------------------------------------

		drop table if exists Appointments; 
		create table Appointments(
        Adate date ,
		Atime time check (Atime like '%%:00:%%' or Atime like '%%:30:%%'),
        Pname varchar(32),
		PID integer,
		primary key(Atime,Adate),
		foreign key (PID) references Patients(PID) on delete cascade);
        update Appointments set Adate='2023-02-11' where PID=3;
		select*from Appointments;
		insert into Appointments values('2023-01-12','12:30:00','Mohammad Nael',1);
		insert into Appointments values('2023-09-12','12:00:00','Adel Saleh',2);
		insert into Appointments values('2023-01-4','01:30:00','Yasmine Hadi',3);
        insert into Appointments values('2023-01-11','11:30:00','Tala Jad',4);
        insert into Appointments values('2023-04-11','9:30:00','Taher Hussien',5);
        insert into Appointments values('2023-11-5','10:00:40','Naya Ali',6);
		insert into Appointments values('2023-09-8','2:00:40','Mahmoud Almasri',7);
       insert into Appointments values('2022-07-5','3:00:00','Zahia Zaher',8);
		insert into Appointments values('2023-06-5','3:30:00','Mohammad Ramzi',9);
		insert into Appointments values('2023-09-2','12:00:00','Laila Barakat',10);
select * from Appointments ;
		


  