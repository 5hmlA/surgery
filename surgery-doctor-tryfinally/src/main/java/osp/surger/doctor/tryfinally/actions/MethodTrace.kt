package osp.surger.doctor.tryfinally.actions

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.commons.AdviceAdapter
import osp.surger.doctor.tryfinally.TryFinally

/**
 * @author yun.
 * @date 2022/4/28
 * @des [一句话描述]
 * @since [https://github.com/5hmlA]
 * <p><a href="https://github.com/5hmlA">github</a>
 */
class MethodTrace : TryFinally {
    override fun methodEnter(className: String, methodName: String, mv: MethodVisitor, adapter: AdviceAdapter) {
        val tag = "$className -> $methodName".let {
            it.substring(0.coerceAtLeast(it.length - 126))
        }
        mv.visitLdcInsn(tag)
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/os/Trace", "beginSection", "(Ljava/lang/String;)V", false)
    }

    override fun methodExit(className: String, methodName: String, mv: MethodVisitor, adapter: AdviceAdapter) {
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/os/Trace", "endSection", "()V", false)
    }

}