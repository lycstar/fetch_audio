import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:fetch_audio/fetch_audio.dart';

void main() {
  const MethodChannel channel = MethodChannel('fetch_audio');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await FetchAudio.platformVersion, '42');
  });
}
