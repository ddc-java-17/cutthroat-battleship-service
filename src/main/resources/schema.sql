SELECT game AS gm JOIN gm.userGame AS ug,
JOIN ug.shipLocations AS sl,
JOIN ug.toUserGames AS tug,
JOIN ug.fromUserGame AS fug,
WHEN sl.userGame = tug.userGame
(sl.x = tug.x AND sl.y = tug.y) AS hit