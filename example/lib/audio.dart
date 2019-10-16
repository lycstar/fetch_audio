class Audio {
  int id;
  int albumId;
  String title;
  String album;
  String thumbUrl;
  String artist;
  String displayName;
  String url;
  String year;
  int duration;
  int size;

  Audio.fromMap(Map map) {
    id = map['id'];
    albumId = map['albumId'];
    title = map['title'];
    album = map['album'];
    thumbUrl = map['thumbUrl'];
    artist = map['artist'];
    displayName = map['displayName'];
    url = map['url'];
    year = map['year'];
    duration = map['duration'];
    size = map['size'];
  }
}
