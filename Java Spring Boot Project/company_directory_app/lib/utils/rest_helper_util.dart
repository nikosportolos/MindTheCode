import 'dart:async';
import 'dart:convert';
import 'package:company_directory_app/models/responses/generic_response.dart';
import 'package:http/http.dart' as http;

class RestHelperUtil {
  // Returns required http headers
  Map<String, String> _getHeaders() {
    return {'content-type': "application/json", 'accept': "application/json"};
  }

  // Returns base endpoint URI
  String _getBaseURI() {
    return "https://mtc-company-directory.herokuapp.com/";
  }

  Future<GenericResponse> getRequest(String endpoint) async {
    String endpointURI = _getBaseURI() + endpoint;
    http.Response response = await http.get(endpointURI, headers: _getHeaders());
    print('Status code: ${response.statusCode}');
    print('${response.body}');

    return GenericResponse.fromJson(json.decode(response.body));
  }
}
