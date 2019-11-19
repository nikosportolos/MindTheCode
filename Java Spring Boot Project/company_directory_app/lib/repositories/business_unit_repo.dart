import 'dart:core';
import 'package:company_directory_app/models/responses/all_business_units_response.dart';
import 'package:company_directory_app/models/responses/business_unit_response.dart';
import 'package:company_directory_app/models/responses/generic_response.dart';
import 'package:company_directory_app/utils/rest_helper_util.dart';

class BusinessUnitRepo {
  RestHelperUtil restHelper = new RestHelperUtil();

  Future<List<BusinessUnitResponse>> getAllBusinessUnits() async {
    print('Loading all business untis ');
    GenericResponse genericResponse = await restHelper.getRequest("businessUnits");

    print(genericResponse);

    if (genericResponse.error != null) {
      return new List<BusinessUnitResponse>();
    }

    AllBusinessUnitsResponse allResponse = AllBusinessUnitsResponse.fromJson(genericResponse.data);

    return allResponse.businessUnits;
  }

  Future<BusinessUnitResponse> getBusinessUnitById(int id) async {
    GenericResponse genericResponse = await restHelper.getRequest("businessUnit/$id");
    if (genericResponse.error != null) {
      return new BusinessUnitResponse();
    } else {
      return BusinessUnitResponse.fromJson(genericResponse.data);
    }
  }
}
