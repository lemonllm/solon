package org.noear.solon.extend.validation.annotation;

import org.noear.solon.core.handler.Context;
import org.noear.solon.core.handler.Result;
import org.noear.solon.extend.validation.Validator;

/**
 *
 * @author noear
 * @since 1.0
 * */
public class NullValidator implements Validator<Null> {
    public static final NullValidator instance = new NullValidator();

    @Override
    public String message(Null anno) {
        return anno.message();
    }

    @Override
    public Result validate(Context ctx, Null anno, String name, StringBuilder tmp) {
        if (name == null) {
            //来自函数
            for (String key : anno.value()) {
                if (ctx.param(key) != null) {
                    tmp.append(',').append(key);
                }
            }
        } else {
            //来自参数
            if (ctx.param(name) != null) {
                tmp.append(',').append(name);
            }
        }

        if (tmp.length() > 1) {
            return Result.failure(tmp.substring(1));
        } else {
            return Result.succeed();
        }
    }
}
