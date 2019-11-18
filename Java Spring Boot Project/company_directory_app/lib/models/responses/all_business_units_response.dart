import 'package:company_directory_app/models/responses/business_unit_response.dart';

class AllBusinessUnitsResponse {
  final List<BusinessUnitResponse> businessUnits;

  AllBusinessUnitsResponse({this.businessUnits});

  factory AllBusinessUnitsResponse.fromJson(Map<String, dynamic> json) {
    var list = json['businessUnits'] as List;
    List<BusinessUnitResponse> itemsList = list.map((i) => BusinessUnitResponse.fromJson(i)).toList();

    return AllBusinessUnitsResponse(
      businessUnits: itemsList,
    );
  }
}
