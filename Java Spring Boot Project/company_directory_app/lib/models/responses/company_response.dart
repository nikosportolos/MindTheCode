class CompanyResponse {
  final int id;
  final String name;

  CompanyResponse({this.id, this.name});

  factory CompanyResponse.fromJson(Map<String, dynamic> json) {
    return CompanyResponse(
      id: json['id'],
      name: json['name'],
    );
  }
}
