import 'dart:core';
import 'package:company_directory_app/models/responses/all_positions_response.dart';
import 'package:company_directory_app/models/responses/generic_response.dart';
import 'package:company_directory_app/models/responses/position_response.dart';
import 'package:company_directory_app/utils/rest_helper_util.dart';

class PositionRepo {
  RestHelperUtil restHelper = new RestHelperUtil();

  Future<List<PositionResponse>> getAllPositions() async {
    print('Loading all positions ');
    GenericResponse genericResponse = await restHelper.getRequest("positions");

    print(genericResponse);

    if (genericResponse.error != null) {
      return new List<PositionResponse>();
    }

    AllPositionsResponse allResponse = AllPositionsResponse.fromJson(genericResponse.data);

    return allResponse.positions;
  }

  Future<PositionResponse> getPositionById(int id) async {
    GenericResponse genericResponse = await restHelper.getRequest("position/$id");
    if (genericResponse.error != null) {
      return new PositionResponse();
    } else {
      return PositionResponse.fromJson(genericResponse.data);
    }
  }
}
