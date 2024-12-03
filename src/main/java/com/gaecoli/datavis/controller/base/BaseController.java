package com.gaecoli.datavis.controller.base;

import com.gaecoli.datavis.utils.ResponseUtils.*;
import com.gaecoli.datavis.utils.ResponseUtils;

public abstract class BaseController {
    protected Response Success(String msg) {
        return Success(msg, null);
    }

    protected Response Success(String msg, Object data) {
        return ResponseUtils.Success(msg, data);
    }

    protected Response Error(String msg) {
        return ResponseUtils.Error(msg);
    }

    protected Response UNAUTHORIZED(String msg) { return ResponseUtils.Unauthorized(msg); };
}
