import 'package:company_directory_app/models/responses/company_response.dart';

class AllCompaniesResponse {
  final List<CompanyResponse> companies;

  AllCompaniesResponse({this.companies});

  factory AllCompaniesResponse.fromJson(Map<String, dynamic> json) {
    var list = json['companies'] as List;
    List<CompanyResponse> itemsList = list.map((i) => CompanyResponse.fromJson(i)).toList();

    return AllCompaniesResponse(companies: itemsList);
  }
}
