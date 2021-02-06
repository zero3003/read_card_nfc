import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:read_card_nfc/read_card_nfc.dart';

void main() {
  const MethodChannel channel = MethodChannel('read_card_nfc');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await ReadCardNfc.platformVersion, '42');
  });
}
