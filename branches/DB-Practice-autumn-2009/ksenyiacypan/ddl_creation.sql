CREATE TABLE "TEACHER" 
( "TEACHER_ID" NUMBER NOT NULL ENABLE, 
"EXAM_ID" NUMBER NOT NULL ENABLE,
"NAME" VARCHAR2(4000), 
CONSTRAINT "TEACHER_PK" PRIMARY KEY ("TEACHER_ID") ENABLE,
CONSTRAINT "TEACHER_FK" FOREIGN KEY ("EXAM_ID")
REFERENCES "EXAM" ("EXAM_ID") ENABLE
)
/

CREATE TABLE "EXAM" 
( "EXAM_ID" NUMBER NOT NULL ENABLE, 
"NAME" VARCHAR2(200), 
"TEACHER_ID" NUMBER NOT NULL ENABLE, 
CONSTRAINT "EXAM_PK" PRIMARY KEY ("EXAM_ID") ENABLE
)
/

CREATE TABLE "STUDENT" 
( "STUDENT_ID" NUMBER NOT NULL ENABLE, 
"SEX" VARCHAR2(200),
"NAME" VARCHAR2(4000), 
"ZA4ETKA_NUMBER" VARCHAR2(200), 
CONSTRAINT "STUDENT_PK" PRIMARY KEY ("STUDENT_ID") ENABLE
)
/

CREATE TABLE "STUDENT_  ZAOCHNIK" 
( "STUDENT_ZAOCH_ID" NUMBER NOT NULL ENABLE, 
"SEX_ZAOCH" VARCHAR2(200)FOREIGN KEY REFERENCES STUDENT(SEX),
"NAME_ZAOCH" VARCHAR2(4000)FOREIGN KEY REFERENCES STUDENT(NAME), 
"ZA4ETKA_NUMBER_ZAOCH" VARCHAR2(200)FOREIGN KEY REFERENCES STUDENT(ZA4ETKA_NAMBER),
CONSTRAINT "STUDENT_ZAOCH_PK" PRIMARY KEY ("STUDENT_ZAOCH_ID") ENABLE
)
/

CREATE TABLE "STUDENT_  OCHNIK" 
( "STUDENT_OCH_ID" NUMBER NOT NULL ENABLE, 
"SEX_OCH" VARCHAR2(200)FOREIGN KEY REFERENCES STUDENT(SEX),
"NAME_OCH" VARCHAR2(4000)FOREIGN KEY REFERENCES STUDENT(NAME),
"ZA4ETKA_NUMBER_OCH" VARCHAR2(200)FOREIGN KEY REFERENCES STUDENT(ZA4ETKA_NAMBER),
CONSTRAINT "STUDENT_OCH_PK" PRIMARY KEY ("STUDENT_OCH_ID") ENABLE
)
/

CREATE TABLE "STUDENT_EXAM" 
( "STUDENT_EXAM_ID" NUMBER NOT NULL ENABLE, 
"STUDENT_ID" NUMBER NOT NULL ENABLE, 
"EXAM_ID" NUMBER NOT NULL ENABLE, 
"MARK" NUMBER(1,0), 
CONSTRAINT "STUDENT_EXAM_PK" PRIMARY KEY ("STUDENT_EXAM_ID") ENABLE, 
CONSTRAINT "STUDENT_EXAM_FK" FOREIGN KEY ("STUDENT_ID")
REFERENCES "STUDENT" ("STUDENT_ID") ENABLE, 
CONSTRAINT "STUDENT_EXAM_FK2" FOREIGN KEY ("EXAM_ID")
REFERENCES "EXAM" ("EXAM_ID") ENABLE
)

/




CREATE OR REPLACE TRIGGER "BI_EXAM" 
before insert on "EXAM" 
for each row 
begin 
select "FORALL".nextval into :NEW.EXAM_ID from dual; 
end; 

/
ALTER TRIGGER "BI_EXAM" ENABLE
/

CREATE OR REPLACE TRIGGER "BI_STUDENT" 
before insert on "STUDENT" 
for each row 
begin 
select "FORALL".nextval into :NEW.STUDENT_ID from dual; 
end; 

/
ALTER TRIGGER "BI_STUDENT" ENABLE
/
CREATE OR REPLACE TRIGGER "BI_STUDENT_EXAM" 
before insert on "STUDENT_EXAM" 
for each row 
begin 
select "FORALL".nextval into :NEW.STUDENT_EXAM_ID from dual; 
end; 

/
ALTER TRIGGER "BI_STUDENT_EXAM" ENABLE
/
CREATE OR REPLACE TRIGGER "BI_TEACHER" 
before insert on "TEACHER" 
for each row 
begin 
select "FORALL".nextval into :NEW.TEACHER_ID from dual; 
end; 

/
ALTER TRIGGER "BI_TEACHER" ENABLE
/