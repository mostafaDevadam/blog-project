class JSONResponse {
  static success(req: any, res: any, message: any, data: any) {
    res.status(200).json({
      code: 500,
      message: message || 'success',
      data: data,
    })
  }

  static serverError(req: any, res: any, message: any, data: any) {
    res.status(500).json({
      code: 500,
      message: message || 'internal server error',
    })
  }
}
