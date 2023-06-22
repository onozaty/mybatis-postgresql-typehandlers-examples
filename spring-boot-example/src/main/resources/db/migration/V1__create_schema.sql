-- enum
CREATE TYPE day AS ENUM ('SUNDAY', 'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY');
CREATE TABLE pgenum_records (
  id serial,
  day day
);

-- list
CREATE TABLE list_records (
  id serial,
  booleans boolean[],
  shorts smallint[],
  integers integer[],
  longs bigint[],
  floats real[],
  doubles double precision[],
  strings text[]
);

-- json
CREATE TABLE json_records (
  id serial,
  json JSONB
);
