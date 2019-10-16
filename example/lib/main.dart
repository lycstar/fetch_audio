import 'dart:async';
import 'dart:io';

import 'package:fetch_audio/fetch_audio.dart';
import 'package:fetch_audio_example/audio.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  List<Audio> _audios = [];

  @override
  void initState() {
    super.initState();
    getAllAudios();
  }

  Future<void> getAllAudios() async {
    List<Audio> audios = [];
    try {
      List<dynamic> maps = await FetchAudio.allAudios;
      audios = maps.map((m) => new Audio.fromMap(m)).toList();
    } on PlatformException {
      print('Failed to get all Audios.');
    }
    if (!mounted) return;
    setState(() {
      _audios = audios;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('FetchAudio'),
        ),
        body: Center(
          child: ListView(
            children: _audios.map((audio) {
              return Card(
                elevation: 2,
                child: Container(
                  margin: EdgeInsets.all(10),
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: <Widget>[
                      Image.file(
                        File(audio.thumbUrl),
                        height: 40,
                        width: 40,
                      ),
                      Text(audio.id.toString()),
                      Text(audio.albumId.toString()),
                      Text(audio.title.toString()),
                      Text(audio.artist.toString()),
                      Text(audio.album.toString()),
                      Text(audio.displayName.toString()),
                      Text(audio.url.toString()),
                      Text(audio.duration.toString()),
                      Text(audio.year.toString()),
                      Text(audio.size.toString())
                    ],
                  ),
                ),
              );
            }).toList(),
          ),
        ),
      ),
    );
  }
}
