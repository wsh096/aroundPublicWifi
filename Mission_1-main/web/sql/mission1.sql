--
-- File generated with SQLiteStudio v3.4.3 on 목 2 23 00:59:05 2023
--
-- Text encoding used: UTF-8
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: BOOKMARK
CREATE TABLE IF NOT EXISTS BOOKMARK (BOOKMARK_ID INTEGER PRIMARY KEY AUTOINCREMENT, BOOKMARK_GROUP_ID INTEGER REFERENCES BOOKMARKGROUP (BOOKMARK_GROUP_ID) ON DELETE SET NULL ON UPDATE SET NULL, WIFI_INFO_ID INTEGER REFERENCES WIFI_INFO (ID) ON DELETE CASCADE ON UPDATE CASCADE, BOOKMARK_CD TIMESTAMP DEFAULT (datetime('now', 'localtime')));

-- Table: BOOKMARKGROUP
CREATE TABLE IF NOT EXISTS BOOKMARKGROUP (BOOKMARK_GROUP_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, BOOKMARK_GROUP_NM TEXT NOT NULL UNIQUE, BOOKMARK_GROUP_SEQ INTEGER NOT NULL UNIQUE, BOOKMARK_GROUP_CD DATETIME DEFAULT (datetime('now', 'localtime')), BOOKMARK_GROUP_ED DATETIME DEFAULT NULL);

-- Table: LOCHISTORY
CREATE TABLE IF NOT EXISTS LOCHISTORY (ID INTEGER PRIMARY KEY AUTOINCREMENT, LAT REAL NOT NULL, LNT REAL NOT NULL, TIME_LOG TIMESTAMP DEFAULT (datetime('now', 'localtime')) NOT NULL);

-- Table: WIFI_INFO
CREATE TABLE IF NOT EXISTS "WIFI_INFO" (

                                           "ID" INTEGER PRIMARY KEY AUTOINCREMENT,

                                           "X_SWIFI_MGR_NO"	TEXT NOT NULL,

                                           "X_SWIFI_WRDOFC"	TEXT,

                                           "X_SWIFI_MAIN_NM"	TEXT NOT NULL,

                                           "X_SWIFI_ADRES1"	TEXT,

                                           "X_SWIFI_ADRES2"	TEXT,

                                           "X_SWIFI_INSTL_FLOOR"	TEXT,

                                           "X_SWIFI_INSTL_MBY"	TEXT,

                                           "X_SWIFI_INSTL_TY"	TEXT,

                                           "X_SWIFI_SVC_SE"	TEXT,

                                           "X_SWIFI_CMCWR"	TEXT,

                                           "X_SWIFI_CNSTC_YEAR"	TEXT,

                                           "X_SWIFI_INOUT_DOOR"	TEXT,

                                           "X_SWIFI_REMARS3"	INTEGER,

                                           "LNT"	REAL,

                                           "LAT"	REAL,

                                           "WORK_DTTM"	TEXT

);

-- Trigger: UPDATE_ED
CREATE TRIGGER IF NOT EXISTS UPDATE_ED AFTER INSERT ON BOOKMARK BEGIN UPDATE BOOKMARKGROUP SET BOOKMARK_GROUP_ED = datetime('now', 'localtime') WHERE BOOKMARK_GROUP_ID = NEW.BOOKMARK_GROUP_ID; END;

-- Trigger: UPDATE_ED2
CREATE TRIGGER IF NOT EXISTS UPDATE_ED2 AFTER DELETE ON BOOKMARK BEGIN UPDATE BOOKMARKGROUP SET BOOKMARK_GROUP_ED = datetime('now', 'localtime') WHERE BOOKMARK_GROUP_ID = old.BOOKMARK_GROUP_ID; END;

-- Trigger: UPDATE_ED3
CREATE TRIGGER IF NOT EXISTS UPDATE_ED3 AFTER UPDATE ON BOOKMARK BEGIN UPDATE BOOKMARKGROUP SET BOOKMARK_GROUP_ED = datetime('now', 'localtime') WHERE BOOKMARK_GROUP_ID = NEW.BOOKMARK_GROUP_ID; END;

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
