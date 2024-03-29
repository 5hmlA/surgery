package osp.surgery.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import osp.surgery.helper.sout
import java.util.*

/**
 * @author yun.
 * @date 2021/7/20
 * @des [一句话描述]
 * @since [https://github.com/5hmlA]
 * <p><a href="https://github.com/5hmlA">github</a>
 */

class Hospital : Plugin<Project> {
    override fun apply(project: Project) {
//        project.extensions.create("spark", com.spark.extension.Spark::class.java)
        val taskReauests = project.gradle.startParameter.taskRequests
        " taskReauests : $taskReauests ".sout()
        if (taskReauests.size > 0) {
            val args = taskReauests[0].args.filter { !it.equals("clean") }
            "args : $args ".sout()
            if (args.isNotEmpty()) {
//                val predicate: (String) -> Boolean = { it.toLowerCase().contains("release") }
//                if (args.any(predicate)) {
//
//                }

//                val android = project.extensions.findByType<com.android.build.gradle.BaseExtension>()
//                val android = project.extensions.findByType(com.android.build.gradle.BaseExtension::class.java)
                val android = project.extensions.findByType(com.android.build.gradle.BaseExtension::class.java)
                println(project.extensions.findByName("android"))
                "project name: ${project.name}  $android  ${android?.transforms}".sout()
                android?.registerTransform(Surgery(project))
            }
        }
    }
}