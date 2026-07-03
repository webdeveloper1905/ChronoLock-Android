# 🔒 ChronoLock - Android Application

ChronoLock, kullanıcıların geleceğe mesajlar ve dijital anılar bırakmasını sağlayan, zamanı akıllıca takip eden bir **Zaman Kapsülü** mobil uygulamasıdır. Kullanıcılar belirledikleri açılış tarihinden önce kapsüllerin içeriğine erişemezler. Proje, modern Android geliştirme standartları ve asenkron ağ mimarisi üzerine inşa edilmiştir.

## 🚀 Teknolojiler ve Mimari Yapı

* **Dil:** Kotlin
* **UI Mimari:** Android XML View System & ViewBinding
* **Ağ Katmanı (Networking):** Retrofit2 & GSON Converter (Asenkron REST API istekleri)
* **Yerel Hafıza & Oturum Yönetimi:** SharedPreferences (Kalıcı "Beni Hatırla" mekanizması)
* **Listeleme:** RecyclerView & Custom Adapter mimarisi ile dinamik kart tasarımı

## 📌 Öne Çıkan Özellikler

* **Güvenli Kimlik Doğrulama:** Kullanıcı kayıt ve giriş işlemleri uzak sunucu entegrasyonuyla doğrulanır.
* **Kalıcı Oturum:** Kullanıcı uygulamayı kapatıp açsa dahi `SharedPreferences` sayesinde oturumu açık kalır. Sağ üstteki "Çıkış" butonu ile güvenli log-out sağlanır.
* **Dinamik Zaman Filtresi:** Kapsüllerin kilit durumu (`Açıldı` / `Kilitli`), cihazın yerel tarihi ile kapsülün hedef tarihi `Adapter` seviyesinde anlık kıyaslanarak görsel rozetlere (Badge) dönüştürülür.
* **Otomatik Liste Güncelleme:** Yeni kapsül eklendikten sonra `onResume` yaşam döngüsü (Lifecycle) tetiklenerek ana sayfa listesi sunucudan otomatik olarak tazelenir.

## 🛠️ Kurulum ve Çalıştırma

1. Projeyi bilgisayarınıza clone edin:
   ```bash
   git clone [https://github.com/KULLANICI_ADIN/ChronoLock-Android.git](https://github.com/KULLANICI_ADIN/ChronoLock-Android.git)
