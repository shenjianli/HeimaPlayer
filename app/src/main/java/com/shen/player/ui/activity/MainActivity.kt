package com.shen.player.ui.activity
import androidx.appcompat.widget.Toolbar
import com.shen.player.R
import com.shen.player.base.BaseActivity
import com.shen.player.util.FragmentUtil
import com.shen.player.util.ToolBarManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

class MainActivity : BaseActivity(),ToolBarManager {

    override val toolBar:Toolbar by lazy {find<Toolbar>(R.id.tool_bar)}

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        super.initData()
        initMainToolbar("黑马影音")
    }

    override fun initListener() {
        super.initListener()
        bottomBar.setOnTabSelectListener {
            val transaction = supportFragmentManager.beginTransaction()
            FragmentUtil.fragmentUtil.getFragment(it)?.let { it1 ->
                transaction.replace(R.id.container,
                    it1,it.toString())
            }
            transaction.commit()
        }
    }
}