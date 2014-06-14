use master;
if exists (select name from master.dbo.sysdatabases where name = N'app-domain-test')
begin 
	drop database [app-domain-test];
end

create database [app-domain-test];
use [app-domain-test];

create user "app-domain-test" from login "app-domain-test";

alter role [db_datareader] add member [app-domain-test];
alter role [db_datawriter] add member [app-domain-test];
alter role [db_ddladmin] add member [app-domain-test];
alter role [db_owner] add member [app-domain-test];
