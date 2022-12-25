-- liquibase formatted sql

--changeSet anovitskiy:1
CREATE INDEX student_name_index ON student (name);

--changeSet anovitskiy:2
CREATE INDEX faculty_nc_idx ON faculty (color, name);
