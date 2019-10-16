import 'dart:async';

import 'package:flutter/services.dart';

class FetchAudio {
  static const MethodChannel _channel = const MethodChannel('fetch_audio');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<List<dynamic>> get allAudios async {
    final List<dynamic> maps =
        await _channel.invokeMethod('getAllAudios').catchError((e) {
      print(e.toString());
    });
    return maps;
  }
}
