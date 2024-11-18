package controller;

import model.DuLieuCoVua;
import model.ViTri;
import view.Main;

public class Thuattoancattia {
    Main mainCoVua;
    public int chieuSau;
    int nodeCount;

    public Thuattoancattia(Main mainCoVua) {
        this.mainCoVua = mainCoVua;
        this.nodeCount = 0;
    }

    public ViTri minimax(int player, ViTri viTri, int depth) {
        if (depth == 0)
            return viTri;

        ViTri viTriTotNhat = null;
        MoRongNuocDi nuocDiMoi = new MoRongNuocDi(viTri, player);
        nuocDiMoi.moRongNuocDiMoi();
        ViTri[] listViTri = nuocDiMoi.getDanhSachMoRong();

        if (listViTri.length == 0)
            return viTri;

        HamDanhGiaTinhDiem hamDanhGiaTinhDiem = new HamDanhGiaTinhDiem();
        long startTime = System.currentTimeMillis();
        for (ViTri viTriMoi : listViTri) {
            if (viTriTotNhat == null)
                viTriTotNhat = viTriMoi;

            ViTri viTriCoDoiKhang = minimax(DuLieuCoVua.doiNguyenTac(player), viTriMoi, depth - 1);
            int diem = hamDanhGiaTinhDiem.tinhDiem(viTriCoDoiKhang);

            if (player == DuLieuCoVua.NGUOI && diem > hamDanhGiaTinhDiem.tinhDiem(viTriTotNhat)) {
                viTriTotNhat = viTriMoi;
            } else if (player == DuLieuCoVua.MAY && diem < hamDanhGiaTinhDiem.tinhDiem(viTriTotNhat)) {
                viTriTotNhat = viTriMoi;
            }
            nodeCount++;
        }
        long endTime = System.currentTimeMillis(); // Lấy thời gian kết thúc
        long executionTime = endTime - startTime;

        System.out.println("Minimax - Thời gian chạy: " + executionTime + " milliseconds");
        System.out.println("Minimax - Số node đã duyệt: " + nodeCount);

        // In bộ nhớ sử dụng
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Minimax - Bộ nhớ đã sử dụng (MB): " + usedMemory / (1024 * 1024));
        return viTriTotNhat;
    }

    public ViTri alphaBeta(int player, ViTri viTri, int alpha, int beta, int depth) {
        if (depth == 0)
            return viTri;

        ViTri viTriTotNhat = null;
        MoRongNuocDi nuocDiMoi = new MoRongNuocDi(viTri, player);
        nuocDiMoi.moRongNuocDiMoi();
        ViTri[] listViTri = nuocDiMoi.getDanhSachMoRong();

        if (listViTri.length == 0)
            return viTri;

        HamDanhGiaTinhDiem hamDanhGiaTinhDiem = new HamDanhGiaTinhDiem();
        long startTime = System.currentTimeMillis();
        for (ViTri viTriMoi : listViTri) {
            if (viTriTotNhat == null)
                viTriTotNhat = viTriMoi;

            if (player == DuLieuCoVua.NGUOI) {
                ViTri viTriCoDoiKhang = alphaBeta(DuLieuCoVua.MAY, viTriMoi, alpha, beta, depth - 1);
                nodeCount++;
                int diem = hamDanhGiaTinhDiem.tinhDiem(viTriCoDoiKhang);

                if (diem > alpha) {
                    viTriTotNhat = viTriMoi;
                    alpha = diem;
                }

                if (beta <= alpha)
                    break;

            } else {
                ViTri viTriCoDoiKhang = alphaBeta(DuLieuCoVua.NGUOI, viTriMoi, alpha, beta, depth - 1);

                if (new GameCoVua(viTriCoDoiKhang).kiemTraChieuTuong(DuLieuCoVua.NGUOI)) {
                    return viTriMoi;
                }

                int diem = hamDanhGiaTinhDiem.tinhDiem(viTriCoDoiKhang);

                if (diem < beta) {
                    viTriTotNhat = viTriMoi;
                    beta = diem;
                }

                if (beta <= alpha)
                    break;
            }
            nodeCount++;
        }
        long endTime = System.currentTimeMillis(); // Lấy thời gian kết thúc
        long executionTime = endTime - startTime;

        System.out.println("Alpha-Beta - Thời gian chạy: " + executionTime + " milliseconds");
        System.out.println("Alpha-Beta - Số node đã duyệt: " + nodeCount);

        // In bộ nhớ sử dụng
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Alpha-Beta - Bộ nhớ đã sử dụng (MB): " + usedMemory / (1024 * 1024));
        return viTriTotNhat;
    }
}
