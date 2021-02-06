class CardModel {
  String cardId;
  String cardName;
  String cardNumber;
  String balance;
  String anotherInfo;

  CardModel(
      {this.cardId,
      this.cardName,
      this.cardNumber,
      this.balance,
      this.anotherInfo});

  CardModel.fromJson(Map<String, dynamic> json) {
    cardId = json['card_id'];
    cardName = json['card_name'];
    cardNumber = json['card_number'];
    balance = json['balance'];
    anotherInfo = json['another_info'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['card_id'] = this.cardId;
    data['card_name'] = this.cardName;
    data['card_number'] = this.cardNumber;
    data['balance'] = this.balance;
    data['another_info'] = this.anotherInfo;
    return data;
  }
}
