CREATE OR REPLACE FORCE VIEW shot_status AS SELECT
       ug.USER_GAME_ID AS player_id,
       sh.X_COORD AS shot_coord_x,
       sh.Y_COORD AS shot_coord_y,
       CASE WHEN sl.SHIP_ID IS NULL THEN false ELSE true END AS hit
FROM user_game AS ug
         JOIN shot AS sh
              ON ug.USER_GAME_ID = sh.TO_USER_GAME_ID
         LEFT JOIN ship_location AS sl
                   ON ug.USER_GAME_ID = sl.USER_GAME_ID
                       AND sl.SHIP_COORDX = sh.X_COORD
                       AND sl.SHIP_COORDY = sh.Y_COORD;
