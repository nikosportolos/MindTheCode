import 'dart:core';

import 'package:company_directory_app/models/responses/business_unit_response.dart';
import 'package:company_directory_app/models/responses/generic_response.dart';
import 'package:company_directory_app/utils/rest_helper_util.dart';

class BusinessUnitRepo {
  RestHelperUtil rest = new RestHelperUtil();

  Future<List<BusinessUnitResponse>> getAllBusinessUnits() async {
    List<BusinessUnitResponse> businessUnitsList = new List<BusinessUnitResponse>();
    GenericResponse genericResponse = await rest.getRequest("businessUnits");

    if (genericResponse.error != null) {
      return businessUnitsList;
    }

    final items = (genericResponse.data as List).map((i) => new BusinessUnitResponse.fromJson(i));
    for (final item in items) {
      businessUnitsList.add(item);
    }

    return businessUnitsList;
  }

  Future<BusinessUnitResponse> getBusinessUnitById(int id) async {
    GenericResponse genericResponse = await rest.getRequest("businessUnit/$id");
    if (genericResponse.error != null) {
      return new BusinessUnitResponse();
    } else {
      return BusinessUnitResponse.fromJson(genericResponse.data);
    }
  }
}
