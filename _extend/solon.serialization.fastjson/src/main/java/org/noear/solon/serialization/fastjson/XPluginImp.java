package org.noear.solon.serialization.fastjson;

import org.noear.solon.Solon;
import org.noear.solon.core.Bridge;
import org.noear.solon.core.Plugin;

public class XPluginImp implements Plugin {
    public static boolean output_meta = false;

    @Override
    public void start(Solon app) {
        output_meta = app.prop().getInt("solon.output.meta", 0) > 0;

        //XRenderManager.register(render);
        Bridge.renderMapping("@json", new FastjsonRender(false));
        Bridge.renderMapping("@type_json", new FastjsonRender(true));

        //支持Json内容类型执行
        Bridge.actionExecutorAdd(new FastjsonJsonActionExecutor());
    }
}
