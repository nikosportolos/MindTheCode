import 'dart:core';
import 'package:company_directory_app/models/responses/all_units_response.dart';
import 'package:company_directory_app/models/responses/generic_response.dart';
import 'package:company_directory_app/models/responses/unit_response.dart';
import 'package:company_directory_app/utils/rest_helper_util.dart';

class UnitRepo {
  RestHelperUtil restHelper = new RestHelperUtil();

  Future<List<UnitResponse>> getAllUnits() async {
    print('Loading all units ');
    GenericResponse genericResponse = await restHelper.getRequest("units");

    print(genericResponse);

    if (genericResponse.error != null) {
      return new List<UnitResponse>();
    }

    AllUnitsResponse allResponse = AllUnitsResponse.fromJson(genericResponse.data);

    return allResponse.units;
  }

  Future<UnitResponse> getUnitById(int id) async {
    GenericResponse genericResponse = await restHelper.getRequest("unit/$id");
    if (genericResponse.error != null) {
      return new UnitResponse();
    } else {
      return UnitResponse.fromJson(genericResponse.data);
    }
  }
}
