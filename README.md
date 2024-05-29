# Proje Kurulumu

Bu projeyi çalıştırmak için aşağıdaki adımları izleyin:

## Adım 1: Docker imajını indirme

Projeyi çalıştırmak için öncelikle DockerHub'dan Docker imajını indirin:

```bash
docker pull demirrs/restaurant-review-app
````
Bu satırı terminal ekranından çalıştırın.
## Adım 2: Docker konteynerini başlatma
```bash
docker run -d --name restaurant-review-app -p 8080:8080 demirrs/restaurant-review-app
````
## Adım 3: Swagger UI'yi açma
Tarayıcınızda Swagger UI'yi açmak için aşağıdaki adrese gidin:
http://localhost:8080/

### Alternatif yol 
[Swagger UI](http://localhost:8080/swagger-ui/index.html#/)

http://localhost:8080/swagger-ui/index.html#/

## Alternatif Kurulum Yolu: GitHub'dan Klonlama
Eğer projeyi GitHub'dan klonlayarak çalıştırmak isterseniz, aşağıdaki adımları izleyin:
```bash
git clone https://github.com/sezerdemir7/RestaurantReviewApp.git
````

# RestaurantReview API Documentation

## Kullanıcı Rolleri ve Yetkiler
### Projede kullanıcı rolleri ve yetkiler aşağıdaki gibidir:

- Standart Kullanıcılar: Yalnızca yorum yapabilirler.
- Kıdemli Kullanıcılar: Restoran oluşturabilirler.
- Yönetici Kullanıcılar: Restoran silebilirler.
### Kurallar
- Standart kullanıcılar yalnızca yorum yapabilir.
- Restoran oluşturmak için kullanıcıların KIDEMLI rolünde olması gerekir.
- Restoran silmek için kullanıcıların YONETICI rolünde olması gerekir.
- Bir kullanıcı, bir restorana yalnızca bir yorum yapabilir ve bunu düzenleyebilir.

## User (Kullanıcılar)

- `GET /v1/user/all`: Tüm kullanıcıları getirmek için kullanılır.
- `POST /v1/user/create`: Yeni bir kullanıcı oluşturmak için kullanılır.
- `GET /v1/user/{id}`: Belirli bir kullanıcıyı getirmek için kullanılır.
- `DELETE /v1/user/{id}`: Belirli bir kullanıcıyı silmek için kullanılır.

## Restaurant (Restoranlar)

- `GET /v1/restaurant/all`: Tüm restoranları getirmek için kullanılır.
- `POST /v1/restaurant/create`: Yeni bir restoran oluşturmak için kullanılır.
- `DELETE /v1/restaurant/delete`: Belirli bir restoranı silmek için kullanılır.
- `GET /v1/restaurant/getRestaurantOwnerId/{id}`: Belirli bir restoran sahibine ait restoranları getirmek için kullanılır.
- `PUT /v1/restaurant/update`: Mevcut bir restoranın bilgilerini güncellemek için kullanılır.
- `GET /v1/restaurant/{id}`: Belirli bir restoranı getirmek için kullanılır.

## Comment (Yorumlar)

- `GET /v1/comment/user`: Belirli bir kullanıcıya ait yorumları getirmek için kullanılır.
- `GET /v1/comment/user-restaurant`: Belirli bir kullanıcı ve restorana ait yorumu getirmek için kullanılır.
- `GET /v1/comment/restaurant/{id}`: Belirli bir restorana ait yorumları getirmek için kullanılır.
- `POST /v1/comment/create`: Yeni bir yorum oluşturmak için kullanılır.
- `DELETE /v1/comment/delete/{id}`: Belirli bir yorumu silmek için kullanılır.
- `PUT /v1/comment/update`: Mevcut bir yorumu güncellemek için kullanılır.

## Hata Durumları
- `404 Not Found`: Belirtilen ID'ye sahip kayıt bulunamadı.
- `400 Bad Request`: İstek uygun formatta değil.
- `403 Forbidden`: Yetkisiz erişim.