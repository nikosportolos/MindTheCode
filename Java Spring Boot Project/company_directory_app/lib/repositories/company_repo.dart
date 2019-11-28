import 'dart:core';
import 'package:company_directory_app/models/responses/all_companies_response.dart';
import 'package:company_directory_app/models/responses/company_response.dart';
import 'package:company_directory_app/models/responses/generic_response.dart';
import 'package:company_directory_app/utils/rest_helper_util.dart';

class CompanyRepo {
  RestHelperUtil restHelper = new RestHelperUtil();

  Future<List<CompanyResponse>> getAllCompanies() async {
    print('Loading all companies ');
    GenericResponse genericResponse = await restHelper.getRequest("companies");

    print(genericResponse);

    if (genericResponse.error != null) {
      return new List<CompanyResponse>();
    }

    AllCompaniesResponse allResponse = AllCompaniesResponse.fromJson(genericResponse.data);

    return allResponse.companies;
  }

  Future<CompanyResponse> getCompanyById(int id) async {
    GenericResponse genericResponse = await restHelper.getRequest("company/$id");
    if (genericResponse.error != null) {
      return new CompanyResponse();
    } else {
      return CompanyResponse.fromJson(genericResponse.data);
    }
  }
}
