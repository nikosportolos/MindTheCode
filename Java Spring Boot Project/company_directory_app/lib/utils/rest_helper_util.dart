import 'dart:async';
import 'dart:convert';
import 'package:http/http.dart' as http;

class RestHelperUtil {
  // Returns required http headers
  static Map<String, String> _getHeaders() {
    return {'content-type': "application/json", 'accept': "application/json"};
  }

  // Returns endpoint URI
  static String _getURI() {
    return "https://mtc-company-directory.herokuapp.com/";
  }

// Returns request object
//  static String _buildRequest(String searchText) {
//    RequestData _data = RequestData(search: searchText);
//    Request req = Request(key: "free", id: "9m9c8U4f", data: _data);
//
//    // Encode request to json
//    var request = json.encode(req.toJson());
//    print('$request');
//    return request;
//  }
//
//  static Future<T> searchForSong(String searchText) async {
//    print('Sending post request...');
//
//    try {
//      http.Response response = await http.post(
//        _getURI(),
//        headers: _getHeaders(),
//        body: _buildRequest(searchText),
//      );
//
//      print('Status code: ${response.statusCode}');
//      print('${response.body}');
//
//      if (response.statusCode == 200) {
//        if (response.body.contains('"result":["off"]')) {
//          throw Exception('Song not found');
//        }
//
//        return Response.fromJson(json.decode(response.body));
//      } else {
//        throw Exception('Failed to load song info');
//      }
//    } catch (error) {
//      print('#searchForSong: ${error.toString()}');
//      throw Exception(error.toString());
//    }
//  }
}
