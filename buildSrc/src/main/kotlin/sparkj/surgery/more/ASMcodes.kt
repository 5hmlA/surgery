package sparkj.surgery.more

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.*
import sparkj.surgery.JAPI
import sparkj.surgery.doctors.logisitscCenter


/**
 * @author yun.
 * @date 2021/9/9
 * @des [一句话描述]
 * @since [https://github.com/ZuYun]
 * <p><a href="https://github.com/ZuYun">github</a>
 */
fun logCode(mv: MethodVisitor, tag: String, msg: String) {
    mv.visitLdcInsn(tag)
    mv.visitLdcInsn(msg)
    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false)
    mv.visitInsn(Opcodes.POP) //log.i有返回值 需要扔掉
}

fun MethodVisitor.addLogCode(tag: String, msg: String) {
    visitLdcInsn(tag)
    visitLdcInsn(msg)
    visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false)
    visitInsn(Opcodes.POP) //log.i有返回值 需要扔掉
}

fun MethodNode.copy(): MethodNode {
    return MethodNode(JAPI, access, name, desc, signature, exceptions.toTypedArray())
}

fun InsnList.insertLogCodeBefore(ret: AbstractInsnNode, tag: String, msg: String) {
    insertBefore(ret, LdcInsnNode(tag))
    insertBefore(ret, LdcInsnNode(msg))
    insertBefore(ret, MethodInsnNode(Opcodes.INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false))
    insertBefore(ret, InsnNode(Opcodes.POP)) //log.i有返回值 扔掉
}

fun InsnList.findAll(vararg opcodes: Int) = this.filter { it.opcode in opcodes }


//setOnClickListener {
//
//}
//name = [onViewCreated$lambda-0], descriptor = [(Lop/po/apptest/FirstFragment;Landroid/view/View;)V], signature = [null], exceptions = [null]
fun isLambdaClick(methodName: String, descriptor: String, className: String) =
    methodName.contains("\$lambda-") && descriptor.endsWith("$className;Landroid/view/View;)V")

//
//============================================================================================
//setOnClickListener { view ->
//
//}
//name = [onCreate$lambda-0], descriptor = [(Landroid/view/View;)V], signature = [null], exceptions = [null]
fun isLambdaWithParamClick(methodName: String, descriptor: String, className: String) = methodName.contains("\$lambda-") &&
        descriptor == "(Landroid/view/View;)V"

//
//==============================================================================================
//setOnClickListener { object :View.OnClickListener{
//    override fun onClick(v: View?) {
//
//    }
//}
//name = [onViewCreated$lambda-0], descriptor = [(Lop/po/apptest/FirstFragment;Landroid/view/View;)V], signature = [null], exceptions = [null]
////innerClass
//    name = [onClick], descriptor = [(Landroid/view/View;)V], signature = [null], exceptions = [null]
//
//    ===================================================================================================
////setOnClickListener(this)
//    [onClick], descriptor = [(Landroid/view/View;)V], signature = [null], exceptions = [null]
fun isNormalClick(methodName: String, descriptor: String) = "onClick" == methodName &&
        descriptor == "(Landroid/view/View;)V"
