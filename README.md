# DostaGiderApp (ArabamApi)

İkinci El Araç satışı yapılan uygulama demo.

### Splash Screen
<img src="https://media.giphy.com/media/mNDervLLA6B22huAo8/giphy.gif" /> <img src="https://user-images.githubusercontent.com/37152719/107275527-eff3be80-6a62-11eb-9db2-3ea5c261b768.jpg" /> 


* Animation
* Network Kontrolü
* Networking kütüphanesi olarak Refrofit kullanılmıştır. Rxjava ile asenkron işlemler gerçekleştiriliyor.

<br/>

### Pagination
<img src="https://media.giphy.com/media/hJEFpkT5aQZpqM6ZJZ/giphy.gif" />

* Paging Library kullanıldı.
* ViewModel , LiveData ve CompositeDisposable  kullanıldı. 

### Sorting 
<img src="https://media.giphy.com/media/c6ikoQxTC90riEe6sp/giphy.gif" /> 

* Api da verilen tüm sıralamalar gerçekleşiyor. Aynı ListViewModel üzerinden LiveData clear edilmesiyle yeni istek atılıyor.
* Fab ile filtreleme ve sıralamaya ulaşılıyor. Filtreleme için Alert Dialog , Sorting için BottomSheetFragment.

### Filter

<img src="https://media.giphy.com/media/yARfYd2QvcyUUDQB8k/giphy.gif" /> 

* Araç Model Yılına göre filtreleme yapılıyor. Farklı bir Query olduğu için ayrı bir Data Source kullanıyor. Ancak listelendiği Recycler ve Observe LiveData aynı.

### Detail
<img src="https://media.giphy.com/media/Er0ln9w0mvhSJFd0O7/giphy.gif" /> <img src="https://media.giphy.com/media/w6XtitufWyk8YKg8CM/giphy.gif" /> 

* Detail View Model üzerinden veri akışı sağlanıyor. ChildFragment yapısı kullanılarak  İlan bilgileri ve Text Fragment a requireParentfragment kullanılarak viewmodel instance yaratıldı
kod tekrarı engellendi.
* Fotoğraf albüm için ViewPager2 kullanıldı. Click ile Full Screen Fotoğraf açılıyor.
* TabLayout ve ViewPager ile ChildFragment e ulaşıldı.
* Ara butonu ile açılan Bottom Sheet üzerinden CALL a yönlendirme.

### Bonus Shimmer Effect. :)
<img src="https://media.giphy.com/media/669ZaKuGACuppzYHVf/giphy.gif" />
