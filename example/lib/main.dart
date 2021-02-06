import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:read_card_nfc/card_model.dart';
import 'package:read_card_nfc/read_card_nfc.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  ValueNotifier<CardModel> cardData = ValueNotifier(CardModel());
  CardModel cardModel;

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Column(
          children: [
            RaisedButton(
              onPressed: () {
                ReadCardNfc.instance.startSession(onDiscovered: (card) async {
                  cardData.value = card;

                  ///stop broadcast
                  ReadCardNfc.instance.stopSession();
                });
              },
              child: Text('Start'),
            ),
            Center(
              child: ValueListenableBuilder<CardModel>(
                  valueListenable: cardData,
                  builder: (context, value, _) {
                    return Column(
                      children: [
                        Text("Nama ${value.cardName}"),
                        Text("Card Number ${value.cardNumber}"),
                        Text("Balance ${value.balance}"),
                      ],
                    );
                  }),
            ),
          ],
        ),
      ),
    );
  }
}
