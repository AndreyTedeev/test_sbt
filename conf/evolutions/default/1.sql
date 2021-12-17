-- Created by Ebean DDL
-- To stop Ebean DDL generation, remove this comment and start using Evolutions

-- !Ups
create table "users" (
  id uuid not null,
  constraint pk_user primary key (id)
);

create table "user_external_id" (
  user_id uuid not null,
  system_type int not null,
  "value" text not null,
  constraint pk_external_id primary key (user_id, system_type),
  constraint fk_user foreign key (user_id) references "users"(id)
);


create table "tenant" (
  id uuid not null,
  host text not null,
  constraint pk_tenant primary key (id)
);

create table "user_tenant" (
  user_id uuid not null,
  tenant_id uuid not null,
  constraint pk_user_tenant primary key (user_id, tenant_id),
  constraint fk_user foreign key (user_id) references "users"(id),
  constraint fk_tenant foreign key (tenant_id) references tenant(id)
);


-- !Downs
drop table if exists "user_external_id";

drop table if exists "user_tenant";

drop table if exists "tenant";

drop table if exists "users";
