package com.alexanr.demin.materialdesign;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.main_container,new MainFragment()).commit();
        toolbar = findViewById(R.id.main_toolbar);
        drawer = findViewById(R.id.main_drawer);
        navigationView = findViewById(R.id.main_nav_view);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.drawer_menu_settings) {
            startActivity(new Intent(this, StyleSelectionActivity.class));
        }
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

/*1. Создать один фрагмент для главной activity.
        Подключить Floating action button к макету этого фрагмента.
        На FAB в качестве иконки установить «плюсик».
        В дальнейшем при нажатии на эту кнопку будет открываться окно добавления фотографии.
        Скоординировать его со Snackbar. Когда будете добавлять фотографии, сделать так, чтобы Snackbar сообщал «Фото добавлено».
        2. Создать навигационное меню (Drawer): «главная», «выбор цветовой темы». Запрограммировать переключение на разные activity.
        3. В созданном ранее в главной activity-фрагменте подготовить все для размещения с помощью GridLayoutManager и RecyclerView
        «плитки» фотографий.
        Подготовить CardView для отображения фотографии: в ней будут размещаться ImageView и значок – признак занесения
        фото в избранное (например, звездочка или сердечко).
        При нажатии на FAB должно открываться системное окно фотографирования.
        Снимки должны сохраняться в каталог приложения getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)).
        Отображаться они должны в подготовленной «плитке», при нажатии на которую фото должно открываться для детального
        просмотра в новой activity. При длительном нажатии на «плитку» фотографии (long tap)
        должно выводится диалоговое окно с вопросом, действительно ли надо удалить фото.
        При нажатии «Да» фотография должна удаляться из списка и из каталога приложения.*/

