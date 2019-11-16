import 'package:company_directory_app/models/responses/error_response.dart';

class GenericResponse<T> {
  T data;
  ErrorResponse error;

  GenericResponse({this.data, this.error});
}
