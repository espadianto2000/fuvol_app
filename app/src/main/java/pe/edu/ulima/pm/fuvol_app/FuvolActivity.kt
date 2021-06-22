package pe.edu.ulima.pm.fuvol_app

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import pe.edu.ulima.pm.fuvol_app.fragments.EquiposFragment
import pe.edu.ulima.pm.fuvol_app.fragments.PosicionesFragment

class FuvolActivity : AppCompatActivity() {

    var fragments : ArrayList<Fragment> = ArrayList()
    var dlaFuvol : DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fuvol)

        val toolbar = findViewById<Toolbar>(R.id.tbaFuvol)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(android.R.drawable.btn_plus)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fragments.add(EquiposFragment())
        fragments.add(PosicionesFragment())

        val nviFuvol = findViewById<NavigationView>(R.id.nviFuvol)
        dlaFuvol = findViewById(R.id.dlaFuvol)

        nviFuvol.setNavigationItemSelectedListener { item : MenuItem ->
            item.setChecked(true)
            val ft = supportFragmentManager.beginTransaction()
            if (item.itemId == R.id.mnuEquipos) {
                // Abrimos fragment AccountFragment
                ft.replace(R.id.flaContent, fragments[0])
            }else if (item.itemId == R.id.mnuTablaPosiciones) {
                // Abrimos fragment ProductsFragment
                ft.replace(R.id.flaContent, fragments[1])
            }
            ft.addToBackStack(null)
            ft.commit()
            dlaFuvol!!.closeDrawers()
            true
        }

        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.flaContent, fragments[0])
        ft.addToBackStack(null)
        ft.commit()

    }
}